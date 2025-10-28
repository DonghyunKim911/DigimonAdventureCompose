package com.dis.local.digimon.impl

import androidx.room.withTransaction
import com.dis.core.database.DigimonDatabase
import com.dis.data.local.DigimonLocalDataSource
import com.dis.data.model.ContentData
import com.dis.data.model.DigimonData
import com.dis.data.model.FavoriteData
import com.dis.local.digimon.mapper.toData
import com.dis.local.digimon.mapper.toLocal
import javax.inject.Inject

class DigimonLocalDataSourceImpl
    @Inject
    constructor(
        private val db: DigimonDatabase,
    ) : DigimonLocalDataSource {
        override suspend fun insertDigimonContents(
            contents: List<ContentData>,
            page: Int,
        ) {
            db.withTransaction {
                val dao = db.digimonDao()
//            if (loadType == LoadType.REFRESH) {
//                dao.clearAll()
//            }
                val contentEntities = contents.map { it.toLocal(page) }
                dao.upsertDigimonList(contentEntities)
            }
        }

        override suspend fun saveDigimonDetail(digimon: DigimonData) {
            val dao = db.digimonDao()
            dao.upsertDigimon(digimon.toLocal())
        }

        override suspend fun getDigimonList(page: Int): List<ContentData> {
            val dao = db.digimonDao()
            return dao.getDigimonList(page).map { it.toData() }
        }

        override suspend fun getDigimonDetail(id: Int): DigimonData? {
            val dao = db.digimonDao()
            return dao.getDigimon(id)?.toData()
        }

        override suspend fun saveFavorite(favorite: FavoriteData) {
            val dao = db.digimonDao()
            dao.saveFavorite(favorite.toLocal())
        }

        override suspend fun fetchFavoriteDigimon(id: Int): FavoriteData? {
            val dao = db.digimonDao()
            return dao.getFavorite(id)?.toData()
        }

        override suspend fun deleteFavoriteDigimon(favorite: FavoriteData) {
            val dao = db.digimonDao()
            dao.deleteFavoriteDigimon(favorite.toLocal())
        }
    }
