package com.sammengistu.quicnetworking.data.source.news.remote

import com.sammengistu.quicnetworking.data.models.News
import com.sammengistu.quicnetworking.data.source.Result

interface NewsRemoteDataSource {
    suspend fun getTopNews(country: String, pageSize: String): Result<News?>
}