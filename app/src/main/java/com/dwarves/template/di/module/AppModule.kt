package com.dwarves.template.di.module

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.view.inputmethod.InputMethodManager
import com.dwarves.template.di.ApplicationScope
import com.dwarves.template.support.ResourceProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [
    NetworkModule::class,
    StorageModule::class,
    BindingModule::class,
    AndroidSupportInjectionModule::class
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

    @Provides
    @ApplicationScope
    fun provideResourceProvider(application: Application): ResourceProvider {
        return ResourceProvider(application)
    }
}
