package com.dis.data.di

import com.dis.data.impl.DigimonRepositoryImpl
import com.dis.domain.digimon.repository.DigimonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDigimonRepository(digimonRepositoryImpl: DigimonRepositoryImpl): DigimonRepository

}
