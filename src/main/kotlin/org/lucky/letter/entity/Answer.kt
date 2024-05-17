package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity

@Table(name = "`answer`")
@Entity
data class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val userId: Int,
    val questionId: Int,
    val choiceId: Int,
    val reason: String? = null,
) : BaseEntity()
