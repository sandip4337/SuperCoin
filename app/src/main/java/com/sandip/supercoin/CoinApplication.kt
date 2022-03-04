package com.sandip.supercoin

import android.app.Application
import com.sandip.supercoin.LocalDataSource.CoinDatabase
import com.sandip.supercoin.RemoteDataSource.CoinApi
import com.sandip.supercoin.RemoteDataSource.RetrofitBuilder
import com.sandip.supercoin.Repository.coinrepository

class CoinApplication: Application() {

    lateinit var repository: coinrepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){

        val quoteService = RetrofitBuilder.getInstance().create(CoinApi::class.java)

        val database = CoinDatabase.getDataBase(this)

        repository = coinrepository(quoteService,database,applicationContext)

    }
}