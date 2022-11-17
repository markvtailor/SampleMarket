package com.markvtls.feature_cart.presentation

import androidx.lifecycle.ViewModel
import com.markvtls.feature_cart.domain.model.CartInfo
import com.markvtls.feature_cart.domain.use_cases.GetCartInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
/**ViewModel for Cart Fragment.*/
internal class CartViewModel @Inject constructor(
    private val getCartInfo: GetCartInfoUseCase
) : ViewModel() {

    private var _cart: Flow<CartInfo>? = null
    val cart get() = _cart


    /**Implementing GetCartInfo UseCase.*/
    suspend fun getCart() {
        _cart = getCartInfo()
    }
}