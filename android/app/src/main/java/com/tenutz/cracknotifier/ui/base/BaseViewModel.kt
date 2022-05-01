package com.tenutz.cracknotifier.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tenutz.cracknotifier.util.Event
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    private val _viewEvent = MutableLiveData<Event<Pair<Int, Any>>>()
    val viewEvent: LiveData<Event<Pair<Int, Any>>>
        get() = _viewEvent

    private val _loadingEvent = MutableLiveData<Event<Boolean>>()
    val loadingEvent: LiveData<Event<Boolean>>
        get() = _loadingEvent

    fun viewEvent(content: Pair<Int, Any>) {
        _viewEvent.postValue(Event(content))
    }

    fun loadingEvent(content: Boolean) {
        _loadingEvent.postValue(Event(content))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}