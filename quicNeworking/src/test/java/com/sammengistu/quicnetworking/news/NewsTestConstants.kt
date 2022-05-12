package com.sammengistu.quicnetworking.news

import com.sammengistu.quicnetworking.data.models.Article
import com.sammengistu.quicnetworking.data.models.News
import com.sammengistu.quicnetworking.data.models.Source

internal class NewsTestConstants {
    companion object {
        val fakeNews = News(
            "",
            listOf(
                Article(
                    Source("", ""),
                    "Author",
                    "Title",
                    "Description",
                    "URL",
                    "Content",
                    "Image URL",
                    "Published At"
                )
            )
        )
    }
}