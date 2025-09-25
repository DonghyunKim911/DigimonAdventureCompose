package com.dis.data.impl

import com.dis.data.local.DigimonLocalDataSource
import com.dis.data.mapper.toDomain
import com.dis.data.remote.DigimonRemoteDataSource
import com.dis.domain.digimon.model.Content
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

    companion object {

        const val DEFAULT_PAGE_SIZE = 20

    }

}
