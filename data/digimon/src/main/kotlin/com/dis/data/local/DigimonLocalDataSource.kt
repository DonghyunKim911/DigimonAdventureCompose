package com.dis.data.local

import com.dis.data.model.ContentData

interface DigimonLocalDataSource {

    suspend fun insertDigimonContents(
        contents: List<ContentData>,
        page: Int,
    )

    suspend fun getDigimonList(page: Int): List<ContentData>

}
