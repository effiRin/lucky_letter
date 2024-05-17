package org.lucky.letter.service

import org.lucky.letter.model.request.UserRequest
import org.lucky.letter.model.request.toEntity
import org.lucky.letter.model.response.UserResponse
import org.lucky.letter.model.response.toResponse
import org.lucky.letter.repository.UserRepository
import org.springframework.stereotype.Service
import java.security.InvalidParameterException

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getUsers() = userRepository.findAll()

    fun getUser(userId: Int): UserResponse {
        return userRepository.findUserById(userId)?.let {
            it.toResponse()
        } ?: throw InvalidParameterException()
    }

    fun saveUser(request: UserRequest): UserResponse {
        return userRepository.findByEmail(email = request.email)?.let {
            throw InvalidParameterException()
        } ?: run {
            userRepository.save(request.toEntity()).toResponse()
        }
    }
}
