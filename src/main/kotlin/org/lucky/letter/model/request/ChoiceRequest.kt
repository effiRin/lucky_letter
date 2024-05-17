package org.lucky.letter.model.request

import org.lucky.letter.entity.Choice

data class ChoiceRequest(
    val content: String,
)

fun ChoiceRequest.toChoice(questionId: Int) = Choice(
    questionId = questionId,
    content = content
)