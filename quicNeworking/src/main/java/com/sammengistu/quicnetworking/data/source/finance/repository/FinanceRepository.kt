package com.sammengistu.quicnetworking.data.source.finance.repository

import com.sammengistu.quicnetworking.data.models.FinanceResponse
import com.sammengistu.quicnetworking.data.source.Result

interface FinanceRepository {
    suspend fun getMarketSummary(lang: String, region: String): Result<FinanceResponse?>
}