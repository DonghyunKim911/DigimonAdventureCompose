package com.core.presentation.mapper

import com.core.presentation.model.FavoriteModel
import com.dis.core.domain.model.Favorite

fun Favorite.toPresentation(): FavoriteModel = FavoriteModel(id = id)

fun FavoriteModel.toDomain(): Favorite = Favorite(id = id)
