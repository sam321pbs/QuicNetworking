package com.sammengistu.quicnetworking.data.source.news.repository

import com.sammengistu.quicnetworking.data.models.News
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.data.source.news.remote.NewsRemoteDataSource
import com.sammengistu.quicnetworking.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): NewsRepository {
    override suspend fun getTopNews(country: String, pageSize: String): Result<News?> {
        return withContext(ioDispatcher) {
            when (val result = remoteDataSource.getTopNews(country, pageSize)) {
                is Result.Success -> Result.Success(result.data)
                is Result.Error -> result
            }
        }
    }
}