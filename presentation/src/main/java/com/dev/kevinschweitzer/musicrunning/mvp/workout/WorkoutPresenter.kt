package com.dev.kevinschweitzer.musicrunning.mvp.workout

import android.widget.Button
import com.dev.kevinschweitzer.musicrunning.custom.PausedButton
import com.dev.kevinschweitzer.musicrunning.fragments.StopConfirmationDialog
import com.dev.kevinschweitzer.musicrunning.mvp.base.BasePresenter
import com.dev.kevinschweitzer.musicrunning.mvp.base.BaseView
import kotlinx.android.synthetic.main.activity_workout.*
import java.util.*
import kotlin.concurrent.timerTask

class WorkoutPresenter(workoutModel: WorkoutModel): BasePresenter() {

    val ONE_SECOND: Long = 1000
    var model: WorkoutModel
    lateinit var timer: Timer
    lateinit var pauseButton: PausedButton
    lateinit var stopButton: Button

    init {
        model = workoutModel
    }

    fun init(view: BaseView) {
        this.view = view
        initButtons()
        startTimer()
    }

    private fun initButtons() {
        pauseButton = view.activity.pauseButton
        stopButton = view.activity.stopButton

        stopButton.setOnClickListener {
            timer.cancel()
            stopWorkout()
        }
        pauseButton.setOnClickListener {
            if(!pauseButton.isPaused()){
                pauseButton.pause()
                timer.cancel()
            } else {
                pauseButton.resume()
                startTimer()
            }
        }
    }

    fun startTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(timerTask {
            view.activity.timerTextView.text = model.getInstantTime()
        }, ONE_SECOND, ONE_SECOND)
    }

    fun onBackPressed() {
        stopWorkout()
    }

    private fun stopWorkout() {
        StopConfirmationDialog.newInstance(this).show(view.activity.supportFragmentManager, " ")
    }
}