package org.lucky.letter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LuckyLetterApplication

fun main(args: Array<String>) {
	runApplication<LuckyLetterApplication>(*args)
}
