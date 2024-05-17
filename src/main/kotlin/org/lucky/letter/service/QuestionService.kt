package org.lucky.letter.service

import org.lucky.letter.common.DateUtil
import org.lucky.letter.model.dto.AnswerCountResult
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
            it.toResponse(
                choices = choiceRepository.findChoicesByQuestionId(questionId = it.id!!).map { it.toResponse() },
                rewardCount = userRepository.findByIdOrNull(userId)?.rewardCount ?: 0,
            )
        }
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

    fun getQuestionList(userId: Int, sorter: String?, isMine: Boolean): List<QuestionListResponse> {
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

        val result = mutableListOf<QuestionListResponse>()

        answers.mapKeys { answer ->
            val questionId = answer.key
            val answerResults = answer.value // questionId 1 - choiceId N
            val answerResultByChoice = answerResults.associateBy { it.choiceId }

            val question = questionRepository.findByIdOrNull(questionId)

            question?.let {
                val answerCount = answerResults.sumOf { it.cnt }

                result.add(
                    QuestionListResponse(
                        questionId = it.id!!,
                        title = it.title,
                        content = it.content,
                        choices = choiceRepository.findChoicesByQuestionId(questionId = it.id)
                            .map { choice ->
                                choice.toChoiceListResponse(
                                    answerResultByChoice[choice.id],
                                    answerCount,
                                )
                            },
                        answerCount = answerCount,
                        createdAt = it.createdAt,
                        userNickname = userRepository.findByIdOrNull(userId)?.nickname,
                    ),
                )
            }
        }

        return result
    }
}
