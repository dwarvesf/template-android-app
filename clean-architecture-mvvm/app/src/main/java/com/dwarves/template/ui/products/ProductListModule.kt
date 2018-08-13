package {{packageName}}.ui.products

import android.content.Context
import {{packageName}}.data.repo.ProductRepo
import {{packageName}}.di.ActivityScope
import {{packageName}}.domain.product.GetProductsUseCase
import {{packageName}}.domain.product.RemoveProductUseCase
import {{packageName}}.support.Navigator
import {{packageName}}.support.NavigatorImpl
import {{packageName}}.support.ResourceProvider
import {{packageName}}.support.ResourceProviderImpl
import {{packageName}}.ui.products.list.ProductItemViewModelMapper
import {{packageName}}.ui.products.list.ProductListAdapter
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class ProductListModule {

    @ActivityScope
    @Provides
    fun provideViewModel(
            loadingManager: ProductListLoadingManager,
            productItemViewModelMapper: ProductItemViewModelMapper,
            getProductsUseCase: GetProductsUseCase,
            removeProductUseCase: RemoveProductUseCase,
            resourceProvider: ResourceProvider,
            navigator: Navigator
    ): ProductListViewModel {
        return ProductListViewModel(loadingManager, productItemViewModelMapper, getProductsUseCase,
                removeProductUseCase, resourceProvider, navigator)
    }

    @ActivityScope
    @Provides
    fun provideProductItemViewModelMapper(): ProductItemViewModelMapper {
        return ProductItemViewModelMapper()
    }

    @ActivityScope
    @Provides
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

    @ActivityScope
    @Provides
    fun provideGetProductsUseCase(productRepo: ProductRepo, gson: Gson): GetProductsUseCase {
        return GetProductsUseCase(productRepo, gson)
    }

    @ActivityScope
    @Provides
    fun provideContext(activity: ProductListActivity): Context {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideNavigator(context: Context): Navigator {
        return NavigatorImpl(context)
    }

    @ActivityScope
    @Provides
    fun provideRemoveProductUseCase(productRepo: ProductRepo, gson: Gson): RemoveProductUseCase {
        return RemoveProductUseCase(productRepo, gson)
    }

    @ActivityScope
    @Provides
    fun provideProductListAdapter(): ProductListAdapter {
        return ProductListAdapter()
    }

    @ActivityScope
    @Provides
    fun provideProductListLoadingManager(activity: ProductListActivity): ProductListLoadingManager {
        return ProductListLoadingManager(activity)
    }
}
