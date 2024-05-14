package org.lucky.letter.model.request

import org.lucky.letter.entity.User

data class UserRequest(
    val name: String
)

fun UserRequest.toEntity() = User(
    name = name
)