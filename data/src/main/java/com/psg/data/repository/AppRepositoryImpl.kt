package com.psg.data.repository

import com.psg.data.mapper.toEntity
import com.psg.data.model.remote.toDomain
import com.psg.data.model.remote.toEntity
import com.psg.data.repository.local.LocalDataSource
import com.psg.data.repository.remote.RemoteDataSource
import com.psg.data.utils.toCalDate
import com.psg.domain.model.Lotto
import com.psg.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate

class AppRepositoryImpl constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : AppRepository {

    override fun getLotto(drwNum: Int?): Flow<Result<Lotto>> = flow {
        drwNum?.let {
            val remote = remoteDataSource.searchLotto(drwNum)
            remote.body()?.let {
                localDataSource.insertLotto(it.toEntity())
                emit(Result.success(it.toDomain()))
            } ?: run {
                emit(Result.failure(Exception("Lottery API Error")))
            }
        } ?: run {
            val remote = remoteDataSource.searchLotto(1000 + LocalDate.now().toCalDate())
            remote.body()?.let {
                localDataSource.updateLotto(it.toEntity())
                emit(Result.success(it.toDomain()))
            } ?: run {
                emit(Result.failure(Exception("Lottery API Error")))
            }
        }

    }

    override fun syncLotto(): Flow<Result<Lotto>> = flow {
        val local = localDataSource.getLottoNum()
        if (local != null && local.drwNoDate.toCalDate() > 7) {
            val remote = remoteDataSource.searchLotto(local.drwNo + local.drwNoDate.toCalDate())
            remote.body()?.let {
                localDataSource.updateLotto(it.toEntity())
                emit(Result.success(it.toDomain()))
            } ?: run {
                emit(Result.failure(Exception("Lottery API Error")))
            }
        } else {
            val remote = remoteDataSource.searchLotto(1000 + LocalDate.now().toCalDate())
            remote.body()?.let {
                localDataSource.insertLotto(it.toEntity())
                emit(Result.success(it.toDomain()))
            } ?: run {
                emit(Result.failure(Exception("Lottery API Error")))
            }
        }
    }

    override fun insertLotto(lotto: Lotto): Flow<Result<Unit>> = flow {
        localDataSource.insertLotto(lotto.toEntity())
        emit(Result.success(Unit))
    }

    override fun updateLotto(lotto: Lotto): Flow<Result<Unit>> = flow {
        localDataSource.updateLotto(lotto.toEntity())
        emit(Result.success(Unit))
    }

    override fun deleteLotto(): Flow<Result<Unit>> = flow {
        localDataSource.deleteLotto()
        emit(Result.success(Unit))
    }


}