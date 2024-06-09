package org.lucky.letter.service

import org.lucky.letter.entity.delete
import org.lucky.letter.entity.update
import org.lucky.letter.model.request.ReviewCommentRequest
import org.lucky.letter.model.request.ReviewRequest
import org.lucky.letter.model.request.toEntity
import org.lucky.letter.model.response.*
import org.lucky.letter.repository.ReviewCommentRepository
import org.lucky.letter.repository.ReviewRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val reviewCommentRepository: ReviewCommentRepository,
) {

    fun saveReview(request: ReviewRequest): ReviewResponse {
        val review = reviewRepository.findByQuestionIdAndIsDeleted(questionId = request.questionId)?.let { review ->
            review.update(title = request.title, content = request.content)
        } ?: request.toEntity()

        return reviewRepository.save(review).toReviewResponse()
    }

    fun deleteReview(reviewId: Int): Boolean {
        val deletedReview = reviewRepository.findByIdAndIsDeleted(reviewId)?.delete() ?: return false
        return reviewRepository.save(deletedReview).isDeleted
    }

    fun getReviewDetail(reviewId: Int): ReviewDetailResponse? {
        return reviewRepository.findReview(reviewId)?.toReviewDetailResponse()
    }

    fun getReviewComment(reviewId: Int): List<ReviewCommentResponse>? {
        return reviewCommentRepository.findReviewComments(reviewId = reviewId)?.map {
            it.toReviewCommentResponse()
        }
    }

    fun saveComment(request: ReviewCommentRequest): Boolean {
        return reviewCommentRepository.save(request.toEntity()).userId == request.userId
    }
}
