package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity
import java.time.LocalDateTime

@Table(name = "`user`")
@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val email: String,
    var password: String,
    var nickname: String,
    var rewardCount: Int = 10,
    val categoryId: String? = null,
    val profileImage: String? = null,
    var isDeleted: Boolean? = false,
    var deletedAt: LocalDateTime? = null,
) : BaseEntity()
