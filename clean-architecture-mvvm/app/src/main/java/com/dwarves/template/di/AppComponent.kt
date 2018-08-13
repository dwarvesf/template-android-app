package {{packageName}}.di

import android.app.Application
import {{packageName}}.DwarvesApp
import {{packageName}}.di.module.AppModule
import {{packageName}}.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@ApplicationScope
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent : AndroidInjector<DwarvesApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DwarvesApp>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        @Override
        abstract override fun build(): AppComponent
    }
}
