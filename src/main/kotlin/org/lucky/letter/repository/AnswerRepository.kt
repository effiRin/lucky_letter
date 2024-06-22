package org.lucky.letter.repository

import org.lucky.letter.entity.Answer
import org.lucky.letter.model.dto.AnswerCountInterface
import org.lucky.letter.repository.query.AnswerQuery
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface AnswerRepository : CrudRepository<Answer, Int> {

    @Query(nativeQuery = true, value = AnswerQuery.findQuestionIdOrderByPopularity)
    fun findQuestionIdOrderByPopularity(): List<AnswerCountInterface>

    @Query(nativeQuery = true, value = AnswerQuery.findQuestionIdOrderByIdDesc)
    fun findQuestionIdOrderByIdDesc(): List<AnswerCountInterface>

    @Query(nativeQuery = true, value = AnswerQuery.findQuestionIdByUserId)
    fun findQuestionIdByUserId(userId: Int): List<AnswerCountInterface>

    fun findAnswersByUserId(userId: Int): List<Answer>
    fun findAnswersByUserIdAndQuestionIdIn(userId: Int, questionId: List<Int>): List<Answer>

    fun findAnswersByUserIdAndQuestionId(userId: Int, questionId: Int): List<Answer>

    fun findAnswersByQuestionIdIn(questionId: List<Int>): List<Answer>

    @Query(nativeQuery = true, value = AnswerQuery.findQuestionAnswer)
    fun findQuestionAnswer(questionId: Int): List<AnswerCountInterface>
}
