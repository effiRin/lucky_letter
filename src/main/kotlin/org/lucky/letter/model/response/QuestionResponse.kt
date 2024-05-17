package org.lucky.letter.model.response

import org.lucky.letter.entity.Question

data class QuestionResponse(
    val questionId: Int,
    val title: String? = null,
    val content: String,
    val choices: List<ChoiceResponse>,
    val rewardCount: Int? = 0
)

fun Question.toResponse(choices: List<ChoiceResponse>, rewardCount: Int,) = QuestionResponse(
    questionId = id!!,
    title = title,
    content = content,
    choices = choices,
    rewardCount = rewardCount
)