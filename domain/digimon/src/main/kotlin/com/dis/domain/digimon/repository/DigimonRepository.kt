package com.dis.domain.digimon.repository

import com.dis.domain.digimon.model.Content
import kotlinx.coroutines.flow.Flow

interface DigimonRepository {

    fun getDigimonList(page: Int): Flow<List<Content>>

}
