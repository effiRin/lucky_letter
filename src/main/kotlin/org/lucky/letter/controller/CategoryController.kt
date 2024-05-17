package org.lucky.letter.controller

import org.lucky.letter.model.request.AnswerRequest
import org.lucky.letter.model.response.AnswerResponse
import org.lucky.letter.model.response.CategoryResponse
import org.lucky.letter.service.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/category")
class CategoryController(
    private val categoryService: CategoryService
) {

    @GetMapping("")
    fun getAll(): List<CategoryResponse> {
        return categoryService.getAll()
    }

}