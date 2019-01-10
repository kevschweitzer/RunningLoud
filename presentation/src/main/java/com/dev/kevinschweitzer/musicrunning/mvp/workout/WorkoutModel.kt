package com.dev.kevinschweitzer.musicrunning.mvp.workout

class WorkoutModel {

    val MAX_COUNT = 60
    val TWO_DIGITS = "%02d"
    val TIMER_SEPARATOR = ":"
    var hours : Int = 0
    var seconds : Int = 0
    var minutes : Int = 0

    fun getInstantTime(): String{

        seconds ++
        if(seconds == MAX_COUNT ){
            seconds = 0
            minutes ++
        }

        if (minutes == MAX_COUNT){
            minutes = 0
            hours ++
        }

        return  String.format(TWO_DIGITS, hours) + TIMER_SEPARATOR +
                String.format(TWO_DIGITS, minutes) + TIMER_SEPARATOR +
                String.format(TWO_DIGITS, seconds)
    }
}