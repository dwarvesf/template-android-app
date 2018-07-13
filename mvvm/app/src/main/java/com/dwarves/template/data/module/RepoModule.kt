package com.dwarves.template.data.module

import com.dwarves.template.data.api.ProductApi
import com.dwarves.template.data.repo.ProductMapper
import com.dwarves.template.data.repo.ProductRepo
import com.dwarves.template.data.repo.ProductRepoImpl
import com.dwarves.template.data.storage.ProductStorage
import com.dwarves.template.di.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RepoModule {

    @ApplicationScope
    @Provides
    fun provideProductRepo(retrofit: Retrofit, storage: ProductStorage): ProductRepo {
        // In real world, will use Retrofit to create api
        return ProductRepoImpl(ProductApi(), storage, ProductMapper())
    }
}
