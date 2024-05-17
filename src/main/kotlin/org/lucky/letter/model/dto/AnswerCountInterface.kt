package org.lucky.letter.model.dto

interface AnswerCountInterface {

    fun getQuestionId(): Int
    fun getChoiceId(): Int
    fun getCnt(): Int
}
