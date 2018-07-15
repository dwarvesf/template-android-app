package com.dwarves.template.ui.products.list

import com.dwarves.template.domain.model.Product

class ProductItemViewModelMapper {

    fun toItems(products: List<Product>): List<ProductItemViewModel> {
        return products.map {
            ProductItemViewModel(it.id, it.title, it.description)
        }
    }
}
