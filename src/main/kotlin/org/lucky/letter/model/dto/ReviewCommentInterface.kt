package org.lucky.letter.model.dto

import java.time.LocalDateTime

interface ReviewCommentInterface {

    fun getReviewCommentId(): Int

    fun getContent(): String

    fun getIsReported(): Int
    fun getNickname(): String

    fun getCreatedAt(): LocalDateTime
}
