package com.natiqhaciyef.cryptotrackerapp.data.models

data class CurrencyHistoryModel(
    val community_data: CommunityData,
    val developer_data: DeveloperData,
    val id: String,
    val image: Image,
    val localization: Localization,
    val market_data: MarketData,
    val name: String,
    val public_interest_stats: PublicInterestStats,
    val symbol: String
)