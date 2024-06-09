package org.lucky.letter.service

import org.lucky.letter.model.request.*
import org.lucky.letter.model.response.UserDuplicateCheckResponse
import org.lucky.letter.model.response.UserResponse
import org.lucky.letter.model.response.toResponse
import org.lucky.letter.repository.UserRepository
import org.springframework.stereotype.Service
import java.security.InvalidParameterException
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun getUser(userId: Int): UserResponse {
        return userRepository.findUserByIdAndIsDeleted(userId)?.toResponse() ?: throw InvalidParameterException()
    }

    fun modifyUser(request: UserInfoRequest): UserResponse {
        return userRepository.findUserByIdAndIsDeleted(request.userId)?.let { user ->
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

    fun login(request: UserLoginRequest): UserResponse {
        return userRepository.findByEmailAndIsDeleted(email = request.email)?.let {
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

    fun withdrawUser(request: UserInfoRequest): Boolean {
        return userRepository.findUserByIdAndIsDeleted(request.userId)?.let { user ->
            if (user.password == request.password) {
                user.apply {
                    isDeleted = true
                    deletedAt = LocalDateTime.now()
                }.run {
                    userRepository.save(this)
                    true
                }
            } else {
                throw InvalidParameterException()
            }
        } ?: throw InvalidParameterException()
    }

    fun checkEmailAndNickname(request: UserDuplicateCheckRequest): UserDuplicateCheckResponse {
        val response = UserDuplicateCheckResponse()

        return response.apply {
            email = request.email?.let { userRepository.findByEmailAndIsDeleted(it)?.let { false } ?: true }
            nickname = request.nickname?.let { userRepository.findByNicknameAndIsDeleted(it)?.let { false } ?: true }
        }
    }
}
