package com.sandip.supercoin.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sandip.supercoin.DataClass.CoinData
import com.sandip.supercoin.DataClass.Coins
import com.sandip.supercoin.LocalDataSource.CoinDatabase
import com.sandip.supercoin.RemoteDataSource.CoinApi
import com.sandip.supercoin.util.NetworkResult
import com.sandip.supercoin.util.NetworkUtil
import retrofit2.Response

class coinrepository(private val coinApi: CoinApi,
                     private val coinDatabase: CoinDatabase,
                     private val applicationContext: Context
){

    private val coinslivedata = MutableLiveData<NetworkResult<Coins>>()

    val coins: LiveData<NetworkResult<Coins>> get() = coinslivedata

    suspend fun getCoins(Api_key : String) {

        if (NetworkUtil.checkForInternet(applicationContext)) {

            try {

                val result = coinApi.getCoins(Api_key)
                coinslivedata.postValue(handleFoodRecipeResponse(result))

            } catch (e: Exception) {
                coinslivedata.postValue(NetworkResult.Error(e.message.toString()))
            }
        } else {
            coinslivedata.postValue(NetworkResult.Error("No Network Connection"))
        }

    }

    suspend fun getCoins():List<CoinData>{
        val coins = coinDatabase.coinDao().getCoins()
        return coins
    }

    private suspend fun handleFoodRecipeResponse(response: Response<Coins>):NetworkResult<Coins>? {
        when {
            response.message().toString().contains("timeout")->{
                return NetworkResult.Error("Requested Time Out")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API key Limited")
            }
            response.body()!!.data.isNullOrEmpty()->{
                return NetworkResult.Error("Recipes Not Found")
            }
            response.isSuccessful->{
                val coins = response.body()

                if (coins != null) {
                    for(i in coins.data){

                        val j = i.quote.uSD

                         coinDatabase.coinDao().insertCoins(CoinData(
                             0,
                             i.id,
                             i.name,
                             i.symbol,
                             j.percentChange1h,
                             j.percentChange24h,
                             j.price,
                             j.percentChange7d))
                    }
                }

                return NetworkResult.Success(coins!!)
            }
            else->{
                return NetworkResult.Error(response.message())
            }
        }
    }
}