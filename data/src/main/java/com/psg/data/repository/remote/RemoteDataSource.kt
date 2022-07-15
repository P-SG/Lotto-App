package com.psg.data.repository.remote

import com.psg.data.model.remote.LottoResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun searchLotto(drwNo: Int): Response<LottoResponse>
}