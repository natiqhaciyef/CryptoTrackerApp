package com.natiqhaciyef.cryptotrackerapp.view.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.cryptotrackerapp.R
import com.natiqhaciyef.cryptotrackerapp.databinding.FragmentPreviousHistoryBinding
import com.natiqhaciyef.cryptotrackerapp.view.adapter.PreviousHistoryAdapter
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.PreviousHistoryViewModel
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.SetPriceViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PreviousHistoryFragment : Fragment() {
    private lateinit var binding: FragmentPreviousHistoryBinding
    private lateinit var adapter: PreviousHistoryAdapter
    private val priceViewModel: PreviousHistoryViewModel by viewModels()
    private val setPriceViewModel: SetPriceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreviousHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: PreviousHistoryFragmentArgs by navArgs()
        val currencyId = data.currencyId
        priceViewModel.getAllPreviousHistoriesFilteredByCurrencyId(currencyId)


        priceViewModel.previousHistoriesFilteredLiveData.observe(viewLifecycleOwner) { previousHistoriesList ->
            adapter = PreviousHistoryAdapter(requireContext(), previousHistoriesList)
            binding.recyclerPreviousPriceHistory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recyclerPreviousPriceHistory.adapter = adapter
        }

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerPreviousPriceHistory)
    }

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            val priceHistory = adapter.list[position]
            setPriceViewModel.deletePrice(priceHistory)
        }
    }
}