package com.tenutz.cracknotifier.util

import java.text.DecimalFormat

fun Int.toZeroZeroFormat(): String = DecimalFormat("00").format(this)