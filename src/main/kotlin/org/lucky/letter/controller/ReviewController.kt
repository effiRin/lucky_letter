package org.lucky.letter.controller

import org.lucky.letter.model.request.ReviewRequest
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
}
