package org.lucky.letter.repository

import org.lucky.letter.entity.Question
import org.lucky.letter.repository.query.QuestionQuery
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface QuestionRepository : CrudRepository<Question, Int> {

    @Query(nativeQuery = true, value = QuestionQuery.getQuestion)
    fun getQuestion(userId: Int): Question?
}
