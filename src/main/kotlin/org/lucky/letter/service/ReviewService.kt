package org.lucky.letter.service

import org.lucky.letter.entity.delete
import org.lucky.letter.entity.update
import org.lucky.letter.entity.updateContent
import org.lucky.letter.model.request.ReviewCommentModifyRequest
import org.lucky.letter.model.request.ReviewCommentRequest
import org.lucky.letter.model.request.ReviewRequest
import org.lucky.letter.model.request.toEntity
import org.lucky.letter.model.response.*
import org.lucky.letter.repository.ReviewCommentRepository
import org.lucky.letter.repository.ReviewRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.security.InvalidParameterException

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
        return reviewRepository.findReview(reviewId)?.let { reviewResult ->
            // 후기 조회 시 뷰카운트 +1
            reviewRepository.findByIdOrNull(reviewId)?.apply {
                viewCount += 1
            }?.run {
                reviewRepository.save(this)
            }
            reviewResult.toReviewDetailResponse()
        }
    }

    fun getReviewComment(reviewId: Int): List<ReviewCommentResponse>? {
        return reviewCommentRepository.findReviewComments(reviewId = reviewId)?.map {
            it.toReviewCommentResponse()
        }
    }

    fun createReviewComment(request: ReviewCommentRequest): Boolean {
        return reviewCommentRepository.save(request.toEntity()).userId == request.userId
    }

    fun modifyReviewComment(request: ReviewCommentModifyRequest): Boolean {
        return reviewCommentRepository.findByIdAndIsDeleted(request.reviewCommentId)?.let { reviewComment ->
            reviewComment.updateContent(content = request.content).run {
                reviewCommentRepository.save(this).id == request.reviewCommentId
            }
        } ?: throw InvalidParameterException()
    }

    fun deleteReviewComment(reviewCommentId: Int): Boolean {
        val deletedReviewComment =
            reviewCommentRepository.findByIdAndIsDeleted(reviewCommentId)?.delete() ?: return false
        return reviewCommentRepository.save(deletedReviewComment).isDeleted
    }

    fun reportReview(reviewId: Int): Boolean {
        return reviewRepository.findByIdOrNull(reviewId)?.let {
            reviewRepository.save(
                it.apply { isReported = true },
            ).id == reviewId
        } ?: false
    }

    fun reportReviewComment(reviewCommentId: Int): Boolean {
        return reviewCommentRepository.findByIdOrNull(reviewCommentId)?.let {
            reviewCommentRepository.save(
                it.apply { isReported = true },
            ).id == reviewCommentId
        } ?: false
    }
}
