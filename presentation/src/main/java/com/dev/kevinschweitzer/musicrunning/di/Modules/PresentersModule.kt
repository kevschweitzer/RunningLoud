package com.dev.kevinschweitzer.musicrunning.di.Modules

import com.dev.kevinschweitzer.musicrunning.mvp.main.MainPresenter
import com.dev.kevinschweitzer.musicrunning.mvp.workout.WorkoutPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentersModule {

    @Provides
    open fun providesMainPresenter() = MainPresenter()

    @Provides
    open fun providesWorkoutPresenter() = WorkoutPresenter()
}