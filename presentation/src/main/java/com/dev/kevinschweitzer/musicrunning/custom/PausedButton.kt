package com.dev.kevinschweitzer.musicrunning.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import com.dev.kevinschweitzer.musicrunning.R
import kotlinx.android.synthetic.main.activity_workout.view.*

class PausedButton(context: Context, attrs: AttributeSet) : Button(context, attrs) {

    val TRUE = 1
    val FALSE = 0
    var status: Int = 0

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PausedButton,
            0, 0).apply {

            try {
                status = getInteger(R.styleable.PausedButton_status, 0)
            } finally {
                recycle()
            }
        }
    }

    fun isPaused(): Boolean {
        return status != 0
    }

    fun pause(){
        status = TRUE
        pauseButton.text = context.getString(R.string.pause_button_text)
        invalidate()
        requestLayout()
    }

    fun resume(){
        status = FALSE
        pauseButton.text = context.getString(R.string.resume_button_text)
        invalidate()
        requestLayout()
    }

}