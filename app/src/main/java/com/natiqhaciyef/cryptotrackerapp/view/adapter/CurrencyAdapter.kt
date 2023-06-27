package com.natiqhaciyef.cryptotrackerapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import com.natiqhaciyef.cryptotrackerapp.databinding.RecyclerCurrencyItemBinding

class CurrencyAdapter(
    private val context: Context,
    private val list: List<CurrencyModel>
) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {

    var onClick: (CurrencyModel) -> Unit = { }

    inner class CurrencyHolder(val binding: RecyclerCurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val binding =
            RecyclerCurrencyItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CurrencyHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val view = holder.binding
        val currencyModel = list[position]

        Glide.with(context).load(currencyModel.image).into(view.currencyImageView)

        val currentPrice = "%.2f".format(currencyModel.current_price)
        val priceChange = "%.2f".format(currencyModel.price_change_24h)
        val totalVolume = "%.2f".format(currencyModel.total_volume)


        view.currencyTitleText.text = currencyModel.name
        view.currencyCurrentPrice.text = "Current price : $currentPrice $"
        view.currencyTotalVolumeText.text = "Total volume : ${changeNumberToWord(totalVolume)}"
        view.currencyPriceChangeText.text = "Price change : $priceChange"

        holder.itemView.setOnClickListener {
            onClick.invoke(currencyModel)
        }
    }

    private fun changeNumberToWord(value: String): String {
        return if (value.length >= 13)
            "${"%.2f".format(value.toDouble() / 1000000000)} B"    // billion
        else if (value.length in 10..12)
            "${"%.2f".format(value.toDouble() / 1000000)} M"    // million
        else
            "${"%.2f".format(value.toDouble() / 1000)} K"   // thousand

    }
}