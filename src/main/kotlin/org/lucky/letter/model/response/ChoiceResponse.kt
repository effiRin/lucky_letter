package org.lucky.letter.model.response

import org.lucky.letter.entity.Choice

data class ChoiceResponse(
    val id: Int,
    val content: String,
)

fun Choice.toResponse() = ChoiceResponse(
    id = id!!,
    content = content
)