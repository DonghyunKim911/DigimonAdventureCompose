package com.dis.domain.digimon.usecase

import com.dis.domain.digimon.model.Content
import com.dis.domain.digimon.repository.DigimonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDigimonListUseCase @Inject constructor(
    private val digimonRepository: DigimonRepository
) {

    operator fun invoke(page: Int): Flow<List<Content>> = digimonRepository.getDigimonList(page)

}
