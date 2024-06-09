package org.lucky.letter.controller

import org.lucky.letter.model.request.UserEmailRequest
import org.lucky.letter.model.request.UserInfoRequest
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

    @PutMapping("")
    fun modifyUser(
        @RequestBody request: UserInfoRequest,
    ): UserResponse {
        return userService.modifyUser(request = request)
    }

    @PutMapping("/login")
    fun login(@RequestBody request: UserEmailRequest): UserResponse {
        return userService.login(request = request)
    }

    @PostMapping("/join")
    fun joinUser(
        @RequestBody userRequest: UserRequest,
    ): UserResponse {
        return userService.saveUser(userRequest)
    }

    @DeleteMapping("/withdraw")
    fun withdrawUser(
        @RequestBody request: UserInfoRequest,
    ): Boolean {
        return userService.withdrawUser(request = request)
    }
}
