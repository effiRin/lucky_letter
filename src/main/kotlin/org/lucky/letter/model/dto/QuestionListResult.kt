package org.lucky.letter.model.dto

import java.time.LocalDateTime

data class QuestionListResult(
    val questionId: Int,
    val title: String,
    val questionContent: String,
    val closedAt: LocalDateTime,
    val choiceId: Int,
    val choiceContent: String,
)
