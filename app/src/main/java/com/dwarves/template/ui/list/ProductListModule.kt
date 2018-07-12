package com.dwarves.template.ui.list

import com.dwarves.template.di.ActivityScope
import com.dwarves.template.domain.product.GetProductsUseCase
import com.dwarves.template.domain.product.RemoveProductUseCase
import com.dwarves.template.ui.list.adapter.ProductListAdapter
import dagger.Module
import dagger.Provides

@Module
class ProductListModule {

    @ActivityScope
    @Provides
    fun provideViewModel(loadingManager: ProductListLoadingManager): ProductListViewModel {
        return ProductListViewModel(loadingManager, GetProductsUseCase(), RemoveProductUseCase())
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
