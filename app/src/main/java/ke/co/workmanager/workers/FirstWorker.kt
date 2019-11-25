package ke.co.workmanager.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import ke.co.workmanager.makeNotification
import ke.co.workmanager.sleep

class FirstWorker(ctx : Context, params : WorkerParameters) : Worker(ctx,params) {
   private val TAG by  lazy{
       FirstWorker::class.java.simpleName
   }
    override fun doWork(): Result {
        val data = inputData.getString("KEY_WORK")
              makeNotification(data!!, applicationContext)
        return  try {
            //actual work
            sleep()
            makeNotification("Completed first Work", applicationContext)
            Result.success()
        }catch (throwable : Throwable){
            Log.e(TAG,"Error doig first work",throwable)
            Result.failure()
        }
    }
}