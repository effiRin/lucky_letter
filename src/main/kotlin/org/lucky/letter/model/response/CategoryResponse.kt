package org.lucky.letter.model.response

import org.lucky.letter.entity.Category

data class CategoryResponse(
    val id: Int,
    val title: String,
    val content: String,
)

fun Category.toResponse() = CategoryResponse(
    id = id!!,
    title = title,
    content = content ?: "",
)
