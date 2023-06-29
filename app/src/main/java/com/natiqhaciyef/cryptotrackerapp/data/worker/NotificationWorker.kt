package com.natiqhaciyef.cryptotrackerapp.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import javax.inject.Inject

class NotificationWorker @Inject constructor(
    val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    companion object {
        var title = ""
        var description = ""
    }

    override suspend fun doWork(): Result {
//        if (title.isNotEmpty() && description.isNotEmpty())
//            NotificationSender.sendNotification(context, title, description)
//        else
        NotificationSender.sendNotification(
            context,
            "Price alert $title",
            "Enter app and check currencies' price limitation"
        )
        return Result.success()
    }
}