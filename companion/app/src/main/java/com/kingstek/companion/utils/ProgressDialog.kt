package com.kingstek.companion.utils

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.kingstek.companion.R

class ProgressDialog (val context: Context) {

    private lateinit var mProgressDialog: Dialog

    /**
     * This function is used to show the progress dialog with the title and message to user.
     */
    fun showProgressDialog(pBarText: String) {
        mProgressDialog = Dialog(context)

        /*Set the screen content from a layout resource.
        The resource will be inflated, adding all top-level views to the screen.*/
        mProgressDialog.setContentView(R.layout.dialog_progress)

        val text: TextView = mProgressDialog.findViewById(R.id.tv_progress_text)
        text.text = pBarText
//        mProgressDialog. tv_progress_text.text = text

        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)

        //Start the dialog and display it on screen.
        mProgressDialog.show()
    }

    /**
     * This function is used to dismiss the progress dialog if it is visible to user.
     */
    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }
}