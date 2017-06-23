package eraiki.io.rxpush.firebase

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FirebaseInstanceIdServiceImp : FirebaseInstanceIdService() {

    var listener = FirebaseInstanceIdServiceObserverImp.Companion.instance

    override fun onTokenRefresh() {
        val token = FirebaseInstanceId.getInstance().token
        if (token != null) listener.onTokenRefresh(token)
    }

}

