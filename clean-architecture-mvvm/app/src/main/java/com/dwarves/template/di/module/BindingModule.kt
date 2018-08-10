package {{packageName}}.di.module

import {{packageName}}.di.ActivityScope
import {{packageName}}.ui.products.ProductListActivity
import {{packageName}}.ui.products.ProductListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ProductListModule::class])
    abstract fun contributeProductListActivity(): ProductListActivity
}
