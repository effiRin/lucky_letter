package org.lucky.letter.controller

import org.lucky.letter.model.request.AnswerRequest
import org.lucky.letter.model.response.AnswerResponse
import org.lucky.letter.service.AnswerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/answer")
class AnswerController(
    private val answerService: AnswerService,
) {

    // 답하기
    @PostMapping("")
    fun createAnswer(@RequestBody request: AnswerRequest): AnswerResponse {
        return answerService.createAnswer(request)
    }

    // 답하기
    @PostMapping("/ai")
    fun createAiAnswer(@RequestBody request: AnswerRequest): Boolean {
        return answerService.createAiAnswer(request)
    }
}
