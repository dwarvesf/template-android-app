package {{packageName}}.di

import android.app.Application
import {{packageName}}.DwarvesApp
import {{packageName}}.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@ApplicationScope
@Component(modules = [AppModule::class, FakeNetworkModule::class])
interface FakeAppComponent : AndroidInjector<DwarvesApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DwarvesApp>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        @Override
        abstract override fun build(): FakeAppComponent
    }
}
