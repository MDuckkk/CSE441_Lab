package com.example.lab13_imggridview

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        val image = findViewById<ImageView>(R.id.imgFull)
        val resId = intent.getIntExtra(EXTRA_RES_ID, 0)
        if (resId != 0) image.setImageResource(resId)
    }

    companion object {
        const val EXTRA_RES_ID = "extra_res_id"
    }
}