package alesk.com.masterhelper.application.utils

import alesk.com.masterhelper.R
import android.app.AlertDialog
import android.content.Context
import android.widget.EditText


    fun showEditDialog(context: Context, title: String, body: String, onSave: (String) -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        val editText = EditText(context)
        editText.setText(body)
        builder.setView(editText)
        builder.setPositiveButton(context.getString(R.string.save), {
            dialog, i -> onSave(editText.text.toString())
        })
        builder.setNegativeButton(context.getString(R.string.cancel), {
            dialog, i -> dialog.cancel()
        })
        builder.show()
    }

    fun showAskingDialog(context: Context, title: String, positiveButton: String, onOk: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder
                .setTitle(title)
                .setNegativeButton(context.getString(R.string.no), { dialog, i -> dialog.cancel() })
                .setPositiveButton(positiveButton, { dialog, i -> onOk() })
                .show()
    }
