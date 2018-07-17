package com.dwarves.template

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner

class EspressoRunner : AndroidJUnitRunner() {

    override fun newApplication(classLoader: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(classLoader, EspressoApp::class.java.name, context)
    }
}
