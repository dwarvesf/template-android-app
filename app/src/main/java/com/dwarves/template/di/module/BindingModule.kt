package com.dwarves.template.di.module

import android.app.ListActivity
import com.dwarves.template.di.ActivityScope
import com.dwarves.template.ui.list.ListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ListModule::class])
    abstract fun contributeListActivity(): ListActivity
}
