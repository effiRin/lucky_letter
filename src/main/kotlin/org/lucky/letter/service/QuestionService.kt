package org.lucky.letter.service

import org.lucky.letter.common.DateUtil
import org.lucky.letter.model.dto.AnswerCountResult
import org.lucky.letter.model.dto.QuestionListResult
import org.lucky.letter.model.request.QuestionRequest
import org.lucky.letter.model.request.toChoice
import org.lucky.letter.model.request.toQuestion
import org.lucky.letter.model.response.*
import org.lucky.letter.model.response.toResponse
import org.lucky.letter.repository.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.InvalidParameterException

@Service
class QuestionService(
    private val questionRepository: QuestionRepository,
    private val choiceRepository: ChoiceRepository,
    private val userRepository: UserRepository,
    private val answerRepository: AnswerRepository,
) {

    fun getQuestion(userId: Int): QuestionResponse? {
        return questionRepository.getQuestion(userId = userId)?.let {
            it.toQuestionResponse(
                choices = choiceRepository.findChoicesByQuestionId(questionId = it.id!!).map { it.toResponse() },
                rewardCount = userRepository.findByIdOrNull(userId)?.rewardCount ?: 0,
            )
        }
    }

    fun getQuestionDetail(questionId: Int): QuestionDetailResponse? {
        return questionRepository.findQuestionById(questionId)?.toQuestionDetailResponse()
    }

    fun getQuestions(userId: Int): QuestionReceivedResponse {
        val rewardCount = userRepository.findByIdOrNull(userId)?.rewardCount ?: 0

        val questionsDto = questionRepository.getQuestions(userId = userId).map {
            QuestionListResult(
                questionId = it.getQuestionId(),
                title = it.getTitle(),
                questionContent = it.getQuestionContent(),
                closedAt = DateUtil.convertLocalDateTime(it.getClosedAt()),
                choiceId = it.getChoiceId(),
                choiceContent = it.getChoiceContent(),
            )
        }

        val questions = questionsDto.groupBy { it.questionId }

        val questionResponse = mutableListOf<QuestionReceivedListResponse>()

        questions.mapKeys {
            val result = it.value
            val resultFirst = result.firstOrNull()

            questionResponse.add(
                QuestionReceivedListResponse(
                    questionId = it.key,
                    title = resultFirst?.title,
                    content = resultFirst?.questionContent,
                    choices = result.map {
                        ChoiceResponse(
                            id = it.choiceId,
                            content = it.choiceContent,
                        )
                    },
                    closedAt = resultFirst?.closedAt,
                ),
            )
        }

        return QuestionReceivedResponse(
            questions = questionResponse,
            rewardCount = rewardCount,
        )
    }

    @Transactional
    fun createQuestion(request: QuestionRequest): QuestionResponse {
        val question = questionRepository.save(request.toQuestion())

        val choicesResponse = mutableListOf<ChoiceResponse>()

        request.choices.map {
            choicesResponse.add(choiceRepository.save(it.toChoice(questionId = question.id!!)).toResponse())
        }

        userRepository.findByIdOrNull(request.userId)?.apply {
            if (rewardCount <= 0) throw InvalidParameterException()
            rewardCount -= 1
        }?.run {
            userRepository.save(this)
        }

        return QuestionResponse(
            questionId = question.id!!,
            title = question.title,
            content = question.content,
            choices = choicesResponse,
            closedAt = DateUtil.convertLocalDateTime(question.closedAt),
        )
    }

    fun getQuestionList(
        userId: Int,
        sorter: String,
        isMine: Boolean,
    ): List<QuestionListResponse> {
        var answers = when (sorter) {
            "popular" -> {
                answerRepository.findQuestionIdOrderByPopularity().map {
                    AnswerCountResult(
                        questionId = it.getQuestionId(),
                        choiceId = it.getChoiceId(),
                        cnt = it.getCnt(),
                    )
                }.groupBy { it.questionId }
            }

            "recent" -> {
                answerRepository.findQuestionIdOrderByIdDesc().map {
                    AnswerCountResult(
                        questionId = it.getQuestionId(),
                        choiceId = it.getChoiceId(),
                        cnt = it.getCnt(),
                    )
                }.groupBy { it.questionId }
            }

            else -> throw InvalidParameterException()
        }

        if (isMine) {
            val myAnsweredQuestionId = answerRepository.findAnswersByUserId(userId).map { it.questionId }
            answers = answers.filter { it.key in myAnsweredQuestionId }
        }

        val questionIds = answers.keys.toList()

        val aiAnswer = answerRepository.findAnswersByUserIdAndQuestionIdIn(userId = 0, questionId = questionIds)
            .associateBy { it.questionId }

        val result = mutableListOf<QuestionListResponse>()

        answers.mapKeys { answer ->
            val questionId = answer.key
            val answerResults = answer.value // questionId 1 - choiceId N
            val answerResultByChoice = answerResults.associateBy { it.choiceId }

            val question = questionRepository.findByIdOrNull(questionId)

            question?.let {
                val answerCount = answerResults.sumOf { it.cnt }
                val aiAnswerByQuestionId = aiAnswer[it.id]

                result.add(
                    QuestionListResponse(
                        questionId = it.id!!,
                        title = it.title,
                        content = it.content,
                        choices = choiceRepository.findChoicesByQuestionId(questionId = it.id)
                            .map { choice ->
                                choice.toChoiceListResponse(
                                    answerResultByChoice[choice.id],
                                    answerCount, // 일단 ai 답변은 count +1하지 않음
                                )
                            },
                        answerCount = answerCount,
                        createdAt = it.createdAt,
                        userNickname = userRepository.findByIdOrNull(userId)?.nickname,
                        aiAnswer = aiAnswerByQuestionId?.toAiAnswer(),
                    ),
                )
            }
        }

        return result
    }

    fun getMyQuestion(userId: Int): List<QuestionListResponse> {
        val myQuestions = questionRepository.findQuestionsByUserIdOrderByIdDesc(userId)
        val questionIds = myQuestions.mapNotNull { it.id }

        val user = userRepository.findUserById(id = userId)

        val questionAnswers = answerRepository.findAnswersByQuestionIdIn(questionId = questionIds)
                .groupBy { it.questionId }

        val answerByQuestionId = answerRepository.findQuestionIdOrderByIdDesc().map {
            AnswerCountResult(
                questionId = it.getQuestionId(),
                choiceId = it.getChoiceId(),
                cnt = it.getCnt(),
            )
        }.groupBy { it.questionId }

        val aiAnswer = answerRepository.findAnswersByUserIdAndQuestionIdIn(userId = 0, questionId = questionIds)
            .associateBy { it.questionId }

        val result = mutableListOf<QuestionListResponse>()

        questionRepository.findQuestionsByUserIdOrderByIdDesc(userId).map { question ->
            val answer = answerByQuestionId[question.id]
            val answerResultByChoice = answer?.associateBy { it.choiceId }

            result.add(
                QuestionListResponse(
                    questionId = question.id!!,
                    title = question.title,
                    content = question.content,
                    choices = choiceRepository.findChoicesByQuestionId(questionId = question.id)
                        .map { choice ->
                            choice.toChoiceListResponse(
                                answerCountResults = answerResultByChoice?.get(choice.id),
                                answerCount = answerResultByChoice?.get(choice.id)?.cnt ?: 0, // 일단 ai 답변은 count +1하지 않음
                            )
                        },
                    answerCount = questionAnswers[question.id]?.size ?: 0,
                    createdAt = question.createdAt,
                    userNickname = user?.nickname,
                    aiAnswer = aiAnswer[question.id]?.toAiAnswer(),
                ),
            )
        }
        return result
    }
}
