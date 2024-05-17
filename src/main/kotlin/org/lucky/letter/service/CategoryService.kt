package org.lucky.letter.service

import org.lucky.letter.model.response.CategoryResponse
import org.lucky.letter.model.response.toResponse
import org.lucky.letter.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
) {

    fun getAll(): List<CategoryResponse> {
        return categoryRepository.findAll().toList().map {
            it.toResponse()
        }
    }
}
