package com.natiqhaciyef.cryptotrackerapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natiqhaciyef.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.natiqhaciyef.cryptotrackerapp.databinding.RecyclerCurrencyItemBinding

class CurrencyAdapter(val context: Context, val list: List<ExtendedCurrencyModel>): RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {
    inner class CurrencyHolder(val binding: RecyclerCurrencyItemBinding): RecyclerView.ViewHolder(binding.root)

    var onItemClick: (ExtendedCurrencyModel) -> Unit = {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val binding = RecyclerCurrencyItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CurrencyHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val view = holder.binding
        val exCurrencyModel = list[position]

        val price = "%.2f".format(exCurrencyModel.currencyModel.usd)
        val totalVolume = "%.2f".format(exCurrencyModel.currencyModel.usd_24h_vol)
        val priceChange = "%.2f".format(exCurrencyModel.currencyModel.usd_24h_change)

        Glide.with(context).load(exCurrencyModel.image).into(view.currencyImageView)
        view.currencyTitleText.text = exCurrencyModel.title
        view.currencyCurrentPrice.text = "Price: $price"
        view.currencyTotalVolumeText.text = "Total volume: $totalVolume"
        view.currencyPriceChangeText.text = "Price change: $priceChange"

        holder.itemView.setOnClickListener {
            onItemClick.invoke(exCurrencyModel)
        }
    }
}