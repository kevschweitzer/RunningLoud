package com.dev.kevinschweitzer.musicrunning.mvp.base

import java.io.Serializable

abstract class BasePresenter: Serializable {

     lateinit var view: BaseView
}