package io.eraiki.rxpush

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class RxBus<T> {

    private val bus = PublishSubject.create<T>()

    fun send(o: T) {
        bus.onNext(o)
    }

    fun toObservable(): Observable<T> {
        return bus
    }

    fun hasObservers(): Boolean {
        return bus.hasObservers()
    }
}