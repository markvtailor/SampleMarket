package com.markvtls.feature_main_screen.presentation.adapters.base

import androidx.recyclerview.widget.DiffUtil
import com.markvtls.feature_main_screen.domain.model.StockItem

internal open class BaseDiffUtil: DiffUtil.ItemCallback<StockItem>() {
        override fun areItemsTheSame(oldItem: StockItem, newItem: StockItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        override fun areContentsTheSame(oldItem: StockItem, newItem: StockItem): Boolean {
            return oldItem.equals(newItem)
        }

    }

