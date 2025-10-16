package com.dis.domain.digimon.usecase

import com.dis.core.domain.model.Favorite
import com.dis.domain.digimon.repository.DigimonRepository
import javax.inject.Inject

class SaveDigimonFavoriteUseCase @Inject constructor(
    private val digimonRepository: DigimonRepository,
) {
    suspend operator fun invoke(favorite: Favorite) {
        digimonRepository.saveFavoriteDigimon(favorite)
    }
}
