package com.example.lab12_phonelist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    var txt1: TextView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txt1: TextView = findViewById(R.id.txtselection)

        val arr1 = arrayOf(
            "Iphone 7",
            "SamSung Galaxy S7",
            "Nokia Lumia 730",
            "Sony Xperia XZ",
            "HTC One E9"
        )

        val adapter1 = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arr1
        )

        val lv1: ListView = findViewById(R.id.lv1)
        lv1.adapter = adapter1

        lv1.setOnItemClickListener { _, _, i, _ ->
            txt1.text = "Vị trí $i : ${arr1[i]}"
        }
    }
}