package com.espartanhack.pokedex20.core.domain.utils.extensions

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Convert the [ZonedDateTime] to the UTC milliseconds.
 *
 * @return The UTC milliseconds.
 */
fun ZonedDateTime.toUTCMillis(): Long = toInstant().toEpochMilli()

/**
 * Create a [ZonedDateTime] from the UTC milliseconds.
 *
 * @param millis The UTC milliseconds.
 *
 * @return The [ZonedDateTime].
 */
fun zonedDateTimeFromUTCMillis(millis: Long): ZonedDateTime =
    ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())