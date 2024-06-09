package org.lucky.letter.model.request

import org.lucky.letter.entity.Review

data class ReviewRequest(
    val questionId: Int,
    val title: String,
    val content: String
)

fun ReviewRequest.toEntity() = Review(
    questionId = questionId,
    title = title,
    content = content,
)