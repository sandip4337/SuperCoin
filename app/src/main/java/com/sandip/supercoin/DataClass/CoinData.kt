package com.sandip.supercoin.DataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CoinTable")
data class CoinData(
    @PrimaryKey(autoGenerate = true)
    val no: Int,
    val id: Int,
    val name: String,
    val symbol: String,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val price: Double,
    val percentChange7d: Double
)
