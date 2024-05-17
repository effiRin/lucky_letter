package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity

@Table(name = "choice")
@Entity
data class Choice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val questionId: Int,
    val content: String,
) : BaseEntity()
