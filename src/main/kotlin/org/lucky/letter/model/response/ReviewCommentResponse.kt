package org.lucky.letter.model.response

import org.lucky.letter.model.dto.ReviewCommentInterface
import java.time.LocalDateTime

data class ReviewCommentResponse(
    val id: Int,
    val content: String,
    val nickname: String,
    val isReported: Boolean,
    val createdAt: LocalDateTime,
)

fun ReviewCommentInterface.toReviewCommentResponse() = ReviewCommentResponse(
    id = getReviewCommentId(),
    content = getContent(),
    nickname = getNickname(),
    isReported = getIsReported() == 1,
    createdAt = getCreatedAt(),
)
