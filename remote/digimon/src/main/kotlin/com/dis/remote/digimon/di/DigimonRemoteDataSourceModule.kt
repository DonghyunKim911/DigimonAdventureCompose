package com.dis.remote.digimon.di

import com.dis.data.remote.DigimonRemoteDataSource
import com.dis.remote.digimon.impl.DigimonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DigimonRemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindDigimonRemoteDataSource(digimonRemoteDataSourceImpl: DigimonRemoteDataSourceImpl): DigimonRemoteDataSource
}
