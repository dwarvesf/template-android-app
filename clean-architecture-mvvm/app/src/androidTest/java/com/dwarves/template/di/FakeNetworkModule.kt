package {{packageName}}.di

import {{packageName}}.api.FakeProductApi
import {{packageName}}.data.api.ProductApi
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
