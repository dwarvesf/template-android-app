package com.dwarves.template.ui.products.list

import android.support.v7.util.DiffUtil

class ProductDiffCallback : DiffUtil.ItemCallback<ProductItemViewModel>() {
    override fun areItemsTheSame(oldItem: ProductItemViewModel, newItem: ProductItemViewModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItemViewModel, newItem: ProductItemViewModel): Boolean {
        return oldItem == newItem
    }
}
