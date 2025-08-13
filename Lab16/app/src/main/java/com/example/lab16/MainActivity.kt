package com.example.lab16

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var edta: EditText
    private lateinit var edtb: EditText
    private lateinit var edtkq: EditText
    private lateinit var btntong: Button
    private lateinit var btnclear: Button
    private lateinit var txtlichsu: TextView

    private var lichsu: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edta = findViewById(R.id.edta)
        edtb = findViewById(R.id.edtb)
        edtkq = findViewById(R.id.edtkq)
        btntong = findViewById(R.id.btntong)
        btnclear = findViewById(R.id.btnclear)
        txtlichsu = findViewById(R.id.txtlichsu)

        // Lấy lại dữ liệu trong SharedPreferences
        val myprefs = getSharedPreferences("mysave", MODE_PRIVATE)
        lichsu = myprefs.getString("ls", "") ?: ""
        txtlichsu.text = lichsu

        btntong.setOnClickListener {
            val a = edta.text.toString().toIntOrNull() ?: 0
            val b = edtb.text.toString().toIntOrNull() ?: 0
            val kq = a + b
            edtkq.setText(kq.toString())
            lichsu += "$a + $b = $kq\n"   // thêm lịch sử + xuống dòng
            txtlichsu.text = lichsu
        }

        btnclear.setOnClickListener {
            lichsu = ""
            txtlichsu.text = lichsu
        }
    }
    override fun onPause() {
        super.onPause()
        val myprefs = getSharedPreferences("mysave", MODE_PRIVATE)
        myprefs.edit()
            .putString("ls", lichsu)
            .apply() // dùng apply() cho bất đồng bộ, thay cho commit()
    }
}