package com.dev.kevinschweitzer.musicrunning.di.Modules

import com.dev.kevinschweitzer.musicrunning.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {

    @Provides
    open fun providesMainPresenter(): MainPresenter {
        return MainPresenter()
    }
}