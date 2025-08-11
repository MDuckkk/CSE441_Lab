package com.example.lab13_imggridview_2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.GridView
import com.example.lab13_imggridview_2.adapter.MyArrayAdapter
import com.example.lab13_imggridview_2.model.Image

class MainActivity : AppCompatActivity() {

    // 12 URL ảnh demo
    private val urlImages = listOf(
        "https://img.tripi.vn/cdn-cgi/image/width=700,height=700/https://gcs.tripi.vn/public-tripi/tripi-feed/img/482812ZYV/anh-mo-ta.png",
        "https://img.tripi.vn/cdn-cgi/image/width=700,height=700/https://gcs.tripi.vn/public-tripi/tripi-feed/img/482812ZYV/anh-mo-ta.png",
        "https://d1hjkbq40fs2x4.cloudfront.net/2017-08-21/files/landscape-photography_1645.jpg",
        "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/10/tai-anh-phong-canh-dep-45.jpg",
        "https://i.pinimg.com/736x/91/36/9d/91369d9fbf87b5b8f3e495959ccc11e8.jpg",
        "https://halotravel.vn/wp-content/uploads/2021/09/anh-phong-canh-dep-15.jpg",
        "https://img.tripi.vn/cdn-cgi/image/width=700,height=700/https://gcs.tripi.vn/public-tripi/tripi-feed/img/482812ZYV/anh-mo-ta.png",
        "https://img.tripi.vn/cdn-cgi/image/width=700,height=700/https://gcs.tripi.vn/public-tripi/tripi-feed/img/482812ZYV/anh-mo-ta.png",
        "https://d1hjkbq40fs2x4.cloudfront.net/2017-08-21/files/landscape-photography_1645.jpg",
        "https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/10/tai-anh-phong-canh-dep-45.jpg",
        "https://i.pinimg.com/736x/91/36/9d/91369d9fbf87b5b8f3e495959ccc11e8.jpg",
        "https://halotravel.vn/wp-content/uploads/2021/09/anh-phong-canh-dep-15.jpg"
    )
    private val names = List(12) { "Ảnh ${it + 1}" }
    private val arrimage = List(12) { i -> Image(urlImages[i], names[i]) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val gridView = findViewById<GridView>(R.id.grid1)
        val adapter = MyArrayAdapter(this, R.layout.list_item, arrimage)
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("name", arrimage[position].name)
            intent.putExtra("url", arrimage[position].url)
            startActivity(intent)
        }
    }
}