package {{packageName}}

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class MvvmApp : Application() {

    companion object {

        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        {{#fabricEnabled}}
        Fabric.with(this, Crashlytics())
        {{/fabricEnabled}}
    }

}