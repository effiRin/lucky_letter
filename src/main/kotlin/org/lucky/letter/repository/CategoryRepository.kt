package org.lucky.letter.repository

import org.lucky.letter.entity.Category
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Int>
