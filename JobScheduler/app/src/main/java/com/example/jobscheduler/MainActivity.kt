package com.example.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    companion object{
        const val jobId = 1
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        startJobButton.setOnClickListener {
            startJob()
        }

        // You can cancel the job
        // If it's not started yet
        stopJobButton.setOnClickListener {
            stopJob()
        }

        // constraints to create notification only when the phone has
        // enough battery and Internet is connected
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        // which class should be executed is decided by this request object
        // MyWork is Worker class
        val request = OneTimeWorkRequestBuilder<MyWork>().setConstraints(constraints).build()
        workManagerBtn.setOnClickListener {
            // It enqueues and manages the work request.
            WorkManager.getInstance(this).enqueue(request)
        }

        // creating a toast to display the status of our task
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer {
                val status = it.state.name
                Toast.makeText(this, "$status", Toast.LENGTH_SHORT).show()
            })
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun stopJob() {
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(jobId)
        Toast.makeText(this, "Job has stopped", Toast.LENGTH_SHORT).show()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startJob() {
        Toast.makeText(this, "Job will start in 2 seconds...", Toast.LENGTH_SHORT).show()

        val jobService = ComponentName(this, MyJobScheduler::class.java)
        val jobInfo = JobInfo
            .Builder(jobId, jobService)
            .setMinimumLatency(2000)
            .build()

        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        if (jobScheduler.schedule(jobInfo) <= 0) {
            Toast.makeText(this, "There is problem while scheduling job", Toast.LENGTH_SHORT).show()
        }

    }
}