package com.natiqhaciyef.cryptotrackerapp.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.natiqhaciyef.cryptotrackerapp.common.Status
import com.natiqhaciyef.cryptotrackerapp.domain.usecases.get.GetAllCryptoCurrenciesUseCase
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
        NotificationSender.sendNotification(context, title, description)
        return Result.success()
    }
}