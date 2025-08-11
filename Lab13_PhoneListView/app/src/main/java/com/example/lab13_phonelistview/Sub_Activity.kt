package com.example.lab13_phonelistview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val phoneName = intent.getStringExtra("name")
        val txtSelected = findViewById<TextView>(R.id.txtSelected)
        txtSelected.text = "Bạn đã chọn\n$phoneName"
    }
}