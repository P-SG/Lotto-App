package com.psg.data.repository.remote

import com.psg.data.api.LottoAPI
import com.psg.data.model.remote.LottoResponse
import retrofit2.Response

class RemoteDataSourceImpl(private val api: LottoAPI)
    :RemoteDataSource{
    override suspend fun searchLotto(drwNo: Int): Response<LottoResponse> = api.getLottoNum(drwNo)
}