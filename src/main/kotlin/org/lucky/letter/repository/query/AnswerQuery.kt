package org.lucky.letter.repository.query

object AnswerQuery {

    const val findQuestionIdOrderByPopularity = """
            SELECT 
                question_id AS questionId,
                choice_id AS choiceId, 
                count(*) AS cnt
            FROM answer
            WHERE choice_id != 0
            GROUP BY questionId, choiceId
            ORDER BY cnt desc
    """

    const val findQuestionIdOrderByIdDesc = """
            SELECT 
                question_id AS questionId, 
                choice_id AS choiceId, 
                count(*) AS cnt
            FROM answer
            WHERE choice_id != 0
            GROUP BY questionId, choiceId
            ORDER BY questionId DESC
    """

    const val findQuestionIdByUserId = """
            SELECT 
                question_id AS questionId, 
                choice_id AS choiceId, 
                count(*) AS cnt
            FROM answer
            WHERE choice_id != 0
            AND user_id = :userId
            GROUP BY questionId, choiceId
            ORDER BY questionId DESC
    """
}