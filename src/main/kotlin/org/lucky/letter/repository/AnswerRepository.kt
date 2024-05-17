package org.lucky.letter.repository

import org.lucky.letter.entity.Answer
import org.springframework.data.repository.CrudRepository

interface AnswerRepository : CrudRepository<Answer, Int>
