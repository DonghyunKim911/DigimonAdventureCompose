package com.dis.core.database.converter

import androidx.room.ProvidedTypeConverter
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

    @TypeConverter fun fromJson(value: String?): List<AttributeEntity?>? =
        value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    @TypeConverter fun toJson(list: List<AttributeEntity?>?): String? =
        list?.let { Json.encodeToString(it) }
}

@ProvidedTypeConverter
class DescriptionListTypeConverter {

    @TypeConverter
    fun fromJson(value: String?): List<DescriptionEntity?>? {
        return value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    }

    @TypeConverter
    fun toJson(type: List<DescriptionEntity?>?): String? {
        return type?.let { Json.encodeToString(it) }
    }
}

class FieldListTypeConverter {

    @TypeConverter
    fun fromJson(value: String?): List<FieldEntity?>? {
        return value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    }

    @TypeConverter
    fun toJson(type: List<FieldEntity?>?): String? {
        return type?.let { Json.encodeToString(it) }
    }

}

class ImageListTypeConverter {

    @TypeConverter
    fun fromJson(value: String?): List<ImageEntity?>? {
        return value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    }

    @TypeConverter
    fun toJson(type: List<ImageEntity?>?): String? {
        return type?.let { Json.encodeToString(it) }
    }

}

class LevelListTypeConverter {

    @TypeConverter
    fun fromJson(value: String?): List<LevelEntity?>? {
        return value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    }

    @TypeConverter
    fun toJson(type: List<LevelEntity?>?): String? {
        return type?.let { Json.encodeToString(it) }
    }

}

class NextEvolutionListTypeConverter {

    @TypeConverter
    fun fromJson(value: String?): List<NextEvolutionEntity?>? {
        return value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    }

    @TypeConverter
    fun toJson(type: List<NextEvolutionEntity?>?): String? {
        return type?.let { Json.encodeToString(it) }
    }

}

class PriorEvolutionListTypeConverter {

    @TypeConverter
    fun fromJson(value: String?): List<PriorEvolutionEntity?>? {
        return value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    }

    @TypeConverter
    fun toJson(type: List<PriorEvolutionEntity?>?): String? {
        return type?.let { Json.encodeToString(it) }
    }
}

class SkillListTypeConverter {

    @TypeConverter
    fun fromJson(value: String?): List<SkillEntity?>? {
        return value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    }

    @TypeConverter
    fun toJson(type: List<SkillEntity?>?): String? {
        return type?.let { Json.encodeToString(it) }
    }
}

class TypeListTypeConverter {

    @TypeConverter
    fun fromJson(value: String?): List<TypeEntity?>? {
        return value?.let { Json { ignoreUnknownKeys = true }.decodeFromString(it) }
    }

    @TypeConverter
    fun toJson(type: List<TypeEntity?>?): String? {
        return type?.let { Json.encodeToString(it) }
    }
}

