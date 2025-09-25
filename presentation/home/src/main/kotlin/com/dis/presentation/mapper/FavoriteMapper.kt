package com.dis.presentation.mapper

import com.dis.domain.digimon.model.Favorite
import com.dis.presentation.model.FavoriteModel

fun Favorite.toPresentation(): FavoriteModel = FavoriteModel(id = id)

fun FavoriteModel.toDomain(): Favorite = Favorite(id = id)
