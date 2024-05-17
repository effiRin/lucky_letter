package org.lucky.letter.repository

import org.lucky.letter.entity.Choice
import org.springframework.data.repository.CrudRepository

interface ChoiceRepository : CrudRepository<Choice, Int> {

    fun findChoicesByQuestionId(questionId: Int): List<Choice>
}
