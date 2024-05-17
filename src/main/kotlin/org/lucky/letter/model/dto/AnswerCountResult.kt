package org.lucky.letter.model.dto

data class AnswerCountResult(
    val questionId: Int,
    val choiceId: Int,
    val cnt: Int,
)