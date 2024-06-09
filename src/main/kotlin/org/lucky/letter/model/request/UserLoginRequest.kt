package org.lucky.letter.model.request

data class UserLoginRequest(
    val email: String,
    val password: String,
)
