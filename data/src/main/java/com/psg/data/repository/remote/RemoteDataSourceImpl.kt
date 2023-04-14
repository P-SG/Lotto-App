package com.psg.data.repository.remote

import com.psg.data.api.LottoAPI
import com.psg.data.model.remote.LottoResponse
import retrofit2.Response

class RemoteDataSourceImpl(private val api: LottoAPI)
    :RemoteDataSource{
    override suspend fun searchLotto(drwNo: Int): Response<LottoResponse> = run {
        api.getLottoNum(drwNo).run {
            if (isSuccessful && body() != null) this else throw Exception("Lottery API Error")
        }
    }
}