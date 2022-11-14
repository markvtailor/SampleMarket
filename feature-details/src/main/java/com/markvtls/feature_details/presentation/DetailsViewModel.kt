package com.markvtls.feature_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markvtls.feature_details.domain.model.StockItemDetails
import com.markvtls.feature_details.domain.use_cases.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getItemDetails: GetDetailsUseCase
) : ViewModel() {

    private var _details: Flow<StockItemDetails>? = null
    val details get() = _details


    fun getDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _details = getItemDetails()
        }
    }
}