package org.lucky.letter.model.request

import org.lucky.letter.entity.Question

data class QuestionRequest(
    val userId: Int,
    val categoryId: String,
    val title: String,
    val content: String,
    val choices: List<ChoiceRequest>,
)

fun QuestionRequest.toQuestion() = Question(
    userId = userId,
    title = title,
    content = content,
    categoryId = categoryId,
)
