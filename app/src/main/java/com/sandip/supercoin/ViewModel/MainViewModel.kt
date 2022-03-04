package com.sandip.supercoin.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandip.supercoin.DataClass.CoinData
import com.sandip.supercoin.DataClass.CoinList
import com.sandip.supercoin.DataClass.Coins
import com.sandip.supercoin.Repository.coinrepository
import com.sandip.supercoin.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: coinrepository) : ViewModel(){

    init {

        viewModelScope.launch(Dispatchers.IO) {

            repository.getCoins("1ab51f5d-b8e6-4695-b2b8-e0d6acbdce06")

        }
    }

    val coins : LiveData<NetworkResult<Coins>> get() = repository.coins

    suspend fun getCoins():List<CoinData>{
        val coins = repository.getCoins()
        return coins
    }

}