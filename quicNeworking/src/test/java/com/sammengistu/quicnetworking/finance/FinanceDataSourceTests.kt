package com.sammengistu.quicnetworking.finance

import com.sammengistu.quicnetworking.data.models.FinanceResponse
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.finance.FinanceConstants.Language.LANG_EN
import com.sammengistu.quicnetworking.data.source.finance.FinanceConstants.Region.US
import com.sammengistu.quicnetworking.data.source.finance.remote.FinanceRemoteDataSource
import com.sammengistu.quicnetworking.data.source.finance.remote.FinanceRemoteDataSourceImpl
import com.sammengistu.quicnetworking.data.source.finance.remote.retrofit.FinanceApiService
import com.sammengistu.quicnetworking.finance.FinanceTestConstants.Companion.fakeFinance
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response

class FinanceDataSourceTests {

    private val apiService: FinanceApiService = Mockito.mock(FinanceApiService::class.java)
    private lateinit var dataSource: FinanceRemoteDataSource

    @Before
    fun setup() {
        dataSource = FinanceRemoteDataSourceImpl(apiService, Dispatchers.IO)
    }

    @Test
    fun `getMarketSummary returns data with valid input`()  = runBlocking {
        Mockito.`when`(
            apiService.getMarketSummary(LANG_EN, US)
        ).thenReturn(Response.success(200, fakeFinance))

        val output = dataSource.getMarketSummary(LANG_EN, US)

        assert(output is Result.Success)
        val financeResponse = (output as Result.Success<FinanceResponse>).data
        assertNotNull(financeResponse)
        assertEquals(financeResponse?.marketSummaryResponse?.result?.get(0)?.market, "Market")
    }

    @Test
    fun `getMarketSummary returns data with api response error`() = runBlocking {
        Mockito.`when`(
            apiService.getMarketSummary("", "")
        ).thenReturn(Response.error(500, ResponseBody.create(null, "")))

        val output = dataSource.getMarketSummary(LANG_EN, US)

        assert(output is Result.Error)
    }

    @Test
    fun `getMarketSummary returns data with invalid lang and region`() = runBlocking {
        val output = dataSource.getMarketSummary("", "")

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }

    @Test
    fun `getMarketSummary returns data with invalid lang`() = runBlocking {
        val output = dataSource.getMarketSummary("", US)

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }

    @Test
    fun `getMarketSummary returns data with invalid region`() = runBlocking {
        val output = dataSource.getMarketSummary(LANG_EN, "")

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }
}