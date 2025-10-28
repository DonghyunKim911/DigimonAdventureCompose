package com.dis.domain.digimon.repository

import com.dis.core.domain.model.Content
import com.dis.core.domain.model.Digimon
import com.dis.core.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface DigimonRepository {
    fun getDigimonList(page: Int): Flow<List<Content>>

    suspend fun getDigimonDetail(id: Int): Digimon?

    suspend fun saveFavoriteDigimon(favorite: Favorite)

    suspend fun deleteFavoriteDigimon(favorite: Favorite)
}
