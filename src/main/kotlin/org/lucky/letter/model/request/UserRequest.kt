package org.lucky.letter.model.request

import org.lucky.letter.entity.User

data class UserRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val categoryId: String? = null,
//    val profileImage: FilePart,
)

fun UserRequest.toEntity() = User(
    email = email,
    password = password,
    nickname = nickname,
    categoryId = categoryId,
)