package com.tenutz.cracknotifier.ui.root.cracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import com.tenutz.cracknotifier.util.dummy.Dummies
import com.tenutz.cracknotifier.util.dummy.DummyCrackDetail
import com.tenutz.cracknotifier.util.dummy.DummyCracks
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CracksViewModel @Inject constructor(

): BaseViewModel() {

    companion object {
        const val EVENT_SEARCH = 1000
        const val EVENT_NAVIGATE_TO_CRACK = 1001
    }

    private val _termCheckedRadioId = MutableLiveData<Int?>(R.id.radiobtn_bsfiltercracks_whole)
    val termCheckedRadioId: LiveData<Int?> = _termCheckedRadioId

    private val _dateFromText = MutableLiveData<String?>(null)
    val dateFromText: LiveData<String?> = _dateFromText

    private val _dateToText = MutableLiveData<String?>(null)
    val dateToText: LiveData<String?> = _dateToText

    private val _tempCracks = MutableLiveData<List<DummyCracks>>()
    val tempCracks: LiveData<List<DummyCracks>> = _tempCracks

    fun setTermCheckedRadioId(id: Int?) {
        _termCheckedRadioId.value = id
    }

    fun applyFilter() {
        viewEvent(Pair(EVENT_SEARCH, Unit))
    }

    fun setDateFromText(dateFromText: String?) {
        _dateFromText.value = dateFromText
    }

    fun setDateToText(dateToText: String?) {
        _dateToText.value = dateToText
    }

    fun tempCracks() {
        Single.just(Dummies.cracks)
            .delay(200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _tempCracks.postValue(it)
            }) { t ->
                Logger.e("${t}")
            }
    }

    fun tempCrack(id: Int) {
        Dummies.crackDetails.find { it.id == id }?.let {
            Single.just(it)
                .delay(200, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewEvent(Pair(EVENT_NAVIGATE_TO_CRACK, it))
                }) { t ->
                    Logger.e("${t}")
                }
        }
    }

}