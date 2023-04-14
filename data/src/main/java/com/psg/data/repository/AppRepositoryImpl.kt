package com.psg.data.repository

import com.psg.data.mapper.toEntity
import com.psg.data.model.local.toDomain
import com.psg.data.model.remote.toDomain
import com.psg.data.repository.local.LocalDataSource
import com.psg.data.repository.remote.RemoteDataSource
import com.psg.domain.model.Lotto
import com.psg.domain.model.LottoDate
import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppRepositoryImpl constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    ):AppRepository {

    override fun getRemoteLotto(drwNum: Int): Flow<Result<Lotto>> = flow {
        val response = remoteDataSource.searchLotto(drwNum)
        val lotto = requireNotNull(response.body()) {
            emit(Result.failure(Exception("Lottery API Error")))
        }.toDomain()
        emit(Result.success(lotto))
    }

    override fun getLocalLotto(): Flow<Result<LottoDate>> = flow {
        val lotto = localDataSource.getLottoNum().toDomain()
        emit(Result.success(lotto))
    }

    override fun insertLotto(lotto: LottoDate): Flow<Result<Unit>> = flow {
        localDataSource.insertLotto(lotto.toEntity())
        emit(Result.success(Unit))
    }

    override fun updateLotto(lotto: LottoDate): Flow<Result<Unit>> = flow {
        localDataSource.updateLotto(lotto.toEntity())
        emit(Result.success(Unit))
    }

    override fun deleteLotto(): Flow<Result<Unit>> = flow {
        localDataSource.deleteLotto()
        emit(Result.success(Unit))
    }


}