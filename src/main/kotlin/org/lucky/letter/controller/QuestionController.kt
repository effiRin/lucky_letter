package org.lucky.letter.controller

import org.lucky.letter.model.request.QuestionRequest
import org.lucky.letter.model.response.QuestionListResponse
import org.lucky.letter.model.response.QuestionReceivedResponse
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

    // 받은 질문 상세 조회
    @GetMapping("")
    fun getQuestion(userId: Int): QuestionResponse? {
        return questionService.getQuestion(userId = userId)
    }

    // 받은 질문 목록
    @GetMapping("/list/receive")
    fun getQuestions(userId: Int): QuestionReceivedResponse {
        return questionService.getQuestions(userId = userId)
    }

    // 질문 생성
    @PostMapping("")
    fun createQuestion(@RequestBody request: QuestionRequest): QuestionResponse {
        return questionService.createQuestion(request = request)
    }

    // 전체 질문 목록
    @GetMapping("/list/send")
    fun getQuestionList(userId: Int, sorter: String?, isMine: Boolean?): List<QuestionListResponse> {
        return questionService.getQuestionList(
            userId = userId,
            sorter = sorter ?: "recent",
            myAnswer = isMine ?: false,
            myQuestion = false,
        )
    }

    // 내가 쓴 질문
    @GetMapping("/list/my")
    fun getMyQuestion(userId: Int): List<QuestionListResponse> {
        return questionService.getMyQuestion(userId = userId)
    }
}
