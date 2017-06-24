package io.eraiki.rxpush.firebase


interface FirebaseInstanceIdServiceObserver {
    fun onTokenRefresh(token:String)
}