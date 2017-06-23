package eraiki.io.rxpush.firebase

import eraiki.io.rxpush.RxBus
import io.reactivex.Observable

class FirebaseInstanceIdServiceObserverImp: FirebaseInstanceIdServiceObserver {
    private var bus = RxBus<String>()

    private object Holder {
        val INSTANCE = FirebaseInstanceIdServiceObserverImp()
    }

    companion object {
        val instance: FirebaseInstanceIdServiceObserverImp by lazy { Holder.INSTANCE }
    }

    fun getMessageObservable(): Observable<String> {
        return bus.toObservable()
    }

    private fun sendToken(token: String){
        bus.send(token)
    }

    override fun onTokenRefresh(token: String) {
        sendToken(token)
    }
}