package com.psg.data.repository

import com.psg.data.mapper.domainToEntity
import com.psg.data.mapper.responseToDomain
import com.psg.data.repository.local.LocalDataSource
import com.psg.data.repository.remote.RemoteDataSource
import com.psg.domain.model.Lotto
import com.psg.domain.model.LottoDate
import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    ):AppRepository {

    override suspend fun getRemoteLotto(drwNum: Int): Flow<Lotto> = flow {
        val response = remoteDataSource.searchLotto(drwNum)
        if (response.isSuccessful){
            emit(responseToDomain(response.body()!!))
        }
    }

    override suspend fun getLocalLotto(): Flow<LottoDate> {
        TODO("Not yet implemented")
    }

    override suspend fun insertLotto(lotto: LottoDate) {
        localDataSource.insertLotto(domainToEntity(lotto))
    }

    override suspend fun updateLotto(lotto: LottoDate) {
        localDataSource.updateLotto(domainToEntity(lotto))
    }

    override suspend fun deleteLotto() {
        localDataSource.deleteLotto()
    }


}