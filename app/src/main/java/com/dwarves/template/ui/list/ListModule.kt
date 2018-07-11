package com.dwarves.template.ui.list

import com.dwarves.template.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @ActivityScope
    @Provides
    fun provideViewModel(): ListViewModel {
        return ListViewModel()
    }
}
