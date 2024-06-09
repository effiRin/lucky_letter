package org.lucky.letter.model.request

data class UserModifyRequest(
    val userId: Int,
    val password: String? = null,
    val newPassword: String? = null,
    val nickname: String? = null,
//    val categoryId: String? = null,
)
