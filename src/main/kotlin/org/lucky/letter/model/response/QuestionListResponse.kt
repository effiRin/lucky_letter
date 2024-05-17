package org.lucky.letter.model.response

import org.lucky.letter.entity.Choice
import org.lucky.letter.model.dto.AnswerCountResult
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime

data class QuestionListResponse(
    val questionId: Int,
    val title: String? = null,
    val content: String,
    val choices: List<ChoiceListResponse>,
    val answerCount: Int,
    val createdAt: LocalDateTime,
    val userNickname: String? = "",
)

data class ChoiceListResponse(
    val id: Int,
    val content: String,
    val percent: BigDecimal,
)

fun Choice.toChoiceListResponse(answerCountResults: AnswerCountResult?, answerCount: Int): ChoiceListResponse {
    val standard = BigDecimal("100")

    return ChoiceListResponse(
        id = id!!,
        content = content,
        percent = (
            answerCountResults?.cnt?.toBigDecimal()
                ?.divide(answerCount.toBigDecimal(), 3, RoundingMode.HALF_UP)
            )?.times(standard) ?: BigDecimal(0),
    )
}
