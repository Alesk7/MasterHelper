package alesk.com.masterhelper.application.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.toast_custom.view.*

const val Y_OFFSET = 100
const val X_OFFSET = 0

fun showShortCustomToast(context: Context?, view: View, text: String) {
    val toast = buildToast(context)
    toast.view = view
    view.message.text = text
    toast.duration = Toast.LENGTH_SHORT
    toast.show()
}

fun buildToast(context: Context?): Toast {
    val toast = Toast(context)
    toast.setGravity(Gravity.BOTTOM, X_OFFSET, Y_OFFSET)
    return toast
}