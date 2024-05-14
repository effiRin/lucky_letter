package org.lucky.letter.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.lucky.letter.common.BaseEntity

@Table(name = "`user`")
@Entity
class User(
    @Id
    @GeneratedValue
    val id: Long? = null,
//    val provider: String,
//    val email: String,
//    private val password: String,
    val name: String,
//    val nickname: String? = null,
//    val profileImage: String,
) : BaseEntity()
