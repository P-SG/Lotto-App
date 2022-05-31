package com.psg.lottoapp.data.api

import com.psg.lottoapp.data.model.LottoResponse
import dagger.Module
import dagger.hilt.InstallIn
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LottoAPI {

    @GET("/common.do")
    suspend fun getLottoNum(
        @Query("drwNo") drwNum: Int,
        @Query("method") method: String = "getLottoNumber"
    ): Response<LottoResponse>
}