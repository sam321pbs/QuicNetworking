package com.sammengistu.quicnetworking.news

import com.sammengistu.quicnetworking.data.models.News
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.news.NewsConstants
import com.sammengistu.quicnetworking.data.source.news.remote.NewsRemoteDataSource
import com.sammengistu.quicnetworking.data.source.news.repository.NewsRepository
import com.sammengistu.quicnetworking.data.source.news.repository.NewsRepositoryImpl
import com.sammengistu.quicnetworking.news.NewsTestConstants.Companion.fakeNews
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class NewsRepositoryTest {

    private val dataSource: NewsRemoteDataSource = mock(NewsRemoteDataSource::class.java)
    private lateinit var repo: NewsRepository

    @Before
    fun setup() {
        repo = NewsRepositoryImpl(dataSource, Dispatchers.IO)
    }

    @Test
    fun `getTopNews returns data with valid input`() = runBlocking {
        val result = Result.Success(fakeNews)

        `when`(dataSource.getTopNews(NewsConstants.Country.US, NewsConstants.Size.PAGE_MAX)).thenReturn(result)

        val output = repo.getTopNews(NewsConstants.Country.US, NewsConstants.Size.PAGE_MAX)

        assert(output is Result.Success<News>)

        val outputArticle = (output as Result.Success<News>).data?.articles?.get(0)

        assertEquals(outputArticle?.title, "Title")
        assertEquals(outputArticle?.author, "Author")
    }

    @Test
    fun `getTopNews returns data with invalid page size`() = runBlocking {
        val result = Result.Error(IllegalArgumentException())

        `when`(dataSource.getTopNews(NewsConstants.Country.US, "")).thenReturn(result)

        val output = repo.getTopNews(NewsConstants.Country.US, "")

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }

    @Test
    fun `getTopNews returns data with invalid country`() = runBlocking {
        val result = Result.Error(IllegalArgumentException())

        `when`(dataSource.getTopNews("", NewsConstants.Size.PAGE_MAX)).thenReturn(result)

        val output = repo.getTopNews("", NewsConstants.Size.PAGE_MAX)

        assert(output is Result.Error)
        assert((output as Result.Error).exception is IllegalArgumentException)
    }
}