package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity

@Table(name = "`category`")
@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val title: String,
    val content: String? = "",
) : BaseEntity()
