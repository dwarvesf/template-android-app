package com.dwarves.template.data.module

import com.dwarves.template.data.storage.AuthStorage
import com.dwarves.template.data.storage.ProductStorage
import com.dwarves.template.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @ApplicationScope
    @Provides
    fun provideAuthStorage(): AuthStorage {
        return AuthStorage()
    }

    @ApplicationScope
    @Provides
    fun provideProductProductStorage(): ProductStorage {
        return ProductStorage()
    }
}
