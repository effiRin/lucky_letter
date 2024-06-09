package org.lucky.letter.controller

import org.lucky.letter.model.request.ReviewCommentRequest
import org.lucky.letter.model.request.ReviewRequest
import org.lucky.letter.model.response.ReviewCommentResponse
import org.lucky.letter.model.response.ReviewDetailResponse
import org.lucky.letter.model.response.ReviewResponse
import org.lucky.letter.service.ReviewService
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

    // 리뷰 댓글 생성(수정 x)
    @PostMapping("/comment")
    fun saveReviewComment(
        @RequestBody request: ReviewCommentRequest,
    ): Boolean {
        return reviewService.saveComment(request)
    }
}
