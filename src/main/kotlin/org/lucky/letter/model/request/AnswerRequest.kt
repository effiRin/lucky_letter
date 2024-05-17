package org.lucky.letter.model.request

import org.lucky.letter.entity.Answer

data class AnswerRequest(
    val userId: Int, // 0이면 ai
    val questionId: Int,
    val choiceId: Int, // 0이면 관심 없음
    val reason: String? = null
)

fun AnswerRequest.toAnswer() = Answer(
    userId = userId,
    questionId = questionId,
    choiceId = choiceId,
    reason = reason,
)