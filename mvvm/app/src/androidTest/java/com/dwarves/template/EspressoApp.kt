package com.dwarves.template

import com.dwarves.template.di.DaggerFakeAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class EspressoApp : DwarvesApp() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerFakeAppComponent.builder()
                .application(this)
                .create(this)
    }
}