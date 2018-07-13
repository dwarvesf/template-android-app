package com.dwarves.template.ui.list.adapter

import android.support.v7.util.DiffUtil

class ProductDiffCallback : DiffUtil.ItemCallback<ProductItemViewModel>() {
    override fun areItemsTheSame(oldItem: ProductItemViewModel, newItem: ProductItemViewModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItemViewModel, newItem: ProductItemViewModel): Boolean {
        return oldItem == newItem
    }
}
