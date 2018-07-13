package com.dwarves.template.di

import android.app.Application
import com.dwarves.template.DwarvesApp
import com.dwarves.template.di.module.AppModule
import com.dwarves.template.data.module.RepoModule
import com.dwarves.template.data.module.StorageModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@ApplicationScope
@Component(modules = [AppModule::class, RepoModule::class, StorageModule::class])
interface AppComponent : AndroidInjector<DwarvesApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DwarvesApp>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        @Override
        abstract override fun build(): AppComponent
    }
}
