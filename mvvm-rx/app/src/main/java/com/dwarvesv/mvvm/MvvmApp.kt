package com.dwarvesv.mvvm

import android.app.Application
import android.content.Context

class MvvmApp : Application() {

    companion object {

        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}