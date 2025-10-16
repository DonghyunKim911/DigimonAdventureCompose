package com.dis.domain.digimon.usecase

import com.dis.core.domain.model.Favorite
import com.dis.domain.digimon.repository.DigimonRepository
import javax.inject.Inject

class DeleteDigimonFavoriteUseCase @Inject constructor(
    private val repository: DigimonRepository
) {

    suspend fun invoke(favorite: Favorite) {
        repository.deleteFavoriteDigimon(favorite)
    }

}