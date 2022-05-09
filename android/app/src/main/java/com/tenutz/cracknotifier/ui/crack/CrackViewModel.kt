package com.tenutz.cracknotifier.ui.crack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.orhanobut.logger.Logger
import com.tenutz.cracknotifier.data.api.dto.crack.CrackDetailsResponse
import com.tenutz.cracknotifier.repository.crack.CrackRepository
import com.tenutz.cracknotifier.ui.base.BaseViewModel
import com.tenutz.cracknotifier.ui.root.cracks.CracksViewModel
import com.tenutz.cracknotifier.util.dummy.DummyCrackDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CrackViewModel @Inject constructor(
    private val crackRepository: CrackRepository,
): BaseViewModel() {

    private val _crackDetails = MutableLiveData<CrackDetailsResponse>()
    val crackDetails: LiveData<CrackDetailsResponse> = _crackDetails

    fun crackDetails(crackId: String) {
        crackRepository.details(crackId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _crackDetails.postValue(it)
            }) { t ->
                Logger.e("${t}")
            }
    }
}