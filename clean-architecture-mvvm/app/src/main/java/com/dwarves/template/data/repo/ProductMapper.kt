package {{packageName}}.data.repo

import {{packageName}}.data.model.ProductEntity
import {{packageName}}.domain.model.Product

class ProductMapper {

    fun toProducts(entities: List<ProductEntity>): List<Product> {
        return entities.map {
            Product(it.id, it.title, it.description)
        }
    }
}
