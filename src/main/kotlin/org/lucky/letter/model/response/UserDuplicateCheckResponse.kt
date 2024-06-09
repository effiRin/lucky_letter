package org.lucky.letter.model.response

data class UserDuplicateCheckResponse(
    var email: Boolean? = null,
    var nickname: Boolean? = null,
)
