package com.example.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Handler
import android.os.Message
import android.widget.Toast

class MyJobScheduler:JobService() {

    private val TAG = "MyJobScheduler"

    override fun onStopJob(params: JobParameters?): Boolean {
        mJobHandler.removeMessages(MainActivity.jobId)
        return false
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        mJobHandler.sendMessage(Message.obtain(mJobHandler, MainActivity.jobId, params))
        return true
    }

    private val mJobHandler: Handler = Handler(object : Handler.Callback {
        override fun handleMessage(msg: Message): Boolean {
            Toast.makeText(applicationContext, "Job running", Toast.LENGTH_SHORT).show()
            for (i in 1..10) {
                Toast.makeText(applicationContext, "Running job $i", Toast.LENGTH_SHORT).show()
            }
            jobFinished(msg.obj as JobParameters, false)
            Toast.makeText(applicationContext, "Job finished", Toast.LENGTH_SHORT).show()
            return true
        }
    })
}