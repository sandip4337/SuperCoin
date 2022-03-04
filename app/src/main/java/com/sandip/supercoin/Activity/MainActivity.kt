package com.sandip.supercoin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandip.supercoin.CoinAdapter
import com.sandip.supercoin.CoinApplication
import com.sandip.supercoin.DataClass.CoinList
import com.sandip.supercoin.R
import com.sandip.supercoin.ViewModel.MainViewModel
import com.sandip.supercoin.ViewModel.MainViewModelFactory
import com.sandip.supercoin.util.CoinArray
import com.sandip.supercoin.util.NetworkResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private val recyclerhome: RecyclerView get() = findViewById(R.id.shimmer_recycler_view)
    lateinit var layoutManager: GridLayoutManager
    lateinit var menuadaptar: CoinAdapter

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shimmer_recycler_view.showShimmer()

        layoutManager = GridLayoutManager(this, 2)

        val repository = (application as CoinApplication).repository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))
                .get(MainViewModel::class.java)

        mainViewModel.coins.observe(this, Observer {

            when (it) {
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {

                    it.data?.let {
                        for (coin in it.data) {

                            val j = coin.quote.uSD

                            val coinObject = CoinList(coin.id, coin.name, coin.symbol,
                                    j.percentChange1h, j.percentChange24h, j.price,j.percentChange7d)

                            CoinArray.add(coinObject)

                            menuadaptar = CoinAdapter(this, CoinArray)

                            recyclerhome.adapter = menuadaptar

                            recyclerhome.layoutManager = layoutManager

                            runOnUiThread {
                                menuadaptar.updateData(CoinArray)
                            }
                        }
                    }
                }

                is NetworkResult.Error -> {
                    when (it.message) {
                        "No Network Connection" ->{
                            GlobalScope.launch {
                                val C = mainViewModel.getCoins()
                                Log.d("coin",C.toString())
                            }
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()}
                    }
                }
            }
        }
        )
    }

}


