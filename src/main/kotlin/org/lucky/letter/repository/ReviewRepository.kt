package org.lucky.letter.repository

import org.lucky.letter.entity.Review
import org.lucky.letter.model.dto.ReviewDetailInterface
import org.lucky.letter.repository.query.ReviewQuery
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ReviewRepository : CrudRepository<Review, Int> {

    @Query(nativeQuery = true, value = ReviewQuery.findReview)
    fun findReview(reviewId: Int): ReviewDetailInterface?

    @Query(nativeQuery = true, value = ReviewQuery.findReviewList)
    fun findReviewList(categoryId: List<Int>, limit: Int, offset: Int): List<ReviewDetailInterface>?

    @Query(nativeQuery = true, value = ReviewQuery.countReviewList)
    fun countReviewList(categoryId: List<Int>): Long

    fun findByQuestionIdAndIsDeleted(questionId: Int, isDeleted: Boolean = false): Review?

    fun findByIdAndIsDeleted(id: Int, isDeleted: Boolean = false): Review?
}
