package com.sammengistu.quicnetworking.data.source.news.repository

import com.sammengistu.quicnetworking.data.models.News
import com.sammengistu.quicnetworking.data.source.Result

interface NewsRepository {
    suspend fun getTopNews(country: String, pageSize: String): Result<News?>
}