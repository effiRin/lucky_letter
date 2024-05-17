package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity

@Table(name = "review")
@Entity
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val questionId: Int,
    val content: String,
) : BaseEntity()

