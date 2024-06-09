package org.lucky.letter.model.request

import org.lucky.letter.entity.ReviewComment

data class ReviewCommentRequest(
    val reviewId: Int,
    val userId: Int,
    val content: String,
)

fun ReviewCommentRequest.toEntity() = ReviewComment(
    reviewId = reviewId,
    userId = userId,
    content = content
)
