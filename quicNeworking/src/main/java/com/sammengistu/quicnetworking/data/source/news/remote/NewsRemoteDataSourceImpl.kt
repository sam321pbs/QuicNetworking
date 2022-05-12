package com.sammengistu.quicnetworking.data.source.news.remote

import com.sammengistu.quicnetworking.data.models.News
import com.sammengistu.quicnetworking.data.source.Result
import com.sammengistu.quicnetworking.di.scope.IoDispatcher
import com.sammengistu.quicnetworking.data.source.news.remote.retrofit.NewsApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NewsRemoteDataSource {

    override suspend fun getTopNews(country: String, pageSize: String): Result<News?> {
        if (country.isBlank() || pageSize.isBlank()) {
            return Result.Error(
                IllegalArgumentException("Can not accept empty string for country or page size.")
            )
        }
        return withContext(ioDispatcher) {
            return@withContext try {
                val response = newsApiService.getTopNews(country, pageSize)
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