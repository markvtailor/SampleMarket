package com.markvtls.feature_main_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.FilterOption
import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.domain.model.StockResponse
import com.markvtls.feature_main_screen.domain.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**ViewModel for Main Screen Fragment.*/
@HiltViewModel
internal class MainScreenViewModel @Inject constructor(
    private val getStock: GetStockInfoUseCase,
    private val getBestSales: GetBestSalesUseCase,
    private val getHotSales: GetHotSalesUseCase,
    private val filterBestSales: FilterSalesUseCase,
    private val searchBestSales: SearchSalesUseCase,
    private val getCartItemsInfo: GetCartItemsInfoUseCase
) : ViewModel() {

    private var _stock: Flow<StockResponse>? = null
    private val _hotSales = MutableStateFlow<List<HotSale>>(emptyList())
    private val _bestSales = MutableStateFlow<List<BestSale>>(emptyList())
    private var _cartItems: Flow<Int>? = null


    val hotSales get() = _hotSales
    val bestSales get() = _bestSales
    val stock get() = _stock
    val cartItems get() = _cartItems


    init {
        getCartItemsQuantity()
        getMarketStock()
    }

    fun getMarketStock() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _stock = getStock()
            } catch (e: Exception) {
                println(e)
            }

        }
    }

    fun getHot() {
        viewModelScope.launch {

            getHotSales().collect {
                _hotSales.emit(it)
            }
        }
    }

    fun getBest() {
        viewModelScope.launch {
            getBestSales().collect {
                _bestSales.emit(it)
            }
        }
    }

    fun filterBest(filterOption: FilterOption) {
        viewModelScope.launch {
            filterBestSales(filterOption).collect {
                _bestSales.emit(it)
            }
        }
    }

    fun composeFilter(brand: String, price: String, size: String): FilterOption {
        //sizes are not implemented
        var maxPrice = 0L
        var minPrice = 0L
        when (price) {
            "$0 - $300" -> {
                minPrice = 0
                maxPrice = 300
            }
            "$300 - $500" -> {
                minPrice = 300
                maxPrice = 500
            }
            "$500 - $1000" -> {
                minPrice = 500
                maxPrice = 1000
            }
            "$1000 - $5000" -> {
                minPrice = 1000
                maxPrice = 5000
            }
            "$5000 - $10000" -> {
                minPrice = 5000
                maxPrice = 10000
            }
        }
        return when {
            brand.isEmpty() && price.isEmpty() && size.isEmpty() -> FilterOption()
            brand.isEmpty() && price.isEmpty() -> FilterOption() //sizes are not implemented
            brand.isEmpty() -> FilterOption(maxPrice = maxPrice, minPrice = minPrice)
            else -> FilterOption(brand, maxPrice = maxPrice, minPrice = minPrice)
        }


    }

    fun searchSales(searchRequest: String) {
        viewModelScope.launch {
            searchBestSales(searchRequest).collect {
                _bestSales.emit(it)
            }
        }

    }

    private fun getCartItemsQuantity() {
        viewModelScope.launch(Dispatchers.IO) {
            _cartItems = getCartItemsInfo()
        }
    }
}