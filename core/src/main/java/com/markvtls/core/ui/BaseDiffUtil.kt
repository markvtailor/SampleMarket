package com.markvtls.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.markvtls.core.ui.RecyclerListItem

open class BaseDiffUtil: DiffUtil.ItemCallback<RecyclerListItem>() {
        override fun areItemsTheSame(oldItem: RecyclerListItem, newItem: RecyclerListItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        override fun areContentsTheSame(oldItem: RecyclerListItem, newItem: RecyclerListItem): Boolean {
            return oldItem.equals(newItem)
        }

    }

