package org.lucky.letter.repository.query

object ReviewQuery {

    const val findReview = """
        SELECT
            r.id as reviewId,
            r.question_id as questionId,
            r.title,            
            r.content,
            r.view_count as viewCount,
            u.nickname
        FROM
            review r
        INNER JOIN
            question q ON q.id = r.question_id
        INNER JOIN
            user u ON u.id = q.user_id
        WHERE
            r.is_deleted = 0
        AND
            r.id = :reviewId
    """

}