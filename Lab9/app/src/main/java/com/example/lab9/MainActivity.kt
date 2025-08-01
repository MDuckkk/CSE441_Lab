package com.example.lab9

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnPlay: ImageButton
    private lateinit var btnStop: ImageButton
    private var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnPlay = findViewById(R.id.btnPlay)
        btnStop = findViewById(R.id.btnStop)

        btnPlay.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
            if (isPlaying) {
                btnPlay.setImageResource(R.drawable.pause)
                isPlaying = false
            } else {
                btnPlay.setImageResource(R.drawable.play)
                isPlaying = true
            }
        }

        btnStop.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
            btnPlay.setImageResource(R.drawable.play)
            isPlaying = true
        }
    }
}