package org.lucky.letter.model.response

import java.time.LocalDateTime

data class QuestionReceivedResponse(
    val questions: List<QuestionReceivedListResponse>,
    val rewardCount: Int? = 0,
)

data class QuestionReceivedListResponse(
    val questionId: Int,
    val title: String? = null,
    val content: String? = null,
    val choices: List<ChoiceResponse>,
    val closedAt: LocalDateTime? = null,
) 
