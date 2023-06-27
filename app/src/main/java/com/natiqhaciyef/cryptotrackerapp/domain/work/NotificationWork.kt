package com.natiqhaciyef.cryptotrackerapp.domain.work

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.Operation
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.natiqhaciyef.cryptotrackerapp.data.worker.NotificationWorker
import java.util.concurrent.TimeUnit

object NotificationWork{
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    private val request = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
        .setConstraints(constraints)
        .build()

    fun activateWorkManager(context: Context, title: String, description: String): Operation {
        NotificationWorker.title = title
        NotificationWorker.description = description

        return WorkManager.getInstance(context).enqueue(request)
    }

}