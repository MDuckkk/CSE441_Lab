package com.example.lab14_sumtabselector

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TabHost
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var edta: EditText
    private lateinit var edtb: EditText
    private lateinit var btncong: Button
    private lateinit var lv1: ListView

    private lateinit var list: MutableList<String>
    private lateinit var myarray: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addControl()
        addEvent()
    }
    private fun addEvent() {
        btncong.setOnClickListener { xulyCong() }
    }

    private fun xulyCong() {
        val a = edta.text.toString().toIntOrNull() ?: return
        val b = edtb.text.toString().toIntOrNull() ?: return

        val c = "$a + $b = ${a + b}"
        list.add(c)
        myarray.notifyDataSetChanged()

        edta.text.clear()
        edtb.text.clear()
    }

    private fun addControl() {
        val tab: TabHost = findViewById(R.id.tabhost)
        tab.setup()

        val tab1 = tab.newTabSpec("t1").apply {
            setContent(R.id.tab1)
            setIndicator("", ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_launcher_foreground))
        }
        tab.addTab(tab1)

        val tab2 = tab.newTabSpec("t2").apply {
            setContent(R.id.tab2)
            setIndicator("", ContextCompat.getDrawable(this@MainActivity, R.drawable.ic_launcher_background))
        }
        tab.addTab(tab2)

        edta = findViewById(R.id.edta)
        edtb = findViewById(R.id.edtb)
        btncong = findViewById(R.id.btncong)
        lv1 = findViewById(R.id.lv1)

        list = mutableListOf()
        myarray = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        lv1.adapter = myarray
    }
}