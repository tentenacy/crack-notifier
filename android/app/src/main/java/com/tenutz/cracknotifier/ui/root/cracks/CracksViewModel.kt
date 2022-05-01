package com.tenutz.cracknotifier.ui.root.cracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CracksViewModel @Inject constructor(

): BaseViewModel() {

    companion object {
        const val EVENT_SEARCH = 1000
    }

    private val _termCheckedRadioId = MutableLiveData<Int?>(R.id.radiobtn_bsfiltercracks_whole)
    val termCheckedRadioId: LiveData<Int?> = _termCheckedRadioId

    private val _dateFromText = MutableLiveData<String?>(null)
    val dateFromText: LiveData<String?> = _dateFromText

    private val _dateToText = MutableLiveData<String?>(null)
    val dateToText: LiveData<String?> = _dateToText

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

}