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

    const val findReviewComments = """
        SELECT
            rc.id as reviewCommentId,
            rc.content,
            rc.is_reported as isReported,
            rc.created_at as createdAt,
            u.nickname
        FROM
            review_comment rc
        INNER JOIN
            user u ON u.id = rc.user_id
        WHERE
            rc.review_id = :reviewId
    """
}