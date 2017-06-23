package eraiki.io.rxpush.firebase


interface FirebaseInstanceIdServiceObserver {
    fun onTokenRefresh(token:String)
}