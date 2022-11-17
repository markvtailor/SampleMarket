package com.markvtls.feature_main_screen.presentation.adapters


import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.markvtls.core.ui.RecyclerListItem
import com.markvtls.feature_main_screen.databinding.BestSaleItemBinding
import com.markvtls.feature_main_screen.databinding.HorizontalStockItemBinding
import com.markvtls.feature_main_screen.databinding.HotSaleItemBinding
import com.markvtls.feature_main_screen.databinding.VerticalStockItemBinding
import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.presentation.adapters.base.RecyclerListHorizontalItem
import com.markvtls.feature_main_screen.presentation.adapters.base.RecyclerListVerticalItem

/**Adapter Delegates for Main Screen Fragment.*/
internal object MainScreenDelegates {

    fun horizontalRecyclerListItemDelegate() =
        adapterDelegateViewBinding<RecyclerListHorizontalItem, RecyclerListItem, HorizontalStockItemBinding>(
            { inflater, container ->
                HorizontalStockItemBinding.inflate(inflater, container, false).apply {
                    val snapHelper = PagerSnapHelper()
                    snapHelper.attachToRecyclerView(horizontalSalesList)
                }
            }) {

            val adapter = HorizontalItemAdapter()
            binding.horizontalSalesList.adapter = adapter
            bind {
                adapter.apply {
                    items = item.horizontalSales
                }
            }
        }

    fun verticalStockItemDelegate(
        toItemDetails: () -> Unit
    ) =
        adapterDelegateViewBinding<RecyclerListVerticalItem, RecyclerListItem, VerticalStockItemBinding>(
            { inflater, container -> VerticalStockItemBinding.inflate(inflater, container, false) })
        {
            val adapter = VerticalItemAdapter(toItemDetails = toItemDetails)
            binding.verticalSalesList.adapter = adapter
            bind {
                adapter.apply {
                    items = item.verticalSales

                }

            }
        }

    fun hotSaleDelegate() =
        adapterDelegateViewBinding<HotSale, RecyclerListItem, HotSaleItemBinding>(
            { inflate, container -> HotSaleItemBinding.inflate(inflate, container, false) }
        ) {
            bind {

                binding.banner.load(item.picture) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation())
                }
                if (item.isNew == true) binding.bannerNew.visibility = View.VISIBLE

            }
        }

    fun bestSaleDelegate(
        toItemDetails: () -> Unit
    ) = adapterDelegateViewBinding<BestSale, RecyclerListItem, BestSaleItemBinding>(
        { inflate, container -> BestSaleItemBinding.inflate(inflate, container, false) }
    ) {
        bind {

            with(binding) {

                phoneImage.apply {
                    setOnClickListener {
                        toItemDetails()
                    }
                    load(item.picture) {
                        scale(Scale.FIT)
                        crossfade(true)
                    }
                }

                favoritesButton.apply {
                    if (item.isFavorites) {
                        setImageResource(com.markvtls.core.R.drawable.ic_favorite_filled)
                    } else {
                        favoritesButton.setImageResource(com.markvtls.core.R.drawable.ic_favorite)
                    }
                }

                price.text = getString(com.markvtls.core.R.string.price, item.priceWithoutDiscount)
                discountPrice.text = getString(com.markvtls.core.R.string.price, item.discountPrice)
                discountPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                title.text = item.title
            }


        }
    }


}