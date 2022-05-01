package com.tenutz.cracknotifier.util

import androidx.fragment.app.Fragment
import com.tenutz.cracknotifier.application.MainActivity
import com.tenutz.cracknotifier.ui.signup.ContainerSignupFragment

fun Fragment.mainActivity() = (requireActivity() as MainActivity)
fun Fragment.signupFragment() = (parentFragment?.parentFragment as ContainerSignupFragment)