package org.lucky.letter.model.response

import org.lucky.letter.entity.User

data class UserResponse(
    val id: Int,
    val email: String,
    val nickname: String,
    val rewardCount: Int,
    val profileImage: String? = null
)

fun User.toResponse() = UserResponse(
    id = id!!,
    email = email,
    nickname = nickname,
    rewardCount = rewardCount,
    profileImage = profileImage
)
