package com.dwarves.template.data.module

import com.dwarves.template.data.api.ProductApi
import com.dwarves.template.data.repo.ProductMapper
import com.dwarves.template.data.repo.ProductRepo
import com.dwarves.template.data.repo.ProductRepoImpl
import com.dwarves.template.data.storage.ProductStorage
import com.dwarves.template.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @ApplicationScope
    @Provides
    fun provideProductRepo(api: ProductApi, storage: ProductStorage): ProductRepo {
        return ProductRepoImpl(api, storage, ProductMapper())
    }
}
