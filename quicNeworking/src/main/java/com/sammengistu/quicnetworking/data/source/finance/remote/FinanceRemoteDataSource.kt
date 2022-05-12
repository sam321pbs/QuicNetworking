package com.sammengistu.quicnetworking.data.source.finance.remote

import com.sammengistu.quicnetworking.data.models.FinanceResponse
import com.sammengistu.quicnetworking.data.source.Result

interface FinanceRemoteDataSource {
    suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?>
}