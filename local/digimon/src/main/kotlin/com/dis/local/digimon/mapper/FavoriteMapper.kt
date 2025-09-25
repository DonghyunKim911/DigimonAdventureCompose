package com.dis.local.digimon.mapper

import com.dis.core.database.entity.FavoriteEntity
import com.dis.data.model.FavoriteData

fun FavoriteEntity.toData(): FavoriteData = FavoriteData(id = id)

fun FavoriteData.toLocal(): FavoriteEntity = FavoriteEntity(id = id)
