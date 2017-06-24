package io.eraiki.reactivefirebasepushmessaging

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.messaging.RemoteMessage
import io.eraiki.rxpush.usecases.OnMessageReceivedUseCase
import io.eraiki.rxpush.usecases.RefreshTokenUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

class MainActivity : AppCompatActivity() {
    val threadExecutor = JobExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeToFCMRegistration()
        subscribeToNotifications()
    }

    fun subscribeToNotifications(){
        OnMessageReceivedUseCase(threadExecutor, AndroidSchedulers.mainThread())
                .execute(NotificationObserver())
    }

    fun subscribeToFCMRegistration(){
        RefreshTokenUseCase(threadExecutor, AndroidSchedulers.mainThread())
                .execute(FCMTokenReceiverObserver())
    }

    fun enrollDevice(deviceToken:String){
        //Send deviceToken to own message server
    }

    inner class NotificationObserver: DisposableObserver<RemoteMessage>() {
        override fun onNext(t: RemoteMessage) {
            PushNotification.buildAndShowNotification(this@MainActivity, t)
        }

        override fun onComplete() {
        }

        override fun onError(exception: Throwable) {

        }
    }

    inner class FCMTokenReceiverObserver: DisposableObserver<String>() {
        override fun onNext(t: String) {
            enrollDevice(t)
        }

        override fun onComplete() {
        }

        override fun onError(exception: Throwable) {

        }
    }
}
