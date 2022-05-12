package com.sammengistu.quicnetworking.news

import com.sammengistu.quicnetworking.data.models.News
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.news.NewsConstants.Country.US
import com.sammengistu.quicnetworking.data.source.news.NewsConstants.Size.PAGE_MAX
import com.sammengistu.quicnetworking.data.source.news.remote.NewsRemoteDataSource
import com.sammengistu.quicnetworking.data.source.news.remote.NewsRemoteDataSourceImpl
import com.sammengistu.quicnetworking.data.source.news.remote.retrofit.NewsApiService
import com.sammengistu.quicnetworking.news.NewsTestConstants.Companion.fakeNews
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class NewsRemoteDataSourceTests {

    private val apiService: NewsApiService = mock(NewsApiService::class.java)
    private lateinit var dataSource: NewsRemoteDataSource

    @Before
    fun setup() {
        dataSource = NewsRemoteDataSourceImpl(apiService, Dispatchers.IO)
    }

    @Test
    fun `getTopNews returns data with valid input`()  = runBlocking {
        `when`(
            apiService.getTopNews(US, PAGE_MAX)
        ).thenReturn(Response.success(200,fakeNews))

        val output = dataSource.getTopNews(US, PAGE_MAX)

        assert(output is Result.Success)
        val news = (output as Result.Success<News>).data
        assertNotNull(news)
        assertEquals(news?.articles?.get(0)?.title, "Title")
    }

    @Test
    fun `getTopNews returns data with api response error`() = runBlocking {
        `when`(
            apiService.getTopNews("", "")
        ).thenReturn(Response.error(500, ResponseBody.create(null, "")))

        val output = dataSource.getTopNews("", "")

        assert(output is Result.Error)
    }

    @Test
    fun `getTopNews returns data with invalid country and page size`() = runBlocking {
        val output = dataSource.getTopNews("", "")

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }

    @Test
    fun `getTopNews returns data with invalid country`() = runBlocking {
        val output = dataSource.getTopNews("", PAGE_MAX)

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }

    @Test
    fun `getTopNews returns data with invalid page size`() = runBlocking {
        val output = dataSource.getTopNews(US, "")

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }
}