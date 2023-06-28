package com.natiqhaciyef.cryptotrackerapp.view.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import com.natiqhaciyef.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.natiqhaciyef.cryptotrackerapp.databinding.FragmentHomeBinding
import com.natiqhaciyef.cryptotrackerapp.view.adapter.CurrencyAdapter
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cryptoLiveData.observe(viewLifecycleOwner) { currencies ->

            val adapter = CurrencyAdapter(requireContext(), currencies)
            binding.recyclerCurrency.adapter = adapter
            binding.recyclerCurrency.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            adapter.onItemClick = {
                val bottomSheet = AlertDialogSetPrice(currencyModel = it)
                bottomSheet.show(childFragmentManager, "BottomSheetDialogFragment")
            }
        }
    }
}