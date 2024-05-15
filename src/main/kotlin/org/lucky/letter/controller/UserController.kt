package org.lucky.letter.controller

import org.lucky.letter.entity.User
import org.lucky.letter.model.request.UserRequest
import org.lucky.letter.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/user")
class UserController(
    private val userService: UserService,
) {

    //    @Schema(description = "테스트 test 용")
    @GetMapping("/test/get")
    fun getUsers(): List<User> {
        println("LOGGING ::: GET TEST")
        return userService.getUsers()
    }

    @PostMapping("/test/save")
    fun saveUsers(
        @RequestBody userRequest: UserRequest,
    ): User {
        println("LOGGING ::: SAVE TEST")
        return userService.saveUser(userRequest)
    }
}
