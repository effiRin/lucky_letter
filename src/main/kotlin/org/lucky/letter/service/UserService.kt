package org.lucky.letter.service

import org.lucky.letter.model.request.UserEmailRequest
import org.lucky.letter.model.request.UserModifyRequest
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

    fun getUser(userId: Int): UserResponse {
        return userRepository.findUserById(userId)?.let {
            it.toResponse()
        } ?: throw InvalidParameterException()
    }

    fun modifyUser(request: UserModifyRequest): UserResponse {
        return userRepository.findUserById(request.userId)?.let { user ->
            val entity = user.apply {
                nickname = request.nickname ?: nickname

                request.password?.let {
                    if (user.password == request.password) {
                        password = request.newPassword ?: password
                    } else {
                        throw InvalidParameterException()
                    }
                }
            }

            userRepository.save(entity).toResponse()
        } ?: throw InvalidParameterException()
    }

    fun login(request: UserEmailRequest): UserResponse {
        return userRepository.findByEmail(email = request.email)?.let {
            if (it.password == request.password) {
                return it.toResponse()
            } else {
                throw InvalidParameterException()
            }
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
