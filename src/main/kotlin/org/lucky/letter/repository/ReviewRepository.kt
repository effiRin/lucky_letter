package org.lucky.letter.repository

import org.lucky.letter.entity.Review
import org.springframework.data.repository.CrudRepository

interface ReviewRepository : CrudRepository<Review, Int>
