package com.dis.remote.digimon.di

import com.dis.remote.digimon.api.DigimonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteApiModule {

    @Provides
    @Singleton
    fun provideDigimonApi(retrofit: Retrofit): DigimonApi {
        return retrofit.create(DigimonApi::class.java)
    }

}