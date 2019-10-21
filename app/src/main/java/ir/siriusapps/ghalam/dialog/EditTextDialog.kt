package ir.siriusapps.ghalam.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import ir.siriusapps.ghalam.R
import kotlinx.android.synthetic.main.edittext_dialog.*

class EditTextDialog(context: Context) : Dialog(context) {

    var title: String? = null
        set(value) {
            titleTextView?.text = value
            field = value
        }

    var hint: String? = null
        set(value) {
            editText?.hint = value
            field = value
        }

    var yesButtonText: String? = null
        set(value) {
            yesButton?.text = value
            field = value
        }

    var noButtonText: String? = null
        set(value) {
            noButton?.text = value
            field = value
        }

    var yesButtonClickListener: View.OnClickListener? = null
        set(value) {
            yesButton?.setOnClickListener(value)
            field = value
        }

    var noButtonClickListener: View.OnClickListener? = null
        set(value) {
            noButton?.setOnClickListener(value)
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.edittext_dialog)

        title?.let { titleTextView.text = it }
        hint?.let { editText.hint = it }
        yesButtonText?.let { yesButton.text = it }
        noButtonText?.let { noButton.text = it }
        yesButtonClickListener?.let { yesButton.setOnClickListener(it) }

        if (noButtonClickListener != null)
            noButton.setOnClickListener(noButtonClickListener)
        else
            noButton.setOnClickListener { dismiss() }
    }

}