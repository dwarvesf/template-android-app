package {{packageName}}.ui.products

import {{packageName}}.R
import {{packageName}}.data.repo.ProductRepo
import {{packageName}}.domain.model.Product
import {{packageName}}.domain.product.GetProductsUseCase
import {{packageName}}.domain.product.RemoveProductUseCase
import {{packageName}}.support.Navigator
import {{packageName}}.ui.products.list.ProductItemViewModel
import {{packageName}}.ui.products.list.ProductItemViewModelMapper
import {{packageName}}.ui.products.list.ProductListAdapter
import {{packageName}}.util.FakeLoadingManager
import {{packageName}}.util.FakeResourceProvider
import {{packageName}}.util.RxImmediateSchedulerRule
import com.google.gson.Gson
import com.jakewharton.rxrelay2.PublishRelay
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.util.Collections

class ProductListViewModelTest {
    private val loadProductsTrigger = PublishRelay.create<Any>()
    private val pullToRefresh = PublishRelay.create<Any>()
    private val listEvents = PublishRelay.create<Pair<Long, ProductListAdapter.EventType>>()
    private lateinit var productListViewModel: ProductListViewModel

    @Rule
    @JvmField
    val testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var navigator: Navigator
    @Mock
    private lateinit var productRepo: ProductRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        productListViewModel = ProductListViewModel(FakeLoadingManager(), ProductItemViewModelMapper(),
                GetProductsUseCase(productRepo, Gson()), RemoveProductUseCase(productRepo, Gson()),
                FakeResourceProvider(), navigator)
    }

    private fun bind(state: ProductListViewModel.SavedState? = null): ProductListViewModel.Output {
        return productListViewModel.bind(ProductListViewModel.Input(
                state,
                loadProductsTrigger,
                pullToRefresh,
                listEvents
        ))
    }

    private fun mockError(): HttpException {
        val body: ResponseBody = ResponseBody.create(MediaType.parse("application/json"), "{\"error\":\"Error\"}")
        return HttpException(retrofit2.Response.error<Any>(body, Response.Builder()
                .code(400)
                .message("error")
                .body(body)
                .protocol(Protocol.HTTP_1_1)
                .request(Request.Builder().url("http://localhost/").build())
                .build()))
    }

    private fun mockProducts() {
        whenever(productRepo.getProducts()).thenReturn(Observable.just(
                listOf(
                        Product(1, "title 1", "des 1"),
                        Product(2, "title 2", "des 2"),
                        Product(3, "title 3", "des 3")
                )
        ))
    }

    @Test
    fun getTitle() {
        mockProducts()

        val output = bind()
        val title = output.title.test()
        loadProductsTrigger.accept(1)

        title.assertValues(
                "${R.string.items} 0",
                "${R.string.items} 3"
        ).dispose()
    }

    @Test
    fun loadProducts_whenFirstOpen_shouldLoadProducts() {
        mockProducts()

        val output = bind()
        val products = output.products.test()
        loadProductsTrigger.accept(1)

        products.assertValues(emptyList(), listOf(
                ProductItemViewModel(1, "title 1", "des 1"),
                ProductItemViewModel(2, "title 2", "des 2"),
                ProductItemViewModel(3, "title 3", "des 3")
        )).dispose()
    }

    @Test
    fun loadProducts_whenLoadError_shouldShowError() {
        whenever(productRepo.getProducts()).thenReturn(Observable.error(mockError()))

        val output = bind()
        val products = output.products.test()
        val errors = output.messages.test()
        loadProductsTrigger.accept(1)

        products.assertValues(emptyList())
                .dispose()
        errors.assertValue("Error")
                .dispose()
    }

    @Test
    fun loadProducts_whenHasSavedState_shouldNotLoadProducts() {
        mockProducts()

        val output = bind(ProductListViewModel.SavedState(Collections.singletonList(
                ProductItemViewModel(1, "title 1", "des 1")
        )))
        val products = output.products.test()
        loadProductsTrigger.accept(1)

        products.assertValues(
                listOf(ProductItemViewModel(1, "title 1", "des 1"))
        ).dispose()
    }

    @Test
    fun loadProducts_whenPullToRefreshCalled_shouldReloadProducts() {
        mockProducts()

        val output = bind(ProductListViewModel.SavedState(listOf(
                ProductItemViewModel(1, "title 1", "des 1")
        )))
        val products = output.products.test()
        loadProductsTrigger.accept(1)
        pullToRefresh.accept(1)

        products.assertValues(
                listOf(
                        ProductItemViewModel(1, "title 1", "des 1")
                ),
                listOf(
                        ProductItemViewModel(1, "title 1", "des 1"),
                        ProductItemViewModel(2, "title 2", "des 2"),
                        ProductItemViewModel(3, "title 3", "des 3")
                )
        ).dispose()
    }

    @Test
    fun createSavedState() {
        mockProducts()

        bind()
        loadProductsTrigger.accept(1)

        assertEquals(listOf(
                ProductItemViewModel(1, "title 1", "des 1"),
                ProductItemViewModel(2, "title 2", "des 2"),
                ProductItemViewModel(3, "title 3", "des 3")
        ), productListViewModel.createSavedState().products)
    }

    @Test
    fun openProductDetail() {
        mockProducts()
        whenever(navigator.toProductDetail(1)).thenReturn(Completable.complete())

        bind()
        loadProductsTrigger.accept(1)
        listEvents.accept(Pair(1, ProductListAdapter.EventType.CLICK))

        verify(navigator).toProductDetail(1)
    }

    @Test
    fun removeProduct_whenRemoveSuccess_shouldUpdateList() {
        mockProducts()
        whenever(productRepo.removeProduct(1)).thenReturn(Single.just(1))

        val output = bind()
        val products = output.products.test()
        loadProductsTrigger.accept(1)
        listEvents.accept(Pair(1, ProductListAdapter.EventType.REMOVE))

        products.assertValues(
                emptyList(),
                listOf(
                        ProductItemViewModel(1, "title 1", "des 1"),
                        ProductItemViewModel(2, "title 2", "des 2"),
                        ProductItemViewModel(3, "title 3", "des 3")
                ),
                listOf(
                        ProductItemViewModel(1, "title 1", "des 1", true),
                        ProductItemViewModel(2, "title 2", "des 2"),
                        ProductItemViewModel(3, "title 3", "des 3")
                ),
                listOf(
                        ProductItemViewModel(2, "title 2", "des 2"),
                        ProductItemViewModel(3, "title 3", "des 3")
                )
        ).dispose()
    }

    @Test
    fun removeProduct_whenRemoveFailed_shouldRefreshList() {
        mockProducts()
        whenever(productRepo.removeProduct(1)).thenReturn(Single.error(mockError()))

        val output = bind()
        val products = output.products.test()
        val errors = output.messages.test()
        loadProductsTrigger.accept(1)
        listEvents.accept(Pair(1, ProductListAdapter.EventType.REMOVE))

        products.assertValues(
                emptyList(),
                listOf(
                        ProductItemViewModel(1, "title 1", "des 1"),
                        ProductItemViewModel(2, "title 2", "des 2"),
                        ProductItemViewModel(3, "title 3", "des 3")
                ),
                listOf(
                        ProductItemViewModel(1, "title 1", "des 1", true),
                        ProductItemViewModel(2, "title 2", "des 2"),
                        ProductItemViewModel(3, "title 3", "des 3")
                ),
                listOf(
                        ProductItemViewModel(1, "title 1", "des 1"),
                        ProductItemViewModel(2, "title 2", "des 2"),
                        ProductItemViewModel(3, "title 3", "des 3")
                )
        ).dispose()
        errors.assertValue("Error")
                .dispose()
    }

    @After
    fun tearDown() {
        productListViewModel.unbind()
    }
}
