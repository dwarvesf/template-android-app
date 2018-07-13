package com.dwarves.template.ui.list

import android.content.Context
import com.dwarves.template.data.repo.ProductRepo
import com.dwarves.template.di.ActivityScope
import com.dwarves.template.domain.product.GetProductsUseCase
import com.dwarves.template.domain.product.RemoveProductUseCase
import com.dwarves.template.support.Navigator
import com.dwarves.template.support.NavigatorImpl
import com.dwarves.template.ui.list.adapter.ProductListAdapter
import dagger.Module
import dagger.Provides

@Module
class ProductListModule {

    @ActivityScope
    @Provides
    fun provideViewModel(
            loadingManager: ProductListLoadingManager,
            getProductsUseCase: GetProductsUseCase,
            removeProductUseCase: RemoveProductUseCase,
            navigator: Navigator
    ): ProductListViewModel {
        return ProductListViewModel(loadingManager, getProductsUseCase, removeProductUseCase, navigator)
    }

    @ActivityScope
    @Provides
    fun provideGetProductsUseCase(productRepo: ProductRepo): GetProductsUseCase {
        return GetProductsUseCase(productRepo)
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
    fun provideRemoveProductUseCase(productRepo: ProductRepo): RemoveProductUseCase {
        return RemoveProductUseCase(productRepo)
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
