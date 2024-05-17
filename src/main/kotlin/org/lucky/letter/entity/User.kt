package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity

@Table(name = "`user`")
@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val email: String,
    val password: String,
    val nickname: String,
    var rewardCount: Int = 10,
    val categoryId: String? = null,
    val profileImage: String? = null,
) : BaseEntity()
