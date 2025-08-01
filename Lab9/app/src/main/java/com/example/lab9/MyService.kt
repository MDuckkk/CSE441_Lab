package com.example.lab9

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MyService : Service() {
    private var myMedia: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        myMedia = MediaPlayer.create(this, R.raw.trentinhbanduoitinhyeu)
        myMedia?.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        myMedia?.let {
            if (it.isPlaying) {
                it.pause()
            } else {
                it.start()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() { 
        super.onDestroy()
        myMedia?.stop()
        myMedia?.release()
    }
}