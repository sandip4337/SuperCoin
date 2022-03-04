package com.sandip.supercoin.LocalDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sandip.supercoin.DataClass.CoinData

@Dao
interface CoinDao {

    @Insert
    suspend fun insertCoins(coins : CoinData)

    @Query("SELECT * FROM CoinTable")
    suspend fun getCoins():List<CoinData>

}