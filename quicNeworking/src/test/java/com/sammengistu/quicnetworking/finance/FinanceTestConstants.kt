package com.sammengistu.quicnetworking.finance

import com.sammengistu.quicnetworking.data.models.*

internal class FinanceTestConstants {
    companion object {
        val fakeFinance = FinanceResponse(
            null,
            MarketSummary(
                listOf(
                    Market(
                        "Exchange",
                        "Market",
                        "Region",
                        MarketChange("fmt"),
                        MarketChangePercent("fmt"),
                        MarketPreviousClose("fmt"),
                        MarketPrice("fmt"),
                        "shortName"
                    )
                )
            )
        )
    }
}