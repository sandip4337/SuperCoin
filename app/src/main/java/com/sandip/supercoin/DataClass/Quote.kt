package com.sandip.supercoin.DataClass


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val uSD: USD
)