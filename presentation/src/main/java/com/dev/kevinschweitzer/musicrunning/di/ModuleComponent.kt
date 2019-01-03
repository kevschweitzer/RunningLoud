package com.dev.kevinschweitzer.musicrunning.di

import com.dev.kevinschweitzer.musicrunning.activities.MainActivity
import com.dev.kevinschweitzer.musicrunning.di.Modules.PresentersModule
import dagger.Component

@Component(modules = [PresentersModule::class])
interface ModuleComponent {

    fun inject(app: MainActivity)
}