package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity

@Table(name = "review_comment")
@Entity
data class ReviewComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val reviewId: Int,
    val userId: Int,
    var content: String,
    var isReported: Boolean = false,
//    var isDeleted: Boolean = false,
//    var deletedAt: LocalDateTime? = null,
) : BaseEntity()
