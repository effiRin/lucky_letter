package org.lucky.letter.model.request

data class UserDuplicateCheckRequest(
    val email: String? = null,
    val nickname: String? = null,
)
