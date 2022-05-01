package com.tenutz.cracknotifier.util

import android.content.Context
import android.util.TypedValue

fun Context.getPxFromDp(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

fun Context.getDpFromPx(px: Int) = resources.displayMetrics.density.let { density ->
    when(density) {
        1.0f -> px / (density * 4.0f)
        1.5f -> px / (8 / 3.0f)
        2.0f -> px / (density * 2.0f)
        else -> px / density
    }
}