package eraiki.io.reactivefirebasepushmessaging

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.grupoasv.setsconductor.presentation.R
import com.grupoasv.setsconductor.presentation.ui.activities.MainActivity


class PushNotification {
    companion object {

        fun buildAndShowNotification(context: Context, message: RemoteMessage) {

            val title = message.data["title"]
            val body = message.data["body"]

            val notificationBuilder = NotificationCompat.Builder(context)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setContentIntent(getPendingIntentForNotification(context))

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notificationBuilder.build())
        }

        private fun getPendingIntentForNotification(context: Context): PendingIntent {

            val resultIntent = Intent(context, MainActivity::class.java)
            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(MainActivity::class.java)
            stackBuilder.addNextIntent(resultIntent)

            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT)
        }
    }

}