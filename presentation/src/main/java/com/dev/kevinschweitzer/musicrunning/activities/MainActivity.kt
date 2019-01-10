package com.dev.kevinschweitzer.musicrunning.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dev.kevinschweitzer.musicrunning.R
import com.dev.kevinschweitzer.musicrunning.di.DaggerModuleComponent
import com.dev.kevinschweitzer.musicrunning.mvp.main.MainPresenter
import com.dev.kevinschweitzer.musicrunning.mvp.main.MainView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        //Back to original theme after splash screen
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Automatically created by compilation using Component class name
        DaggerModuleComponent.create().injectMain(this)
        presenter.init(MainView(this))
    }
}
