package com.dev.kevinschweitzer.musicrunning.mvp.main

import com.dev.kevinschweitzer.musicrunning.activities.WorkoutActivity
import com.dev.kevinschweitzer.musicrunning.mvp.base.BasePresenter
import com.dev.kevinschweitzer.musicrunning.mvp.base.BaseView
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter: BasePresenter(){

    fun init(view: BaseView) {
        this.view = view
        view.activity.start_workout_btn.setOnClickListener(){
            view.activity.startActivity(WorkoutActivity.getIntent(view.activity))
        }
    }

}