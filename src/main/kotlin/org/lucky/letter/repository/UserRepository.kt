package org.lucky.letter.repository

import org.lucky.letter.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findUserById(id: Long): User?
}