package com.dis.data.impl

import com.dis.core.domain.model.Content
import com.dis.core.domain.model.Digimon
import com.dis.core.domain.model.Favorite
import com.dis.data.local.DigimonLocalDataSource
import com.dis.data.mapper.toData
import com.dis.data.mapper.toDomain
import com.dis.data.remote.DigimonRemoteDataSource
import com.dis.domain.digimon.repository.DigimonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DigimonRepositoryImpl @Inject constructor(
    private val remote: DigimonRemoteDataSource,
    private val local: DigimonLocalDataSource,
): DigimonRepository {

    override fun getDigimonList(page: Int): Flow<List<Content>> = flow {
            val digimons = local.getDigimonList(page)
            if (digimons.isEmpty()) {
                val response = remote.getDigimonList(page = page, pageSize = DEFAULT_PAGE_SIZE)
                response.contents?.let { result ->
                    local.insertDigimonContents(contents = result.filterNotNull(), page = page)
                    emit(local.getDigimonList(page).map { it.toDomain() })
                } ?: kotlin.run {
                    emit(emptyList())
                }
            } else {
                emit(digimons.map { it.toDomain() })
            }
        }

    override suspend fun getDigimonDetail(id: Int): Digimon? {
        val digimon = local.getDigimonDetail(id)?.toDomain()
        val favorite = local.fetchFavoriteDigimon(id)
        val isFavorite = if (favorite != null) favorite.id == id else false

        return if (digimon == null) {
            val response = remote.getDigimonDetail(id)
            local.saveDigimonDetail(response)
            response.toDomain().copy(isFavorite = isFavorite)
        } else {
            digimon.copy(isFavorite = isFavorite)
        }
    }

    override suspend fun saveFavoriteDigimon(favorite: Favorite) {
        local.saveFavorite(favorite.toData())
    }

    override suspend fun deleteFavoriteDigimon(favorite: Favorite) {
        local.deleteFavoriteDigimon(favorite.toData())
    }

    companion object {

        const val DEFAULT_PAGE_SIZE = 20

    }

}
