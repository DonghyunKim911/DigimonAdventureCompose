package com.dis.core.database.di

import android.content.Context
import androidx.room.Room
import com.dis.core.database.DigimonDatabase
import com.dis.core.database.converter.AttributeListTypeConverter
import com.dis.core.database.converter.DescriptionListTypeConverter
import com.dis.core.database.converter.FieldListTypeConverter
import com.dis.core.database.converter.ImageListTypeConverter
import com.dis.core.database.converter.LevelListTypeConverter
import com.dis.core.database.converter.NextEvolutionListTypeConverter
import com.dis.core.database.converter.PriorEvolutionListTypeConverter
import com.dis.core.database.converter.SkillListTypeConverter
import com.dis.core.database.converter.TypeListTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

private const val DB_NAME = "digimon.db"

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DigimonTypeConverters

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDigimonDatabase(
        @ApplicationContext context: Context,
        attributeListTypeConverter: AttributeListTypeConverter,
        descriptionListTypeConverter: DescriptionListTypeConverter,
        fieldListTypeConverter: FieldListTypeConverter,
        imageListTypeConverter: ImageListTypeConverter,
        levelListTypeConverter: LevelListTypeConverter,
        nextEvolutionListTypeConverter: NextEvolutionListTypeConverter,
        priorEvolutionListTypeConverter: PriorEvolutionListTypeConverter,
        skillListTypeConverter: SkillListTypeConverter,
        typeListTypeConverter: TypeListTypeConverter
    ): DigimonDatabase = Room
        .databaseBuilder(context, DigimonDatabase::class.java,DB_NAME)
//        .addTypeConverter(attributeListTypeConverter)
//        .addTypeConverter(descriptionListTypeConverter)
//        .addTypeConverter(fieldListTypeConverter)
//        .addTypeConverter(imageListTypeConverter)
//        .addTypeConverter(levelListTypeConverter)
//        .addTypeConverter(nextEvolutionListTypeConverter)
//        .addTypeConverter(priorEvolutionListTypeConverter)
//        .addTypeConverter(skillListTypeConverter)
//        .addTypeConverter(typeListTypeConverter)
        .build()

    @Provides
    @Singleton
    fun provideAttributeListTypeConverter(): AttributeListTypeConverter = AttributeListTypeConverter()

    @Provides
    @Singleton
    fun provideDescriptionListTypeConverter(): DescriptionListTypeConverter = DescriptionListTypeConverter()

    @Provides
    @Singleton
    fun provideFieldListTypeConverter(): FieldListTypeConverter = FieldListTypeConverter()

    @Provides
    @Singleton
    fun provideImageListTypeConverter(): ImageListTypeConverter = ImageListTypeConverter()

    @Provides
    @Singleton
    fun provideLevelListTypeConverter(): LevelListTypeConverter = LevelListTypeConverter()

    @Provides
    @Singleton
    fun provideNextEvolutionListTypeConverter(): NextEvolutionListTypeConverter = NextEvolutionListTypeConverter()

    @Provides
    @Singleton
    fun providePriorEvolutionListTypeConverter(): PriorEvolutionListTypeConverter = PriorEvolutionListTypeConverter()

    @Provides
    @Singleton
    fun provideSkillListListTypeConverter(): SkillListTypeConverter = SkillListTypeConverter()

    @Provides
    @Singleton
    fun provideTypeListTypeConverter(): TypeListTypeConverter = TypeListTypeConverter()

}
