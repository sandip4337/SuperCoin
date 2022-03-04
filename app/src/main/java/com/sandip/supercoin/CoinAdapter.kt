package com.sandip.supercoin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sandip.supercoin.Activity.CoinActivty
import com.sandip.supercoin.DataClass.CoinData
import com.sandip.supercoin.DataClass.CoinList
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class CoinAdapter(private val context: Context, var itemList : ArrayList<CoinList>): RecyclerView.Adapter<CoinAdapter.adaptarholder>(){

    fun updateData(itemList: List<CoinList>) {
        this.itemList = itemList as ArrayList<CoinList>
        notifyDataSetChanged()
    }

    class adaptarholder(view: View) : RecyclerView.ViewHolder(view){

        val coinicon = view.findViewById<ImageView>(R.id.coinIcon)
        val coinname = view.findViewById<TextView>(R.id.coinName)
        val homecard = view.findViewById<CardView>(R.id.homecards)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptarholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.coin_layout, parent, false)
        return adaptarholder(view)
    }

    override fun onBindViewHolder(holder: adaptarholder, position: Int) {

        val coin = itemList[position]
        val imageUrl = "https://res.cloudinary.com/dxi90ksom/image/upload/"
        holder.coinname?.text = coin.name
        Picasso.get().load(imageUrl + coin.symbol.toLowerCase(Locale.ROOT) + ".png").into(holder.coinicon)

        fun Double.roundDecimal(digit: Int) = "%.${digit}f".format(this)

        holder.homecard.setOnClickListener {
            val intent = Intent(context, CoinActivty::class.java)
            intent.putExtra("id", coin.id)
            intent.putExtra("name", coin.name)
            intent.putExtra("symbol", coin.symbol)
            intent.putExtra("price",'$' + (coin.price).toString())
            intent.putExtra("percentChange1h", ((coin.percentChange1h).roundDecimal(2) + "%"))
            intent.putExtra("percentChange24h", ((coin.percentChange24h).roundDecimal(2) + "%"))
            intent.putExtra("percentChange7d", ((coin.percentChange7d).roundDecimal(2) + "%"))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}