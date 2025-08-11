package com.example.lab13_imggridview

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private val photos = intArrayOf(
        R.drawable.p1, R.drawable.p2, R.drawable.p3,
        R.drawable.p4, R.drawable.p5, R.drawable.p6,
        R.drawable.p7, R.drawable.p1, R.drawable.p2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val grid: GridView = findViewById(R.id.gridView)
        val adapter = PhotoGridAdapter(this, photos.toList())
        grid.adapter = adapter

        grid.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val intent = Intent(this, PhotoActivity::class.java)
                intent.putExtra(PhotoActivity.EXTRA_RES_ID, photos[position])
                startActivity(intent)
            }
    }
}