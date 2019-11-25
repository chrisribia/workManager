package ke.co.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

fun makeNotification(text : String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = " workManager Notification channel name"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("NOTIFICATION CHANNEL", name, importance)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.createNotificationChannel(channel)

    }
    val builder = NotificationCompat.Builder(context, "NOTIFICATION CHANNEL")
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Notfication title")
        .setContentText(text)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    NotificationManagerCompat.from(context).notify(1, builder.build())
}
fun sleep(){
    try {
        Thread.sleep(10000,0)
    }catch (e: InterruptedException){
        Log.e("Utils",e.message)
    }
}

