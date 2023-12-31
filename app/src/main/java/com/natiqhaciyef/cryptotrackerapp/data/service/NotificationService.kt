package com.natiqhaciyef.cryptotrackerapp.data.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class NotificationService: Service() {
    private val TAG = "Custom service"

    init {
        Log.d(TAG,"Service is running...")
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Service killed...")
    }
}