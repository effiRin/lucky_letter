package org.lucky.letter.service

import org.lucky.letter.model.request.AnswerRequest
import org.lucky.letter.model.request.toAnswer
import org.lucky.letter.model.response.AnswerResponse
import org.lucky.letter.repository.AnswerRepository
import org.lucky.letter.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.security.InvalidParameterException

@Service
class AnswerService(
    private val answerRepository: AnswerRepository,
    private val userRepository: UserRepository,
) {
    fun createAnswer(request: AnswerRequest): AnswerResponse {
        answerRepository.save(request.toAnswer())

        val user = userRepository.findByIdOrNull(request.userId)?.apply {
            rewardCount += 1
        }?.run {
            userRepository.save(this)
        } ?: throw InvalidParameterException()

        return AnswerResponse(
            rewardCount = user.rewardCount,
        )
    }
}
