package com.dis.data.local

import com.dis.core.domain.model.Favorite
import com.dis.data.model.ContentData
import com.dis.data.model.DigimonData
import com.dis.data.model.FavoriteData
import kotlinx.coroutines.flow.Flow

interface DigimonLocalDataSource {
    suspend fun insertDigimonContents(
        contents: List<ContentData>,
        page: Int,
    )

    suspend fun saveDigimonDetail(digimon: DigimonData)

    suspend fun getDigimonList(page: Int): List<ContentData>

    suspend fun getDigimonDetail(id: Int): DigimonData?

    suspend fun saveFavorite(favorite: FavoriteData)

    suspend fun fetchFavoriteDigimon(id: Int): FavoriteData?

    suspend fun deleteFavoriteDigimon(favorite: FavoriteData)
}
