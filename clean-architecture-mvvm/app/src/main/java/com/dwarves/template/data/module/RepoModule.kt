package {{packageName}}.data.module

import {{packageName}}.data.api.ProductApi
import {{packageName}}.data.repo.ProductMapper
import {{packageName}}.data.repo.ProductRepo
import {{packageName}}.data.repo.ProductRepoImpl
import {{packageName}}.data.storage.ProductStorage
import {{packageName}}.di.ApplicationScope
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
