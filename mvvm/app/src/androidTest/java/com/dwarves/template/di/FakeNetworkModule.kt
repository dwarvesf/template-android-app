package com.dwarves.template.di

import com.dwarves.template.api.FakeProductApi
import com.dwarves.template.data.api.ProductApi
import dagger.Module
import dagger.Provides

@Module
class FakeNetworkModule {

    @Provides
    @ApplicationScope
    fun provideProductApi(): ProductApi {
        return FakeProductApi()
    }
}
