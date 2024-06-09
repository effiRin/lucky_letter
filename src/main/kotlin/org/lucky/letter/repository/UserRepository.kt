package org.lucky.letter.repository

import org.lucky.letter.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {

    fun findUserById(id: Int): User?

    fun findUserByIdAndIsDeleted(id: Int, isDeleted: Boolean = false): User?

    fun findByEmail(email: String): User?

    fun findByEmailAndIsDeleted(email: String, isDeleted: Boolean = false): User?

    fun findByNicknameAndIsDeleted(nickname: String, isDeleted: Boolean = false): User?
}
