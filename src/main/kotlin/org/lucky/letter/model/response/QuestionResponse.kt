package org.lucky.letter.model.response

import org.lucky.letter.common.DateUtil
import org.lucky.letter.entity.Question
import java.time.LocalDateTime

data class QuestionResponse(
    val questionId: Int,
    val title: String? = null,
    val content: String,
    val choices: List<ChoiceResponse>,
    val rewardCount: Int? = 0,
    val closedAt: LocalDateTime,
)

fun Question.toResponse(choices: List<ChoiceResponse>, rewardCount: Int) = QuestionResponse(
    questionId = id!!,
    title = title,
    content = content,
    choices = choices,
    rewardCount = rewardCount,
    closedAt = DateUtil.convertLocalDateTime(closedAt),
)


