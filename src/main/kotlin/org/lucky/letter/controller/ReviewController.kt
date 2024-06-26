package org.lucky.letter.controller

import org.lucky.letter.model.request.ReviewCommentModifyRequest
import org.lucky.letter.model.request.ReviewCommentRequest
import org.lucky.letter.model.request.ReviewRequest
import org.lucky.letter.model.response.ReviewCommentResponse
import org.lucky.letter.model.response.ReviewDetailResponse
import org.lucky.letter.model.response.ReviewListResponse
import org.lucky.letter.model.response.ReviewResponse
import org.lucky.letter.service.ReviewService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/review")
class ReviewController(
    private val reviewService: ReviewService,
) {

    // 리뷰 생성 및 수정
    @PutMapping("")
    fun saveReview(@RequestBody request: ReviewRequest): ReviewResponse {
        return reviewService.saveReview(request = request)
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    fun deleteReview(@PathVariable reviewId: Int): Boolean {
        return reviewService.deleteReview(reviewId)
    }

    // 리뷰 목록 조회
    @GetMapping("/list")
    fun getReviewList(
        categoryId: Int? = null,
        userId: Int,
        page: Int? = 0,
        size: Int? = 10,
    ): Page<ReviewListResponse> {
        return reviewService.getReviewList(categoryId = categoryId, userId = userId, pageRequest = PageRequest.of(page ?: 0, size ?: 10))
    }

    // 리뷰 상세 조회
    @GetMapping("/{reviewId}")
    fun getReviewDetail(@PathVariable reviewId: Int): ReviewDetailResponse? {
        return reviewService.getReviewDetail(reviewId)
    }

    // 리뷰 댓글 리스트 조회
    @GetMapping("/comment/{reviewId}")
    fun getReviewComment(@PathVariable reviewId: Int): List<ReviewCommentResponse>? {
        return reviewService.getReviewComment(reviewId)
    }

    // 리뷰 댓글 생성
    @PostMapping("/comment")
    fun createReviewComment(
        @RequestBody request: ReviewCommentRequest,
    ): Boolean {
        return reviewService.createReviewComment(request)
    }

    // 리뷰 댓글 수정
    @PutMapping("/comment")
    fun modifyReviewComment(
        @RequestBody request: ReviewCommentModifyRequest,
    ): Boolean {
        return reviewService.modifyReviewComment(request)
    }

    @DeleteMapping("/comment/{reviewCommentId}")
    fun deleteReviewComment(
        @PathVariable reviewCommentId: Int,
    ): Boolean {
        return reviewService.deleteReviewComment(reviewCommentId)
    }

    // 리뷰 신고하기
    @PutMapping("/report/{reviewId}")
    fun reportReview(@PathVariable reviewId: Int): Boolean {
        return reviewService.reportReview(reviewId)
    }

    // 리뷰 댓글 신고하기
    @PutMapping("/comment/report/{reviewCommentId}")
    fun reportReviewComment(@PathVariable reviewCommentId: Int): Boolean {
        return reviewService.reportReviewComment(reviewCommentId)
    }
}
