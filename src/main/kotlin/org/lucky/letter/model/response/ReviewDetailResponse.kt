package org.lucky.letter.model.response

import org.lucky.letter.model.dto.ReviewDetailInterface

data class ReviewDetailResponse(
    val reviewId: Int,
    val questionId: Int,
    val nickname: String,
    val title: String,
    val content: String,
    val viewCount: Int,
)

fun ReviewDetailInterface.toReviewDetailResponse() = ReviewDetailResponse(
    reviewId = getReviewId(),
    questionId = getQuestionId(),
    nickname = getNickname(),
    title = getTitle(),
    content = getContent(),
    viewCount = getViewCount() ?: 0
)