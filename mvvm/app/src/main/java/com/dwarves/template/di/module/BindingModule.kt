package com.dwarves.template.di.module

import com.dwarves.template.di.ActivityScope
import com.dwarves.template.ui.products.ProductListActivity
import com.dwarves.template.ui.products.ProductListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ProductListModule::class])
    abstract fun contributeProductListActivity(): ProductListActivity
}
