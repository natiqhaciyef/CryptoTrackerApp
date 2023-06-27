package com.natiqhaciyef.cryptotrackerapp.view.screens.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natiqhaciyef.cryptotrackerapp.data.worker.NotificationSender
import com.natiqhaciyef.cryptotrackerapp.databinding.ActivityMainBinding
import com.natiqhaciyef.cryptotrackerapp.domain.work.NotificationWork
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NotificationWork.activateWorkManager(this, "Max price alert","Max price rose from your limit. Please check app and change limitation.")
    }
}