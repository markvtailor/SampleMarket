package com.markvtls.feature_main_screen.presentation.fragments


import android.app.Activity
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.forEach
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.markvtls.core.navigation.NavigationActions
import com.markvtls.feature_main_screen.databinding.FragmentMainScreenBinding
import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.presentation.MainScreenViewModel
import com.markvtls.feature_main_screen.presentation.adapters.MainScreenAdapter
import com.markvtls.feature_main_screen.presentation.adapters.base.RecyclerListHorizontalItem
import com.markvtls.feature_main_screen.presentation.adapters.base.RecyclerListVerticalItem
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
internal class MainScreenFragment : Fragment() {


    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainScreenViewModel by viewModels()
    private val adapter = MainScreenAdapter { toItemDetails() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMarketStock()
        viewModel.getHot()
        viewModel.getBest()
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

            viewModel.cartItems?.asLiveData()?.observe(viewLifecycleOwner) {
                if (it > 0) binding.bottomNavigation.getOrCreateBadge(com.markvtls.core.R.id.cartFragment).number = it
            }
            viewModel.stock?.asLiveData()?.observe(viewLifecycleOwner) {
                loadMarketStock(it.hotSale, it.bestSale)
                finishLoading()
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

            bottomNavigation.setOnItemSelectedListener {
                navigate(it)
            }

            categoryButtonsList.forEach { category ->
                (category as LinearLayout).forEach {
                    if (it is ImageButton) it.setOnClickListener {
                        default(categoryButtonsList)
                        mark(category)
                    }
                }
            }

        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun loadMarketStock(hotSales: List<HotSale>, bestSales: List<BestSale>) {
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        adapter.apply { items = listOf(RecyclerListHorizontalItem(hotSales), RecyclerListVerticalItem(bestSales)) }
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
        NavigationActions.toDetailsScreen(findNavController())
    }

    private fun toCart() {
        NavigationActions.toCart(findNavController())
    }


    private fun navigate(destination: MenuItem): Boolean {
        return when (destination.itemId) {
            com.markvtls.core.R.id.cartFragment -> {
                toCart()
                true
            }
            com.markvtls.core.R.id.favoritesFragment -> {
                true
            }
            com.markvtls.core.R.id.userFragment -> {
                true
            }
            else -> false
        }
    }

    private fun finishLoading() {
        with(binding) {
            loading.visibility = View.INVISIBLE
            mainLayout.visibility = View.VISIBLE
            navBar.visibility = View.VISIBLE
        }
    }

    companion object {
        val filterSheet = FilterBottomSheet()
    }


}