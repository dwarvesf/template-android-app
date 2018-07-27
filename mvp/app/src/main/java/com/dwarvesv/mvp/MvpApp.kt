package com.dwarvesv.mvp

import android.app.Application
import android.content.Context

class MvpApp : Application() {

    companion object {

        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}