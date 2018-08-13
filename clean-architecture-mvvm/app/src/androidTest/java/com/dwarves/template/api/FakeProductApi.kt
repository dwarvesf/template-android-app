package {{packageName}}.api

import {{packageName}}.data.api.ProductApi
import {{packageName}}.data.model.ProductEntity
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.HttpException

// Use to mock api
class FakeProductApi : ProductApi {
    override fun getProducts(): Single<List<ProductEntity>> {
        val products = (1..100).map {
            ProductEntity(it.toLong(), "Title $it", "Description $it")
        }

        return Single.just(products)
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

        return Single.just(id)
    }
}
