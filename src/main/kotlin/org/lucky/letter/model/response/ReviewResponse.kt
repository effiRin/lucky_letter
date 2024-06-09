package org.lucky.letter.model.response

import org.lucky.letter.entity.Review

data class ReviewResponse(
    val reviewId: Int,
    val questionId: Int,
    val title: String,
    val content: String,
)

fun Review.toReviewResponse() = ReviewResponse(
    reviewId = id!!,
    questionId = questionId,
    title = title,
    content = content,
)
