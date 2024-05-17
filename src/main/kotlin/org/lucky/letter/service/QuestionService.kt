package org.lucky.letter.service

import org.lucky.letter.model.request.QuestionRequest
import org.lucky.letter.model.request.toChoice
import org.lucky.letter.model.request.toQuestion
import org.lucky.letter.model.response.ChoiceResponse
import org.lucky.letter.model.response.QuestionResponse
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
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository,
) {

    fun getQuestion(userId: Int): QuestionResponse? {
        return questionRepository.getQuestion(userId = userId)?.let {
            it.toResponse(
                choices = choiceRepository.findChoicesByQuestionId(questionId = it.id!!).map { it.toResponse() },
                rewardCount = userRepository.findByIdOrNull(userId)?.rewardCount ?: 0
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
        )
    }
}
