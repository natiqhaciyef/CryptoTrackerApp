package com.natiqhaciyef.cryptotrackerapp.view.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.natiqhaciyef.cryptotrackerapp.R
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import com.natiqhaciyef.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.databinding.AlertDialogSetPriceBinding
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.SetPriceViewModel

class AlertDialogSetPrice(
    val currencyModel: ExtendedCurrencyModel,
) : BottomSheetDialogFragment() {
    private lateinit var binding: AlertDialogSetPriceBinding
    private lateinit var viewModel: SetPriceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlertDialogSetPriceBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SetPriceViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleText.text = "Set price for ${currencyModel.title}"
        binding.setPriceButton.setOnClickListener {
            val maxPrice = binding.setPriceInputMax.text.toString().toDouble()
            val minPrice = binding.setPriceInputMin.text.toString().toDouble()

            viewModel.insertPrice(
                PriceModel(
                    id = 0,
                    minPrice = minPrice,
                    maxPrice = maxPrice,
                    currencyId = currencyModel.title.lowercase(),
                    currencyName = currencyModel.title,
                    currencyImage = ""
                )
            )

            findNavController().navigate(R.id.homeFragment)
        }

        binding.checkHistory.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToPreviousHistoryFragment(currencyModel.title.lowercase())
            findNavController().navigate(action)
        }
    }
}