package com.tenutz.cracknotifier.util

import androidx.fragment.app.Fragment
import com.tenutz.cracknotifier.application.MainActivity

fun Fragment.mainActivity() = (requireActivity() as MainActivity)