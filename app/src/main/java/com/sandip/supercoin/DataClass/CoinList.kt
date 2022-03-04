package com.sandip.supercoin.DataClass

data class CoinList (
    val id: Int,
    val name: String,
    val symbol: String,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val price: Double,
    val percentChange7d: Double
)