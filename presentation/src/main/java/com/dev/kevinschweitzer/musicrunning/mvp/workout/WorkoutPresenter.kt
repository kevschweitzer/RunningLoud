package com.dev.kevinschweitzer.musicrunning.mvp.workout

import com.dev.kevinschweitzer.musicrunning.mvp.base.BasePresenter
import com.dev.kevinschweitzer.musicrunning.mvp.base.BaseView

class WorkoutPresenter: BasePresenter() {

    fun init(view: BaseView) {
        this.view = view
    }
}