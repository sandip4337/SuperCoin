package com.sandip.supercoin.DataClass


import com.google.gson.annotations.SerializedName

data class Coins(
    @SerializedName("data")
    val `data`: List<Data>,
)