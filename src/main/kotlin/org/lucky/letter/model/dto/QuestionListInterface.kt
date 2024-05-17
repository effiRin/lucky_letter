package org.lucky.letter.model.dto

interface QuestionListInterface {

    fun getQuestionId(): Int
    fun getTitle(): String

    fun getQuestionContent(): String

    fun getClosedAt(): Long

    fun getChoiceId(): Int

    fun getChoiceContent(): String
}
