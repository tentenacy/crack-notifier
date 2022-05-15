package com.tenutz.cracknotifier.ui.root.cracks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.R
import com.tenutz.cracknotifier.data.api.dto.common.CommonCondition
import com.tenutz.cracknotifier.data.paging.entity.Cracks
import com.tenutz.cracknotifier.data.paging.repository.CrackPagingRepository
import com.tenutz.cracknotifier.data.repository.crack.CrackRepository
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import com.tenutz.cracknotifier.util.dateFrom
import com.tenutz.cracknotifier.util.start
import com.tenutz.cracknotifier.util.toDateTimeFormat
import com.tenutz.cracknotifier.util.tomorrow
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CracksViewModel @Inject constructor(
    private val crackPagingRepository: CrackPagingRepository,
    private val crackRepository: CrackRepository,
): BaseViewModel() {

    companion object {
        const val EVENT_NAVIGATE_TO_CRACK = 1001
    }

    private val _durationCheckedRadioId = MutableLiveData<Int?>(R.id.radiobtn_bsfiltercracks_whole)
    val durationCheckedRadioId: LiveData<Int?> = _durationCheckedRadioId

    private val _sortCheckedRadioId = MutableLiveData<Int?>(R.id.radiobtn_bsfiltercracks_sort_latest)
    val sortCheckedRadioId: LiveData<Int?> = _sortCheckedRadioId

    private val _dateFromText = MutableLiveData<String?>(null)
    val dateFromText: LiveData<String?> = _dateFromText

    private val _dateToText = MutableLiveData<String?>(null)
    val dateToText: LiveData<String?> = _dateToText

    private val _sortText = MutableLiveData<String?>(null)
    val sortText: LiveData<String?> = _sortText

    private val _cracks = MutableLiveData<PagingData<Cracks.Crack>>()
    val cracks: LiveData<PagingData<Cracks.Crack>> = _cracks

    fun setDurationCheckedRadioId(id: Int?) {
        _durationCheckedRadioId.value = id
    }

    fun setSortCheckedRadioId(id: Int?) {
        _sortCheckedRadioId.value = id
    }

    fun setDateFromText(dateFromText: String?) {
        _dateFromText.value = dateFromText
    }

    fun setDateToText(dateToText: String?) {
        _dateToText.value = dateToText
    }

    fun setSortText(sortText: String?) {
        _sortText.value = sortText
    }

    fun cracks() {
        crackPagingRepository.cracks(
            CommonCondition(
                dateFrom = dateFromText.value?.let { dateFrom(it).start().toDateTimeFormat() },
                dateTo = dateToText.value?.let { dateFrom(it).tomorrow().start().toDateTimeFormat() },
                sort = sortText.value,
            )
        )
            .observeOn(AndroidSchedulers.mainThread())
            .cachedIn(viewModelScope)
            .subscribe({
                _cracks.postValue(it)
            }) { t ->
                Logger.e("${t}")
            }
    }

    fun crackDetails(crackId: String) {
        crackRepository.details(crackId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewEvent(Pair(EVENT_NAVIGATE_TO_CRACK, it))
            }) { t ->
                Logger.e("${t}")

            }
    }
}