package org.lucky.letter.model.dto

interface ReviewDetailInterface {

    fun getReviewId(): Int

    fun getQuestionId(): Int

    fun getTitle(): String

    fun getContent(): String

    fun getViewCount(): Int?

    fun getNickname(): String

    fun getIsReported(): Int

    fun getUserId(): Int
}
