package com.dev.kevinschweitzer.musicrunning.di

import com.dev.kevinschweitzer.musicrunning.activities.MainActivity
import com.dev.kevinschweitzer.musicrunning.activities.WorkoutActivity
import com.dev.kevinschweitzer.musicrunning.di.Modules.ModelModule
import com.dev.kevinschweitzer.musicrunning.di.Modules.PresentersModule
import dagger.Component

@Component(modules = [PresentersModule::class, ModelModule::class])
interface ModuleComponent {

    fun injectMain(app: MainActivity)

    fun injectWorkout(activity: WorkoutActivity)
}