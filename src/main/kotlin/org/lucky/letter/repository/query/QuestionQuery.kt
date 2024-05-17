package org.lucky.letter.repository.query

object QuestionQuery {

    const val getQuestion = """
        SELECT
            *
        FROM
            question q
        WHERE
            q.id NOT IN (SELECT question_id FROM answer WHERE user_id = :userId)                    
        LIMIT 1
    """

}