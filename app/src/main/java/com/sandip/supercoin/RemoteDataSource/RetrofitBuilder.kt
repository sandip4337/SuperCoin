package com.sandip.supercoin.RemoteDataSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val base_url = "https://pro-api.coinmarketcap.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}