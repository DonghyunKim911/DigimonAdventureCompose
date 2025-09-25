package com.dis.remote.digimon.impl

import androidx.annotation.WorkerThread
import com.dis.data.model.DigimonListData
import com.dis.data.remote.DigimonRemoteDataSource
import com.dis.remote.digimon.api.DigimonApi
import com.dis.remote.digimon.mapper.toData
import javax.inject.Inject

class DigimonRemoteDataSourceImpl @Inject constructor(
    private val digimonApi: DigimonApi
): DigimonRemoteDataSource {

    @WorkerThread
    override suspend fun getDigimonList(
        page: Int,
        pageSize: Int
    ): DigimonListData {
        return digimonApi.getDigimonList(page, pageSize).getOrThrow().toData()
    }

}
