package eraiki.io.rxpush.firebase

import com.google.firebase.messaging.RemoteMessage


interface FirebaseMessagingServiceObserver {
    fun onMessageReceivedObservable(message: RemoteMessage)
}