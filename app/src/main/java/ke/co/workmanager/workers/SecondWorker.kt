package ke.co.workmanager.workers

import android.content.Context
import android.nfc.Tag
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import ke.co.workmanager.makeNotification
import ke.co.workmanager.sleep
import java.lang.Thread.sleep
import java.lang.reflect.Parameter

class SecondWorker(ctx : Context, params : WorkerParameters) : Worker(ctx,params) {
   private val TAG by  lazy{
       SecondWorker::class.java.simpleName
   }
    override fun doWork(): Result {

              makeNotification("Doing second work", applicationContext)
        return  try {
            //actual work
            sleep()
            makeNotification("Completed second Work", applicationContext)
            Result.success()
        }catch (throwable : Throwable){
            Log.e(TAG,"Error doing second work",throwable)
            Result.failure()
        }
    }
}