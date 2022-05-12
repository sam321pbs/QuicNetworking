package com.sammengistu.quicnetworking.data.source.finance.remote

import com.sammengistu.quicnetworking.data.models.FinanceResponse
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.di.scope.IoDispatcher
import com.sammengistu.quicnetworking.data.source.finance.remote.retrofit.FinanceApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FinanceRemoteDataSourceImpl @Inject constructor(
    private val financeApiService: FinanceApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FinanceRemoteDataSource {
    override suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?> {
        if (lang.isBlank() || region.isBlank()) {
            return Result.Error(
                IllegalArgumentException("Can not accept empty string for lang or region.")
            )
        }
        return withContext(ioDispatcher) {
            return@withContext try {
                val response = financeApiService.getMarketSummary(lang, region)
                if (response.isSuccessful) {
                    Result.Success(response.body())
                } else {
                    Result.Error(Exception(response.errorBody().toString()))
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }
}