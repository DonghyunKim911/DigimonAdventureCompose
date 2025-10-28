package com.dis.data.remote

import com.dis.data.model.DigimonData
import com.dis.data.model.DigimonListData

interface DigimonRemoteDataSource {
    suspend fun getDigimonList(
        page: Int,
        pageSize: Int,
    ): DigimonListData

    suspend fun getDigimonDetail(id: Int): DigimonData
}
