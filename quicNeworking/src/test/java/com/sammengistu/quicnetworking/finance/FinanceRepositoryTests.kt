package com.sammengistu.quicnetworking.finance

import com.sammengistu.quicnetworking.data.models.FinanceResponse
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.finance.FinanceConstants
import com.sammengistu.quicnetworking.data.source.finance.FinanceConstants.Language.LANG_EN
import com.sammengistu.quicnetworking.data.source.finance.FinanceConstants.Region.US
import com.sammengistu.quicnetworking.data.source.finance.remote.FinanceRemoteDataSource
import com.sammengistu.quicnetworking.data.source.finance.repository.FinanceRepository
import com.sammengistu.quicnetworking.data.source.finance.repository.FinanceRepositoryImpl
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FinanceRepositoryTests {

    private val dataSource: FinanceRemoteDataSource = Mockito.mock(FinanceRemoteDataSource::class.java)
    private lateinit var repo: FinanceRepository

    @Before
    fun setup() {
        repo = FinanceRepositoryImpl(dataSource, Dispatchers.IO)
    }

    @Test
    fun `getMarketSummary returns data with valid input`() = runBlocking {
        val result = Result.Success(FinanceTestConstants.fakeFinance)

        Mockito.`when`(dataSource.getMarketSummary(LANG_EN, US))
            .thenReturn(result)

        val output = repo.getMarketSummary(LANG_EN, US)

        assert(output is Result.Success<FinanceResponse>)

        val outputMarket = (output as Result.Success<FinanceResponse>).data?.marketSummaryResponse?.result?.get(0)

        assertEquals(outputMarket?.market, "Market")
        assertEquals(outputMarket?.region, "Region")
    }

    @Test
    fun `getMarketSummary returns data with invalid lang`() = runBlocking {
        val result = Result.Error(Exception())

        Mockito.`when`(dataSource.getMarketSummary("", US))
            .thenReturn(result)

        val output = repo.getMarketSummary("", US)

        assert(output is Result.Error)
    }

    @Test
    fun `getMarketSummary returns data with invalid region`() = runBlocking {
        val result = Result.Error(Exception())

        Mockito.`when`(dataSource.getMarketSummary(LANG_EN, ""))
            .thenReturn(result)

        val output = repo.getMarketSummary(LANG_EN, "")

        assert(output is Result.Error)
    }
}