package org.lucky.letter.model.response

import org.lucky.letter.model.dto.ReviewDetailInterface

data class ReviewListResponse(
    val reviewId: Int,
    val title: String,
    val viewCount: Int,
    val nickname: String,
    val isReported: Boolean,
    val isMine: Boolean,
)

fun ReviewDetailInterface.toReviewListResponse(userId: Int) = ReviewListResponse(
    reviewId = getReviewId(),
    title = getTitle(),
    viewCount = getViewCount() ?: 0,
    nickname = getNickname(),
    isReported = getIsReported() == 1,
    isMine = getUserId() == userId
)