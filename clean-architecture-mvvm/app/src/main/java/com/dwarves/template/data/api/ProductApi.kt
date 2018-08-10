package {{packageName}}.data.api

import {{packageName}}.data.model.ProductEntity
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

interface ProductApi {
    fun getProducts(): Single<List<ProductEntity>>
    fun removeProduct(id: Long): Single<Long>
}

class ProductApiImpl : ProductApi {
    override fun getProducts(): Single<List<ProductEntity>> {
        val products = (1..100).map {
            ProductEntity(it.toLong(), "Title $it", "Description $it")
        }

        return Single.just(products).delay(2, TimeUnit.SECONDS)
    }

    override fun removeProduct(id: Long): Single<Long> {
        // Sample error
        if (id == 1L) {
            val body: ResponseBody = ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"Error\"}")
            return Single.error(HttpException(retrofit2.Response.error<Any>(body, Response.Builder()
                    .code(400)
                    .message("error")
                    .body(body)
                    .protocol(Protocol.HTTP_1_1)
                    .request(Request.Builder().url("http://localhost/").build())
                    .build())))
        }

        return Single.just(id).delay(1, TimeUnit.SECONDS)
    }
}
