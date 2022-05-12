package com.sammengistu.quicnetworking.data.source.finance.repository

import com.sammengistu.quicnetworking.data.models.FinanceResponse
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.finance.remote.FinanceRemoteDataSource
import com.sammengistu.quicnetworking.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FinanceRepositoryImpl @Inject constructor(
    private val remoteDataSource: FinanceRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): FinanceRepository {
    override suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?> {
        return withContext(ioDispatcher) {
            when (val result = remoteDataSource.getMarketSummary(lang, region)) {
                is Result.Success -> Result.Success(result.data)
                is Result.Error -> Result.Error(result.exception)
            }
        }
    }
}