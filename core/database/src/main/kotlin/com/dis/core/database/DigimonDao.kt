package com.dis.core.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.dis.core.database.entity.ContentEntity

@Dao
interface DigimonDao {

    @Upsert
    suspend fun upsertDigimonList(contents: List<ContentEntity>)

    @Query("SELECT * FROM ContentEntity WHERE page = :page")
    suspend fun getDigimonList(page: Int): List<ContentEntity>

}
