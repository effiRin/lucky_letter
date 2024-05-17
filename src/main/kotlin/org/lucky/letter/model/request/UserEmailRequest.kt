package org.lucky.letter.model.request

data class UserEmailRequest(
    val email: String,
    val password: String,
)
