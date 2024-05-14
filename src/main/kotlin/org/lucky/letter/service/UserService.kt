package org.lucky.letter.service

import org.lucky.letter.model.request.UserRequest
import org.lucky.letter.model.request.toEntity
import org.lucky.letter.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun getUsers() = userRepository.findAll()

    fun saveUser(request: UserRequest) = userRepository.save(request.toEntity())
}
