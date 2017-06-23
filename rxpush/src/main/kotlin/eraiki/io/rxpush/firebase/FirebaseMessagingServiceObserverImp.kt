package eraiki.io.rxpush.firebase

import com.google.firebase.messaging.RemoteMessage
import eraiki.io.rxpush.RxBus
import io.reactivex.Observable


class FirebaseMessagingServiceObserverImp: FirebaseMessagingServiceObserver {

    private var bus = RxBus<RemoteMessage>()

    private object Holder {
        val INSTANCE = FirebaseMessagingServiceObserverImp()
    }

    companion object {
        val instance: FirebaseMessagingServiceObserverImp by lazy { Holder.INSTANCE }
    }

    fun getMessageObservable(): Observable<RemoteMessage> {
        return bus.toObservable()
    }

    private fun sendMessage(remoteMessage: RemoteMessage){
        bus.send(remoteMessage)
    }

    override fun onMessageReceivedObservable(message: RemoteMessage) {
        sendMessage(message)
    }
}