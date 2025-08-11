package com.example.lab13_phonegridview

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val arr = arrayOf(
        "Ipad", "Iphone", "New Ipad",
        "SamSung", "Nokia", "Sony Ericson",
        "LG", "Q-Mobile", "HTC", "Blackberry",
        "G Phone", "FPT - Phone", "HK Phone"
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
        val selection: TextView = findViewById(R.id.selection)
        val gv: GridView = findViewById(R.id.gridView1)

        // Adapter cho GridView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arr
        )
        gv.adapter = adapter

        // Bắt sự kiện chọn item
        gv.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                selection.text = arr[position]
            }

    }
}