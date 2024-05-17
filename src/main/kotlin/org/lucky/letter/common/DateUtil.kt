package org.lucky.letter.common

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

object DateUtil {
    fun convertLocalDateTime(mills: Long): LocalDateTime =
        Instant.ofEpochMilli(mills).atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime()
}
