package io.eraiki.rxpush.usecases

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.eraiki.rxpush.firebase.FirebaseInstanceIdServiceObserverImp
import java.util.concurrent.Executor


class RefreshTokenUseCase constructor(private val threadExecutor: Executor, private val scheduler: Scheduler) {
    private val disposables: CompositeDisposable = CompositeDisposable()

    private fun buildUseCaseObservable(): Observable<String> {
        return FirebaseInstanceIdServiceObserverImp.instance.getMessageObservable()
    }

    fun execute(observer: DisposableObserver<String>) {
        val observable = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
