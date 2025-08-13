package com.example.lab17

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val DB_PATH_SUFFIX = "/databases/"
    private val DATABASE_NAME = "qlsach.db"

    private var database: SQLiteDatabase? = null

    // Khai báo ListView & adapter
    private lateinit var lv: ListView
    private lateinit var mylist: ArrayList<String>
    private lateinit var myadapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Copy CSDL từ assets vào /databases nếu chưa có
        processCopy()

        // Mở CSDL để dùng
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null)

        // Tạo ListView + Adapter
        lv = findViewById(R.id.lv)
        mylist = ArrayList()
        myadapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_list_item_1,
            mylist
        )
        lv.adapter = myadapter

        // Truy vấn CSDL và cập nhật ListView
        database?.query("tbsach", null, null, null, null, null, null)?.use { c ->
            if (c.moveToFirst()) {
                do {
                    val data = "${c.getString(0)}-${c.getString(1)}-${c.getString(2)}"
                    mylist.add(data)
                } while (c.moveToNext())
            }
        }

        myadapter.notifyDataSetChanged()
    }
    /** Kiểm tra và copy DB từ assets nếu chưa tồn tại */
    private fun processCopy() {
        val dbFile = getDatabasePath(DATABASE_NAME)
        if (!dbFile.exists()) {
            try {
                copyDatabaseFromAssets()
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG)
                    .show()
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun dbAbsolutePath(): String =
        applicationInfo.dataDir + DB_PATH_SUFFIX + DATABASE_NAME

    /** Thực hiện copy file DB từ assets vào /databases */
    private fun copyDatabaseFromAssets() {
        try {
            // Tạo thư mục nếu chưa có
            val dir = File(applicationInfo.dataDir + DB_PATH_SUFFIX)
            if (!dir.exists()) dir.mkdir()

            assets.open(DATABASE_NAME).use { input ->
                FileOutputStream(dbAbsolutePath()).use { output ->
                    val buffer = ByteArray(4 * 1024)
                    var read: Int
                    while (true) {
                        read = input.read(buffer)
                        if (read <= 0) break
                        output.write(buffer, 0, read)
                    }
                    output.flush()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }
}