package com.dev.kevinschweitzer.musicrunning.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dev.kevinschweitzer.musicrunning.R
import com.dev.kevinschweitzer.musicrunning.di.DaggerModuleComponent
import com.dev.kevinschweitzer.musicrunning.mvp.workout.WorkoutPresenter
import com.dev.kevinschweitzer.musicrunning.mvp.workout.WorkoutView
import javax.inject.Inject

class WorkoutActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: WorkoutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        DaggerModuleComponent.create().injectWorkout(this)
        presenter.init(WorkoutView(this))
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    companion object {
         fun getIntent(context: Context): Intent{
             return Intent(context, WorkoutActivity::class.java)
         }
    }
}
