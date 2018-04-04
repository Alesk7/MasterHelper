package alesk.com.masterhelper.application.utils

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable

fun getOptimizedBitmap(sourceImage: BitmapDrawable, windowWidth: Double): Bitmap {
    val bitmap = sourceImage.bitmap
    val imageHeight = bitmap.height.toDouble()
    val imageWidth = bitmap.width.toDouble()
    val ratio = windowWidth / imageWidth
    val newImageHeight = (imageHeight * ratio).toInt()
    return getResizedBitmap(bitmap, newImageHeight, windowWidth.toInt())
}

fun getResizedBitmap(bm: Bitmap, newHeight: Int, newWidth: Int): Bitmap {
    val scaleWidth = newWidth.toFloat() / bm.width
    val scaleHeight = newHeight.toFloat() / bm.height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    return Bitmap.createBitmap(bm, 0, 0, bm.width, bm.height, matrix, false)
}