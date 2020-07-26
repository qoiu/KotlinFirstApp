package com.geekbrains.kotlinfirstapp.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.geekbrains.kotlinfirstapp.R

class LogoutDialog(
        private val positiveListener: DialogInterface.OnClickListener,
        private val title: String? = null,
        private val message: String? = null,
        private val btnPos: String? = null,
        private val btnNeg: String? = null
) : DialogFragment() {
    companion object {
        val TAG = LogoutDialog::class.java.name + "TAG"
        fun createInstance(positiveListener: DialogInterface.OnClickListener,
                           title: String? = null,
                           message: String? = null,
                           btnPos: String? = null,
                           btnNeg: String? = null
        ): LogoutDialog = LogoutDialog(positiveListener,title,message, btnPos, btnNeg)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = AlertDialog.Builder(context!!)
            .setTitle(title ?: getString(R.string.logout_title))
            .setMessage(message?:getString(R.string.logout_message))
            .setPositiveButton(btnPos?:getString(R.string.logout_positive), positiveListener)
            .setNegativeButton(btnNeg?:getString(R.string.logout_negative)) { dialog, which ->
                dismiss()
            }
            .create()

    interface LogoutListener {
        fun onLogout()
    }
}