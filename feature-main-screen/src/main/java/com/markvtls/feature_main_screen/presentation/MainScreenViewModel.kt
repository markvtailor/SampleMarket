package com.markvtls.feature_main_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainScreenViewModel @Inject constructor(
    private val getStock: GetStockInfoUseCase,
    private val getBestSales: GetBestSalesUseCase,
    private val getHotSales: GetHotSalesUseCase,
    private val filterBestSales: FilterSalesUseCase,
    private val searchBestSales: SearchSalesUseCase
) : ViewModel() {

    private var _stock: Flow<StockResponse>? = null
    private val _hotSales = MutableStateFlow<List<HotSale>>(emptyList())
    private val _bestSales = MutableStateFlow<List<BestSale>>(emptyList())
    val hotSales get() = _hotSales
    val bestSales get() = _bestSales
    val stock get() = _stock


    init {
        getMarketStock()
    }

     fun getMarketStock() {
        viewModelScope.launch(Dispatchers.IO) {
            _stock = getStock()
        }
    }

    fun getHot() {
        viewModelScope.launch(Dispatchers.IO) {
                getHotSales().collect {
                    _hotSales.emit(it)
                }
        }
    }

    fun getBest() {
        viewModelScope.launch(Dispatchers.IO) {
                getBestSales().collect {
                    _bestSales.emit(it)
                }
        }
    }

    fun filterBest(filterOption: FilterOption) {
        viewModelScope.launch(Dispatchers.IO) {
                filterBestSales(filterOption).collect {
                    _bestSales.emit(it)
                }
            }
    }

    fun composeFilter(brand: String, price: String, size: String): FilterOption {
        //sizes are not implemented
        var maxPrice = 0L
        var minPrice = 0L
        when(price) {
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
        viewModelScope.launch(Dispatchers.IO) {
            searchBestSales(searchRequest).collect {
                _bestSales.emit(it)
            }
        }

    }
}