package com.markvtls.feature_main_screen.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.markvtls.feature_main_screen.R
import com.markvtls.feature_main_screen.databinding.BottomSheetContentBinding
import com.markvtls.feature_main_screen.presentation.MainScreenViewModel

/**Bottom sheet for filter UI.*/
internal class FilterBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetContentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainScreenViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetContentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchFilterButton.setOnClickListener {
            val filter = viewModel.composeFilter(
                binding.brandFilter.text.toString(),
                binding.priceFilter.text.toString(),
                binding.sizeFilter.text.toString()
            )
            viewModel.filterBest(filter)
            dismiss()
        }

        binding.cancelFilterButton.setOnClickListener { dismiss() }


    }

    override fun onResume() {
        super.onResume()
        (binding.brandFilter as? MaterialAutoCompleteTextView)?.setSimpleItems(R.array.brands_list)
        (binding.priceFilter as? MaterialAutoCompleteTextView)?.setSimpleItems(R.array.prices_list)
        (binding.sizeFilter as? MaterialAutoCompleteTextView)?.setSimpleItems(R.array.sizes_list)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        const val TAG = "FilterBottomSheet"
    }
}