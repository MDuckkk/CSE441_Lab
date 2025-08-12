package com.example.lab13_phonelistview_2

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab13_phonelistview_2.adapter.PhoneAdapter
import com.example.lab13_phonelistview_2.model.Phone

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val data = listOf(
            Phone("Điện thoại Sky", 5_000_000, R.drawable.ic_launcher_foreground),
            Phone("Điện thoại SamSung", 10_000_000, R.drawable.ic_launcher_foreground),
            Phone("Điện thoại IP", 20_000_000, R.drawable.ic_launcher_foreground),
            Phone("Điện thoại HTC", 8_000_000, R.drawable.ic_launcher_foreground),
            Phone("Điện thoại LG", 8_500_000, R.drawable.ic_launcher_foreground),
            Phone("Điện thoại WP", 6_000_000, R.drawable.ic_launcher_foreground)
        )
        // Lưu ý: thay các drawable trên bằng ảnh bạn có trong /res/drawable

        val lv = findViewById<ListView>(R.id.lvPhones)
        lv.adapter = PhoneAdapter(this, data)
    }
}