package com.dwarves.template.di.module

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.view.inputmethod.InputMethodManager
import com.dwarves.template.data.module.RepoModule
import com.dwarves.template.data.module.StorageModule
import com.dwarves.template.di.ApplicationScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [
    BindingModule::class,
    AndroidSupportInjectionModule::class,
    RepoModule::class, StorageModule::class
])
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @ApplicationScope
    fun provideInputMethodManager(application: Application): InputMethodManager {
        return application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    @Provides
    @ApplicationScope
    fun providePackageManager(application: Application): PackageManager {
        return application.packageManager
    }
}
