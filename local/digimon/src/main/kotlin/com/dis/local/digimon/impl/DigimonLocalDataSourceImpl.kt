package com.dis.local.digimon.impl

import androidx.room.withTransaction
import com.dis.core.database.DigimonDatabase
import com.dis.data.local.DigimonLocalDataSource
import com.dis.data.model.ContentData
import com.dis.local.digimon.mapper.toData
import com.dis.local.digimon.mapper.toLocal
import javax.inject.Inject

class DigimonLocalDataSourceImpl @Inject constructor(
    private val db: DigimonDatabase,
): DigimonLocalDataSource {

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

    override suspend fun getDigimonList(page: Int): List<ContentData> {
        val dao = db.digimonDao()
        return dao.getDigimonList(page).map { it.toData() }
    }

}
