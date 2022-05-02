package com.tenutz.cracknotifier.ui.crack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import com.tenutz.cracknotifier.util.dummy.Dummies
import com.tenutz.cracknotifier.util.dummy.DummyCrackDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CrackViewModel @Inject constructor(

): BaseViewModel() {

    private val _tempCrack = MutableLiveData<DummyCrackDetail>()
    val tempCrack: LiveData<DummyCrackDetail> = _tempCrack

    fun tempCrack(id: Int) {
        Dummies.crackDetails.find { it.id == id }?.let {
            Single.just(it)
                .delay(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _tempCrack.postValue(it)
                }) { t ->
                    Logger.e("${t}")
                }
        }
    }
}