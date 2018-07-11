package com.dwarves.template

import com.dwarves.template.di.AppComponent
import com.dwarves.template.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerApplication
import timber.log.Timber

class DwarvesApp : DaggerApplication() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initLogger()
        buildComponent()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO: init fabric here
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    private fun buildComponent() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
    }
}
