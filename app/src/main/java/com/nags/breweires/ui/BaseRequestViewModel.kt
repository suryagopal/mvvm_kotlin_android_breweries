package com.nags.breweires.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Base request view model class, which provides some
 * handy methods to trigger API calls.
 * Also provides a loading state.
 */
open class BaseRequestViewModel : ViewModel() {
    private val mCompositeDisposable = CompositeDisposable()
    private var mErrorException: MutableLiveData<Exception> = MutableLiveData()
    private val mLoaderState: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
        .apply { setValue(false) }

    /**
     * As soon as the view model is cleared
     * should dispose the CompositeDisposable as well.
     */
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }

    fun getLoader() = mLoaderState

    /**
     * Single<T> request which apply schedulers
     * and also maintains a state of loader.
     */
    fun <T> singleRequest(req: Single<T>): Single<T> {
        return req
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onDisplayLoading() }
            .doAfterTerminate { onHideLoading() }
    }

    /**
     * Completable request which apply schedulers
     * and also maintain a state of loader.
     */
    fun completableRequest(req: Completable): Completable {
        return req
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onDisplayLoading() }
            .doAfterTerminate { onHideLoading() }
    }

    /**
     * Add disposable to composite disposable.
     */
    fun addDisposable(disposable: Disposable) = mCompositeDisposable.add(disposable)

    fun onError() = mErrorException

    fun handleError(error: Throwable) {
        mErrorException.postValue(error as Exception)
    }

    private fun onDisplayLoading() {
        mLoaderState.postValue(true)
    }

    private fun onHideLoading() {
        mLoaderState.postValue(false)
    }
}