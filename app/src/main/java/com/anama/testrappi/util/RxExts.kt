package com.anama.testrappi.util

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import com.anama.testrappi.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.SocketTimeoutException

class LifeDisposable(owner: LifecycleOwner): LifecycleObserver {

    init {
        owner.lifecycle.addObserver(this)
    }

    val dis:CompositeDisposable = CompositeDisposable()

    infix fun add(disposable: Disposable){
        dis.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clearDisposable(){
        dis.clear()
    }
}

fun <T> Observable<T>.applySchedulers(): Observable<T> = compose {
    it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}


fun <T> Observable<T>.subscribeByShot(onNext: (T) -> Unit, onHttpError: (resString: Int) -> Unit,
                                      onError: ((error: Throwable) -> Unit)? = null): Disposable =

        doOnError {
            when(it){
                is SocketTimeoutException -> onHttpError(R.string.socket)
                is HttpException -> {
                    when(it.code()) {
                        404 -> onHttpError(R.string.http_404)
                        401 -> onHttpError(R.string.http_401)
                        else -> onHttpError(R.string.http_500)
                    }
                }
                else -> onError?.invoke(it)
            }
        }
                .subscribe(onNext, {})