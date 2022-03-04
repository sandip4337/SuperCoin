package com.sandip.supercoin.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sandip.supercoin.R
import kotlinx.android.synthetic.main.activity_coin_activty.*

class CoinActivty : AppCompatActivity() {

    var coinId:String? = "100"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_activty)

        coinId = intent.getStringExtra("id")

        coinName.text = intent.getStringExtra("name")
        coinSymbol.text = intent.getStringExtra("symbol")
        priceUsdText.text = intent.getStringExtra("price")
        percentChange1hText.text = intent.getStringExtra("percentChange1h")
        percentChange24hText.text = intent.getStringExtra("percentChange24h")
        percentChange7day.text = intent.getStringExtra("percentChange7d")

    }

    fun Share(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, " *Name*: ${coinName.text} \n *Symbol*: ${coinSymbol.text} \n *Price*: ${priceUsdText.text} \n *1hr*: ${percentChange1hText.text} \n *24hr*: ${percentChange24hText.text} \n *7d*: ${percentChange7day.text}")
        val chooser = Intent.createChooser(intent,"price is")
        startActivity(chooser)

    }
}