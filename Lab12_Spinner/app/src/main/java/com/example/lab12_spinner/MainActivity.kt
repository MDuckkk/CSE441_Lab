package com.example.lab12_spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    var arr1: Array<String> = arrayOf(
        "Hàng Điện tử", "Hàng Hóa Chất",
        "Hàng Gia dụng", "Hàng xây dựng"
    )
    var txt1: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txt1: TextView = findViewById(R.id.txt1)
        val spin1: Spinner = findViewById(R.id.spinner1)

        val adapter1 = ArrayAdapter(
            this,                                   // dùng requireContext() nếu ở Fragment
            android.R.layout.simple_spinner_item,
            arr1                                    // mảng String đã có sẵn
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)

        spin1.adapter = adapter1

        spin1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                txt1.text = arr1[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // no-op
            }
        }
    }
}