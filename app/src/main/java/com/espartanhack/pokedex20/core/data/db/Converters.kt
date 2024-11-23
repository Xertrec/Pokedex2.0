package com.espartanhack.pokedex20.core.data.db

import androidx.room.TypeConverter
import com.espartanhack.pokedex20.core.domain.utils.extensions.toUTCMillis
import com.espartanhack.pokedex20.core.domain.utils.extensions.zonedDateTimeFromUTCMillis
import java.time.ZonedDateTime

class Converters {

    @TypeConverter
    fun fromZonedDateTime(localDateTime: ZonedDateTime): Long = localDateTime.toUTCMillis()

    @TypeConverter
    fun toZonedDateTime(epochMillis: Long): ZonedDateTime =
        zonedDateTimeFromUTCMillis(millis = epochMillis)
}