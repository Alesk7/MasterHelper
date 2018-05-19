package alesk.com.masterhelper.application.utils

import alesk.com.masterhelper.R
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.EditText

const val UNIT_DROPDOWN_WIDTH = 233

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

fun showCustomViewDialog(context: Context, view: View, title: String,
                         onOk: (DialogInterface, Int) -> Unit): AlertDialog.Builder {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setView(view)
    builder.setPositiveButton(context.getString(R.string.ok), onOk)
    builder.setNegativeButton(context.getString(R.string.cancel), {
        dialog, i -> dialog.cancel()
    })
    return builder
}
