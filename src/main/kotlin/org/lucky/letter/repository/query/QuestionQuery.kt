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

    const val getQuestions = """
        SELECT
            q.id as questionId,
            q.title as title,
            q.content as questionContent,
            q.closed_at as closedAt,
            c.id as choiceId,
            c.content as choiceContent
        FROM
            question q
        INNER JOIN
            choice c ON c.question_id = q.id
        
        
        WHERE
            q.id NOT IN (SELECT question_id FROM answer WHERE user_id = :userId)                    
    """
}