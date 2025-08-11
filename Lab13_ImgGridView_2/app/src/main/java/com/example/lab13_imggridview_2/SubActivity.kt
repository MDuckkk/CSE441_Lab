package com.example.lab13_imggridview_2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sub)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sub_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtName = findViewById<TextView>(R.id.textView2)
        val img = findViewById<ImageView>(R.id.imageView2)
        val name = intent.getStringExtra("name")
        val url = intent.getStringExtra("url")
        txtName.text = "Bạn đã chọn " + name
        Picasso.get().load(url).into(img)
    }
}