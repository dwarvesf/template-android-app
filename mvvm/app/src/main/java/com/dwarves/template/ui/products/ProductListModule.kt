package com.dwarves.template.ui.products

import android.content.Context
import com.dwarves.template.data.repo.ProductRepo
import com.dwarves.template.di.ActivityScope
import com.dwarves.template.domain.product.GetProductsUseCase
import com.dwarves.template.domain.product.RemoveProductUseCase
import com.dwarves.template.support.Navigator
import com.dwarves.template.support.NavigatorImpl
import com.dwarves.template.support.ResourceProvider
import com.dwarves.template.support.ResourceProviderImpl
import com.dwarves.template.ui.products.list.ProductItemViewModelMapper
import com.dwarves.template.ui.products.list.ProductListAdapter
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
