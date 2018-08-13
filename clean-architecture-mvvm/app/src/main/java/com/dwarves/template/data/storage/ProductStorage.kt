package {{packageName}}.data.storage

import android.support.annotation.AnyThread
import android.support.annotation.MainThread
import {{packageName}}.data.model.ProductEntity
import io.reactivex.Completable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

// Can use room or any other storage
// For this example I will use in-mem storage (cache)
class ProductStorage {
    private var products: List<ProductEntity> = emptyList()

    @AnyThread
    fun getProducts(): Single<List<ProductEntity>> {
        return Single.just(products)
    }

    // Try to run this on main-thread to avoid side-effects
    @MainThread
    fun storeProduct(products: List<ProductEntity>): Completable {
        return Completable.fromAction {
            this.products = products
        }
    }

    @MainThread
    fun removeProduct(id: Long): Completable {
        return Completable.fromAction {
            products = products.filter { it.id != id }
        }
    }
}
