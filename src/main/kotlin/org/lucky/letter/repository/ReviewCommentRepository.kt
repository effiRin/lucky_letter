package org.lucky.letter.repository

import org.lucky.letter.entity.ReviewComment
import org.lucky.letter.model.dto.ReviewCommentInterface
import org.lucky.letter.repository.query.ReviewQuery
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface ReviewCommentRepository : CrudRepository<ReviewComment, Int> {

    @Query(nativeQuery = true, value = ReviewQuery.findReviewComments)
    fun findReviewComments(reviewId: Int): List<ReviewCommentInterface>?
}
