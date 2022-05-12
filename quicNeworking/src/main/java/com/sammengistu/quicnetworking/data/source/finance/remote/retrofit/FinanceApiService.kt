package com.sammengistu.quicnetworking.data.source.finance.remote.retrofit

import com.sammengistu.quicnetworking.data.models.FinanceResponse
import com.sammengistu.quicnetworking.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

// https://www.yahoofinanceapi.com/tutorial
interface FinanceApiService {

    @Headers(
        "X-API-KEY: ${BuildConfig.FINANCE_API_KEY}",
        "accept: application/json"
    )
    @GET("/v6/finance/quote/marketSummary")
    suspend fun getMarketSummary(
        @Query("lang") lang: String,
        @Query("region") region: String
    ): Response<FinanceResponse>
}