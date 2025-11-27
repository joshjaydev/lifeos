package com.lifeos.app.data.local

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromInstant(value: Instant?): Long? = value?.toEpochMilliseconds()

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let { Instant.fromEpochMilliseconds(it) }

    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? = value?.toString()

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }

    @TypeConverter
    fun fromLocalTime(value: LocalTime?): String? = 
        value?.let { "${it.hour.toString().padStart(2, '0')}:${it.minute.toString().padStart(2, '0')}" }

    @TypeConverter
    fun toLocalTime(value: String?): LocalTime? = value?.let { LocalTime.parse(it) }

    @TypeConverter
    fun fromMap(value: Map<String, Any>?): String? = value?.let { json.encodeToString(it) }

    @TypeConverter
    fun toMap(value: String?): Map<String, Any>? = value?.let {
        json.decodeFromString<Map<String, kotlinx.serialization.json.JsonElement>>(it)
            .mapValues { entry -> entry.value.toString() }
    }
}
