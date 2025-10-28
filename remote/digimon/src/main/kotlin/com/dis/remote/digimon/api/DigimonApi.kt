package com.dis.remote.digimon.api

import com.dis.core.network.retrofit.ApiResponse
import com.dis.remote.digimon.model.DigimonListResponse
import com.dis.remote.digimon.model.DigimonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DigimonApi {
    @GET("api/v1/digimon")
    suspend fun getDigimonList(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 20,
    ): ApiResponse<DigimonListResponse>

    @GET("api/v1/digimon/{id}")
    suspend fun getDigimon(
        @Path("id") id: Int,
    ): ApiResponse<DigimonResponse>
}
