package com.example.lab19

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity() {
    private lateinit var btnParse: Button
    private lateinit var lv: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val rows = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnParse = findViewById(R.id.btnparse)
        lv = findViewById(R.id.lv)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, rows)
        lv.adapter = adapter

        btnParse.setOnClickListener { parseXml() }
    }
    private fun parseXml() {
        rows.clear()

        // 1) Mở file trong assets
        assets.open("employee.xml").use { input ->
            // 2) Tạo PullParser
            val factory = XmlPullParserFactory.newInstance().apply {
                isNamespaceAware = false
            }
            val parser = factory.newPullParser().apply {
                setInput(input, "UTF-8")
            }

            // 3) Duyệt tài liệu
            var eventType = parser.eventType
            var line = StringBuilder()

            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        when (parser.name) {
                            "employee" -> {
                                // lấy attribute theo tên an toàn
                                val id = parser.getAttributeValue(null, "id") ?: ""
                                val title = parser.getAttributeValue(null, "title") ?: ""
                                line.append("$id-$title-")
                            }
                            "name" -> {
                                line.append(parser.nextText()).append("-")
                            }
                            "phone" -> {
                                line.append(parser.nextText())
                            }
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if (parser.name == "employee") {
                            rows.add(line.toString())
                            line = StringBuilder()
                        }
                    }
                }
                eventType = parser.next()
            }
        }

        adapter.notifyDataSetChanged()
    }
}