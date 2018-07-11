package com.dwarves.template.di.module

import com.dwarves.template.data.AuthStorage
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
}
