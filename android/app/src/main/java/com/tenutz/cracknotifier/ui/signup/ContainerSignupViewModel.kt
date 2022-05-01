package com.tenutz.cracknotifier.ui.signup

import androidx.lifecycle.MutableLiveData
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContainerSignupViewModel @Inject constructor(

): BaseViewModel() {

    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val passwordCheck = MutableLiveData<String>("")
    val name = MutableLiveData<String>("")
}