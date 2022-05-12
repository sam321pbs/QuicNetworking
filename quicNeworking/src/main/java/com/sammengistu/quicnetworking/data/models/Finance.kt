package com.sammengistu.quicnetworking.data.models

data class FinanceResponse(
    val error: String?,
    val marketSummaryResponse: MarketSummary?,
)

data class MarketSummary(
    val result: List<Market>
)

data class Market(
    val exchange: String?,
    val market: String?,
    val region: String?,
    val regularMarketChange: MarketChange?,
    val regularMarketChangePercent: MarketChangePercent?,
    val regularMarketPreviousClose: MarketPreviousClose?,
    val regularMarketPrice: MarketPrice?,
    val shortName: String?
)

data class MarketChange(
    val fmt: String?
)

data class MarketChangePercent(
    val fmt: String?
)

data class MarketPreviousClose(
    val fmt: String?
)

data class MarketPrice(
    val fmt: String?
)