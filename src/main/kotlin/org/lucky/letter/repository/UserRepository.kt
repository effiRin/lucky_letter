package org.lucky.letter.repository

import org.lucky.letter.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {

    fun findUserById(id: Int): User?

    fun findByEmail(email: String,): User?
}
