package com.dwarves.template.ui.list.adapter

import android.os.Parcelable
import com.dwarves.template.domain.model.Product
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductItemViewModel(
        val id: Long,
        val title: String,
        val description: String,
        val loading: Boolean = false
) : Parcelable

fun toProductItems(products: List<Product>): List<ProductItemViewModel> {
    return products.map {
        ProductItemViewModel(it.id, it.title, it.description)
    }
}
