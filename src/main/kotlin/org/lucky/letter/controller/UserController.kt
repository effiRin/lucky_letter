package org.lucky.letter.controller

import org.lucky.letter.model.request.UserRequest
import org.lucky.letter.model.response.UserResponse
import org.lucky.letter.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/user")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("")
    fun getUser(userId: Int): UserResponse {
        return userService.getUser(userId = userId)
    }

    @PostMapping("")
    fun saveUser(
        @RequestBody userRequest: UserRequest,
    ): UserResponse {
        return userService.saveUser(userRequest)
    }
}
