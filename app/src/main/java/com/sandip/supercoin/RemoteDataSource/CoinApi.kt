package com.sandip.supercoin.RemoteDataSource

import com.sandip.supercoin.DataClass.Coins
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi{

    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getCoins(@Query("CMC_PRO_API_KEY") CMC_PRO_API_KEY:String): Response<Coins>

}