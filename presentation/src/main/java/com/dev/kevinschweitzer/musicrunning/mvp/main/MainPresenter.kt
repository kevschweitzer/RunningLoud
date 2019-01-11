package com.dev.kevinschweitzer.musicrunning.mvp.main

import android.content.Intent
import com.dev.kevinschweitzer.musicrunning.activities.MapActivity
import com.dev.kevinschweitzer.musicrunning.activities.WorkoutActivity
import com.dev.kevinschweitzer.musicrunning.mvp.base.BasePresenter
import com.dev.kevinschweitzer.musicrunning.mvp.base.BaseView
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter: BasePresenter(){

    fun init(view: BaseView) {
        this.view = view
        view.activity.start_workout_btn.setOnClickListener {
            view.activity.startActivity(WorkoutActivity.getIntent(view.activity))
        }

        view.activity.button.setOnClickListener {
            val intent = Intent(view.activity, MapActivity::class.java)
            view.activity.startActivity(intent)
        }
    }

}