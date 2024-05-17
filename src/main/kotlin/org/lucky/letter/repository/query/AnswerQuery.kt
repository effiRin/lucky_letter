package org.lucky.letter.repository.query

object AnswerQuery {

    const val findQuestionIdOrderByPopularity = """
            SELECT 
                question_id AS questionId,
                choice_id AS choiceId, 
                count(*) AS cnt
            FROM answer
            GROUP BY questionId, choiceId
            ORDER BY cnt desc
    """

    const val findQuestionIdOrderByIdDesc = """
            SELECT 
                question_id AS questionId, 
                choice_id AS choiceId, 
                count(*) AS cnt
            FROM answer
            GROUP BY questionId, choiceId
            ORDER BY id DESC
    """
}