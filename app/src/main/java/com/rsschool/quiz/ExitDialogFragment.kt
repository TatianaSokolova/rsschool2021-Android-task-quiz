package com.rsschool.quiz

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ExitDialogFragment: DialogFragment() {
    private val mAnswers = Constants.answers
    private val mPositions = Constants.checkedPositions

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.setMessage("Exit App?")
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        mAnswers.clear()
                        mPositions.clear()
                        Constants.answersList.clear()
                        activity?.finish()
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->  })
            builder.create()

        } ?: throw  IllegalStateException("Activity cannot be null")
    }
}