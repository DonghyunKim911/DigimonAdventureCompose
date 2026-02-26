package com.dis.domain.digimon.usecase

import com.dis.core.domain.model.Content
import com.dis.domain.digimon.repository.DigimonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkDigimonListUseCase
    @Inject
    constructor(
        private val digimonRepository: DigimonRepository,
    ) {
        operator fun invoke(): Flow<List<Content>> = digimonRepository.getBookmarkDigimonList()
    }
