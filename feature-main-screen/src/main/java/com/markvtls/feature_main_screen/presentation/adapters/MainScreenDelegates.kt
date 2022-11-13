package com.markvtls.feature_main_screen.presentation.adapters


import android.graphics.Paint
import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.markvtls.feature_main_screen.R
import com.markvtls.feature_main_screen.databinding.BestSaleItemBinding
import com.markvtls.feature_main_screen.databinding.HorizontalStockItemBinding
import com.markvtls.feature_main_screen.databinding.HotSaleItemBinding
import com.markvtls.feature_main_screen.databinding.VerticalStockItemBinding
import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.domain.model.StockItem
import com.markvtls.feature_main_screen.presentation.adapters.base.StockHorizontalItem
import com.markvtls.feature_main_screen.presentation.adapters.base.StockVerticalItem


internal object MainScreenDelegates {

    val horizontalStockItemDelegate = adapterDelegateViewBinding<StockHorizontalItem, StockItem, HorizontalStockItemBinding>(
        {inflater, container -> HorizontalStockItemBinding.inflate(inflater, container, false).apply {
            horizontalSalesList.adapter = horizontalStockAdapter
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(horizontalSalesList)
        } } ) {
        bind {

            (binding.horizontalSalesList.adapter as ListDelegationAdapter<List<StockItem>>).apply {

                items = item.horizontalSales
                notifyDataSetChanged()
            }
        }
    }

    val verticalStockItemDelegate = adapterDelegateViewBinding<StockVerticalItem, StockItem, VerticalStockItemBinding>(
        {inflater, container -> VerticalStockItemBinding.inflate(inflater, container, false).apply {
            verticalSalesList.adapter = verticalStockAdapter

        } } ) {
        bind {
            (binding.verticalSalesList.adapter as ListDelegationAdapter<List<StockItem>>).apply {
                items = item.verticalSales
                notifyDataSetChanged()
            }
        }
    }

     private val hotSaleDelegate = adapterDelegateViewBinding<HotSale, StockItem, HotSaleItemBinding>(
        {inflate, container -> HotSaleItemBinding.inflate(inflate,container, false)}
    ) {
        bind {

            binding.banner.load(item.picture) {
                crossfade(true)
                transformations(RoundedCornersTransformation())
            }
            if (item.isNew == true) binding.bannerNew.visibility = View.VISIBLE

        }
    }

    private val bestSaleDelegate = adapterDelegateViewBinding<BestSale, StockItem, BestSaleItemBinding>(
        {inflate, container -> BestSaleItemBinding.inflate(inflate,container, false)}
    ) {
        bind {

            with(binding) {
                phoneImage.load(item.picture) {
                    scale(Scale.FIT)
                    crossfade(true)
                }
                favoritesButton.apply {
                    val image = if (item.isFavorites) R.drawable.ic_favorite_filled else R.drawable.ic_favorite
                    setImageResource(image)
                    setOnClickListener {
                        val actualImage = if (item.isFavorites) R.drawable.ic_favorite else R.drawable.ic_favorite_filled
                        setImageResource(actualImage)
                    }
                }

                price.text = getString(com.markvtls.core.R.string.price,item.priceWithoutDiscount)
                discountPrice.text = getString(com.markvtls.core.R.string.price,item.discountPrice)
                discountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                title.text = item.title
            }


        }
    }


    private val horizontalStockAdapter = ListDelegationAdapter(
        hotSaleDelegate
    )

    private val verticalStockAdapter = ListDelegationAdapter(
        bestSaleDelegate
    )
}