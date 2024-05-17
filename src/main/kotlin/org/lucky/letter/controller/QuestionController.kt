package org.lucky.letter.controller

import org.lucky.letter.model.request.QuestionRequest
import org.lucky.letter.model.response.QuestionResponse
import org.lucky.letter.service.QuestionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/question")
class QuestionController(
    private val questionService: QuestionService,
) {

    // 질문 상세 조회
    @GetMapping("")
    fun getQuestion(userId: Int): QuestionResponse? {
        return questionService.getQuestion(userId = userId)
    }

    // 질문 생성
    @PostMapping("")
    fun createQuestion(@RequestBody request: QuestionRequest): QuestionResponse {
        return questionService.createQuestion(request = request)
    }
}
