package {{packageName}}.data.module

import {{packageName}}.data.storage.AuthStorage
import {{packageName}}.data.storage.ProductStorage
import {{packageName}}.di.ApplicationScope
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
