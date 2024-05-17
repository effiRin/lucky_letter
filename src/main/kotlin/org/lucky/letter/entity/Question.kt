package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity

@Table(name = "question")
@Entity
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val userId: Int,
    val title: String? = null,
    val content: String,
    val categoryId: String? = null,
) : BaseEntity()
