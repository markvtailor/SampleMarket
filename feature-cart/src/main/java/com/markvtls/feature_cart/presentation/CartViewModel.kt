package com.markvtls.feature_cart.presentation

import androidx.lifecycle.ViewModel
import com.markvtls.feature_cart.domain.model.CartInfo
import com.markvtls.feature_cart.domain.use_cases.GetCartInfoUseCase
import com.markvtls.feature_cart.domain.use_cases.GetCartItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
internal class CartViewModel @Inject constructor(
    private val getCartInfo: GetCartInfoUseCase,
    private val getCartItems: GetCartItemsUseCase
) : ViewModel() {

    private var _cart: Flow<CartInfo>? = null
    val cart get() = _cart


    suspend fun getCart() {
        _cart = getCartInfo()
    }
}