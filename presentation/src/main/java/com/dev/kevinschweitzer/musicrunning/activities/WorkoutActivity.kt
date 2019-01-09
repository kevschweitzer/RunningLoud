package com.dev.kevinschweitzer.musicrunning.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dev.kevinschweitzer.musicrunning.R
import com.dev.kevinschweitzer.musicrunning.mvp.workout.WorkoutPresenter
import com.dev.kevinschweitzer.musicrunning.mvp.workout.WorkoutView
import javax.inject.Inject

class WorkoutActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: WorkoutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        presenter.init(WorkoutView(this))
    }

    companion object {
         fun getIntent(): Intent{
             return Intent(this as Context, WorkoutActivity::class.java)
         }
    }
}
