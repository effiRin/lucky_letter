package org.lucky.letter.model.response

import org.lucky.letter.common.DateUtil
import org.lucky.letter.entity.Question
import java.time.LocalDateTime

data class QuestionDetailResponse(
    val questionId: Int,
    val title: String? = null,
    val content: String,
    val closedAt: LocalDateTime,
)

fun Question.toQuestionDetailResponse() = QuestionDetailResponse(
    questionId = id!!,
    title = title,
    content = content,
    closedAt = DateUtil.convertLocalDateTime(closedAt),
)
