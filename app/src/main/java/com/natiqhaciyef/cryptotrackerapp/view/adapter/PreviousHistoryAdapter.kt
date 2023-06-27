package com.natiqhaciyef.cryptotrackerapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.databinding.RecyclerPreviousHistoryItemBinding

class PreviousHistoryAdapter(val context: Context, val list: List<PriceModel>) :
    RecyclerView.Adapter<PreviousHistoryAdapter.PreviousHistoryHolder>() {

    inner class PreviousHistoryHolder(val binding: RecyclerPreviousHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousHistoryHolder {
        val binding =
            RecyclerPreviousHistoryItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return PreviousHistoryHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PreviousHistoryHolder, position: Int) {
        val view = holder.binding
        val previousHistoryModel = list[position]

        view.maxTitle.text = "Max: ${previousHistoryModel.maxPrice}"
        view.minTitle.text = "Max: ${previousHistoryModel.minPrice}"
        Glide.with(context).load(previousHistoryModel.currencyImage).into(view.iconImage)
    }

}