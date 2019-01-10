package com.dev.kevinschweitzer.musicrunning.di.Modules

import com.dev.kevinschweitzer.musicrunning.mvp.workout.WorkoutModel
import dagger.Module
import dagger.Provides

@Module
class ModelModule {

    @Provides
    fun providesWorkoutModel() = WorkoutModel()
}