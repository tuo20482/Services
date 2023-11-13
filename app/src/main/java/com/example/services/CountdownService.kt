package com.example.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class CountdownService : Service() {

    companion object {
        const val COUNTDOWN_TIME = "countdown_time"
    }

    private val serviceScope = CoroutineScope(Dispatchers.Main)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time = intent.getIntExtra(COUNTDOWN_TIME, 0)
        startCountdown(time)
        return START_NOT_STICKY
    }

    private fun startCountdown(time: Int) {
        serviceScope.launch {
            for (i in time downTo 1) {
                Log.d("CountdownService", "Countdown: $i")
                delay(1000)
            }
            Log.d("CountdownService", "Countdown finished!")
            stopSelf()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}
