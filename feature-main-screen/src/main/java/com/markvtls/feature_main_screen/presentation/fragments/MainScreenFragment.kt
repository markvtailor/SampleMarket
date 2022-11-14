package com.markvtls.feature_main_screen.presentation.fragments


import android.app.Activity
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.forEach
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.markvtls.feature_main_screen.R
import com.markvtls.feature_main_screen.databinding.FragmentMainScreenBinding
import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.presentation.MainScreenViewModel
import com.markvtls.feature_main_screen.presentation.adapters.MainScreenAdapter
import com.markvtls.feature_main_screen.presentation.adapters.MainScreenDelegates
import com.markvtls.feature_main_screen.presentation.adapters.base.StockHorizontalItem
import com.markvtls.feature_main_screen.presentation.adapters.base.StockVerticalItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
internal class MainScreenFragment : Fragment() {


    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainScreenViewModel by viewModels()
    private val adapter = MainScreenAdapter { toItemDetails() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMarketStock()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            mark(categoryPhonesLayout)

            categoryButtonsList.forEach { category ->
                (category as LinearLayout).forEach {
                    if (it is ImageButton) it.setOnClickListener {
                        default(categoryButtonsList)
                        mark(category)
                    }
                }
            }


            viewModel.stock?.asLiveData()?.observe(viewLifecycleOwner) {
                loadHotSales(it.hotSale, it.bestSale)
                viewModel.getHot()
                viewModel.getBest()
            }

            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                viewModel.bestSales.asLiveData().observe(viewLifecycleOwner) { best ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.hotSales.collect { hot ->
                            loadHotSales(hot, best)
                        }
                    }
                }
            }

            filter.setOnClickListener {
                filterSheet.show(childFragmentManager, FilterBottomSheet.TAG)
            }

            searchField.doOnTextChanged { text, _, _, _ ->
                viewModel.searchSales(text.toString())
            }

            searchField.setOnFocusChangeListener { view, hasFocus ->
                if (!hasFocus) hideKeyboard(view)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadHotSales(hotSales: List<HotSale>, bestSales: List<BestSale>) {

        val recyclerView = binding.recyclerview

        recyclerView.adapter = adapter
        adapter.apply {
            items = listOf(StockHorizontalItem(hotSales), StockVerticalItem(bestSales))

        }

    }


    private fun default(categoryList: LinearLayout) {
        categoryList.forEach { category ->
            (category as LinearLayout).forEach {
                when (it) {
                    is ImageButton -> it.backgroundTintList = ColorStateList.valueOf(resources.getColor(com.markvtls.core.R.color.white))
                    is TextView -> it.setTextColor(resources.getColor(com.markvtls.core.R.color.dark_blue))
                }
            }
        }
    }

    private fun mark(category: LinearLayout) {
        category.forEach {
            when (it) {
                is ImageButton -> it.backgroundTintList = ColorStateList.valueOf(resources.getColor(com.markvtls.core.R.color.orange))
                is TextView -> it.setTextColor(resources.getColor(com.markvtls.core.R.color.orange))
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }

     private fun toItemDetails() {
        findNavController().navigate(com.markvtls.core.R.id.action_global_detailsFragment)
    }
    companion object {
        val filterSheet = FilterBottomSheet()
    }
}