package org.lucky.letter.model.dto

data class AnswerCountResult(
    val questionId: Int,
    val choiceId: Int,
    val cnt: Int,
)

fun AnswerCountInterface.toAnswerCountResult(): AnswerCountResult {
    return AnswerCountResult(
        questionId = getQuestionId(),
        choiceId = getChoiceId(),
        cnt = getCnt(),
    )
}