package com.dis.local.digimon.di

import com.dis.data.local.DigimonLocalDataSource
import com.dis.local.digimon.impl.DigimonLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DigimonLocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsDigimonLocalDataSource(digimonLocalDataSourceImpl: DigimonLocalDataSourceImpl): DigimonLocalDataSource

}