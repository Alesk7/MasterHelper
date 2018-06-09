package alesk.com.masterhelper.application.utils

import alesk.com.masterhelper.R
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.EditText

fun showTextFieldDialog(context: Context, title: String, body: String, onSave: (String) -> Unit) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    val editText = EditText(context)
    editText.setText(body)
    builder.setView(editText)
    builder.setPositiveButton(context.getString(R.string.ok), { _, _ -> onSave(editText.text.toString()) })
    builder.setNegativeButton(context.getString(R.string.cancel), { dialog, _ -> dialog.cancel() })
    builder.show()
}

fun showAskingDialog(context: Context, title: String, positiveButton: String, onOk: () -> Unit) {
    val builder = AlertDialog.Builder(context)
    builder
            .setTitle(title)
            .setNegativeButton(context.getString(R.string.no), { dialog, _ -> dialog.cancel() })
            .setPositiveButton(positiveButton, { _, _ -> onOk() })
            .show()
}

fun showMessageDialog(context: Context, title: String, message: String, negativeButton: String,
                      positiveButton: String, onOk: () -> Unit) {
    val builder = AlertDialog.Builder(context)
    builder
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(negativeButton, { dialog, _ -> dialog.cancel() })
            .setPositiveButton(positiveButton, { _, _ -> onOk() })
            .show()
}

fun buildCustomViewDialog(context: Context, view: View, title: String,
                          onOk: (DialogInterface, Int) -> Unit): AlertDialog.Builder {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setView(view)
    builder.setPositiveButton(context.getString(R.string.ok), onOk)
    builder.setNegativeButton(context.getString(R.string.cancel), { dialog, _ -> dialog.cancel() })
    return builder
}

fun buildCustomViewDialogWithoutButtons(context: Context, view: View, title: String): AlertDialog.Builder {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setView(view)
    return builder
}
