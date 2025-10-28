package com.dis.core.database.converter

import androidx.room.TypeConverter
import com.dis.core.database.entity.AttributeEntity
import com.dis.core.database.entity.DescriptionEntity
import com.dis.core.database.entity.FieldEntity
import com.dis.core.database.entity.ImageEntity
import com.dis.core.database.entity.LevelEntity
import com.dis.core.database.entity.NextEvolutionEntity
import com.dis.core.database.entity.PriorEvolutionEntity
import com.dis.core.database.entity.SkillEntity
import com.dis.core.database.entity.TypeEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AttributeListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<AttributeEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(list: List<AttributeEntity?>?): String? = list?.let { Json.encodeToString(it) }
}

class DescriptionListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<DescriptionEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(type: List<DescriptionEntity?>?): String? = type?.let { Json.encodeToString(it) }
}

class FieldListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<FieldEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(type: List<FieldEntity?>?): String? = type?.let { Json.encodeToString(it) }
}

class ImageListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<ImageEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(type: List<ImageEntity?>?): String? = type?.let { Json.encodeToString(it) }
}

class LevelListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<LevelEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(type: List<LevelEntity?>?): String? = type?.let { Json.encodeToString(it) }
}

class NextEvolutionListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<NextEvolutionEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(type: List<NextEvolutionEntity?>?): String? = type?.let { Json.encodeToString(it) }
}

class PriorEvolutionListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<PriorEvolutionEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(type: List<PriorEvolutionEntity?>?): String? = type?.let { Json.encodeToString(it) }
}

class SkillListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<SkillEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(type: List<SkillEntity?>?): String? = type?.let { Json.encodeToString(it) }
}

class TypeListTypeConverter {
    @TypeConverter
    fun fromJson(value: String?): List<TypeEntity?>? = value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }

    @TypeConverter
    fun toJson(type: List<TypeEntity?>?): String? = type?.let { Json.encodeToString(it) }
}
