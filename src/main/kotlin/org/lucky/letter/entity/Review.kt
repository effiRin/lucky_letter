package org.lucky.letter.entity

import jakarta.persistence.*
import org.lucky.letter.common.BaseEntity
import java.time.LocalDateTime

@Table(name = "review")
@Entity
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val questionId: Int,
    var title: String,
    var content: String,
    var viewCount: Long = 0,
    var isReported: Boolean = false,
    var isDeleted: Boolean = false,
    var deletedAt: LocalDateTime? = null,
) : BaseEntity()

fun Review.update(title: String, content: String): Review {
    this.title = title
    this.content = content

    return this
}

fun Review.delete(): Review {
    this.isDeleted = true
    this.deletedAt = LocalDateTime.now()

    return this
}
