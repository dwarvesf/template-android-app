package {{packageName}}.ui.products.list

import {{packageName}}.domain.model.Product

class ProductItemViewModelMapper {

    fun toItems(products: List<Product>): List<ProductItemViewModel> {
        return products.map {
            ProductItemViewModel(it.id, it.title, it.description)
        }
    }
}
