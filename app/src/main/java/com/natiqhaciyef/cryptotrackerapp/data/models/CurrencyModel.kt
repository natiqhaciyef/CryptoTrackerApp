package com.natiqhaciyef.cryptotrackerapp.data.models

data class CurrencyModel(
    val id: String,
    val name: String,
    val image: String,
    val symbol: String,
    val current_price: Double,
    val high_24h: Double,
    val low_24h: Double,
    val price_change_24h: Double,
    val last_updated: String,
    val market_cap: Double,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val market_cap_rank: Int,
    val max_supply: Double,
    val price_change_percentage_24h: Double,
    val total_supply: Double,
    val total_volume: Double,
    val circulating_supply: Double,
    val fully_diluted_valuation: Double,
)