package io.eraiki.rxpush.firebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseMessagingServiceImp : FirebaseMessagingService() {
    private var listener: FirebaseMessagingServiceObserver? =
            FirebaseMessagingServiceObserverImp.Companion.instance

    override fun onMessageReceived(message: RemoteMessage) {
        listener?.onMessageReceivedObservable(message)
    }
}
