package com.dev.kevinschweitzer.musicrunning.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.dev.kevinschweitzer.musicrunning.R
import com.dev.kevinschweitzer.musicrunning.mvp.workout.WorkoutPresenter


class StopConfirmationDialog: DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val presenter = arguments?.getSerializable(PRESENTER_KEY) as WorkoutPresenter
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.stop_confirmation_message).setPositiveButton(R.string.confirm_stop){ dialog, which ->
            activity?.finish()
        }.setNegativeButton(R.string.decline_stop){ dialog, which ->
            dialog.dismiss()
            presenter?.startTimer()
        }
        return builder.create()
    }

    companion object {
        val PRESENTER_KEY = "PRESENTER"

        fun newInstance(workoutPresenter: WorkoutPresenter): StopConfirmationDialog {
            val fragment = StopConfirmationDialog()
            val args = Bundle()
            args.putSerializable(PRESENTER_KEY,workoutPresenter)
            fragment.arguments = args
            return fragment
        }
    }

}