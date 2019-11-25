package ke.co.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import ke.co.workmanager.workers.FirstWorker
import ke.co.workmanager.workers.SecondWorker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Work.setOnClickListener {
            val constrain = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val  workManger = WorkManager.getInstance()

            val data = Data.Builder()
            data.putString("KEY_WORK","DOING Myyy FIRST WORK")

            var firstWorkerRequest = OneTimeWorkRequest.Builder(FirstWorker::class.java).setInputData(data.build()).build()
            var secondWorkerRequest = OneTimeWorkRequest.Builder(SecondWorker::class.java).addTag("SECOND_WORK").setConstraints(constrain).build()
            //workManger.enqueue(firstWorkerRequest)
            workManger.beginWith(firstWorkerRequest).then(secondWorkerRequest).enqueue()

            workManger.getWorkInfoByIdLiveData(firstWorkerRequest.id).observe(this, Observer {
                var fwstatus = it.state
                Toast.makeText(this,fwstatus.name,Toast.LENGTH_LONG).show()
            })


            workManger.getWorkInfosByTagLiveData("SECOND_WORK").observe(this, Observer {
                var swstatus = it.getOrNull(0)?.state
                Toast.makeText(this,swstatus?.name,Toast.LENGTH_LONG).show()
            })

        }
    }
}
