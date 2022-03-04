package com.sandip.supercoin.LocalDataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sandip.supercoin.DataClass.CoinData

@Database(entities = [CoinData::class],version = 1)
abstract class CoinDatabase:RoomDatabase(){

    abstract fun coinDao():CoinDao

    companion object{
        @Volatile
        private var INSTANCE:CoinDatabase? = null

        fun getDataBase(context: Context):CoinDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CoinDatabase::class.java,
                        "CoinsDB")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}