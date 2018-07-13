package com.dwarves.template.data.repo

import com.dwarves.template.data.model.ProductEntity
import com.dwarves.template.domain.model.Product

class ProductMapper {

    fun toProducts(entities: List<ProductEntity>): List<Product> {
        return entities.map {
            Product(it.id, it.title, it.description)
        }
    }
}
