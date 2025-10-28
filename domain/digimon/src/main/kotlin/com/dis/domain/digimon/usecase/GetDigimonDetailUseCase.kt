package com.dis.domain.digimon.usecase

import com.dis.core.domain.model.Digimon
import com.dis.domain.digimon.repository.DigimonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDigimonDetailUseCase
    @Inject
    constructor(
        private val digimonRepository: DigimonRepository,
    ) {
        suspend operator fun invoke(id: Int): Digimon? = digimonRepository.getDigimonDetail(id)
    }
