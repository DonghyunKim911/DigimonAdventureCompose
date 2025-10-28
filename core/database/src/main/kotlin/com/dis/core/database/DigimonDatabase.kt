package com.dis.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dis.core.database.converter.AttributeListTypeConverter
import com.dis.core.database.converter.DescriptionListTypeConverter
import com.dis.core.database.converter.FieldListTypeConverter
import com.dis.core.database.converter.ImageListTypeConverter
import com.dis.core.database.converter.LevelListTypeConverter
import com.dis.core.database.converter.NextEvolutionListTypeConverter
import com.dis.core.database.converter.PriorEvolutionListTypeConverter
import com.dis.core.database.converter.SkillListTypeConverter
import com.dis.core.database.converter.TypeListTypeConverter
import com.dis.core.database.entity.ContentEntity
import com.dis.core.database.entity.DigimonEntity
import com.dis.core.database.entity.FavoriteEntity

@Database(entities = [ContentEntity::class, DigimonEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    value = [
        AttributeListTypeConverter::class,
        DescriptionListTypeConverter::class,
        FieldListTypeConverter::class,
        ImageListTypeConverter::class,
        LevelListTypeConverter::class,
        NextEvolutionListTypeConverter::class,
        PriorEvolutionListTypeConverter::class,
        SkillListTypeConverter::class,
        TypeListTypeConverter::class,
    ],
)
abstract class DigimonDatabase : RoomDatabase() {
    abstract fun digimonDao(): DigimonDao
}
