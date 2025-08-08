package com.example.lab12_note

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {
    var lv: ListView? = null
    var arraywork: ArrayList<String>? = null
    var arrAdapater: ArrayAdapter<String>? = null
    var edtwork: EditText? = null
    var edth: EditText? = null
    var edtm: EditText? = null
    var txtdate: TextView? = null
    var txtdate2: TextView? = null
    var btnwork: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtmin);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtdate);
        txtdate2 = findViewById(R.id.txtdate2);
        // Khai báo mảng ArrayList rỗng làm data source
        val arraywork = ArrayList<String>()

// Khai báo ArrayAdapter và đưa data source vào
        val arrAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arraywork)

// Đưa Adapter lên ListView
        lv?.adapter = arrAdapter

// Lấy ngày giờ hệ thống
        val currentDate = Calendar.getInstance().time

// Format theo định dạng dd/MM/yyyy
        val simpleDateFormat = java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

// Hiển thị lên TextView
        txtdate2?.text = "Hôm Nay: ${simpleDateFormat.format(currentDate)}"

// Viết sự kiện khi click vào Button btnwork
        btnwork?.setOnClickListener {
            val work = edtwork?.text.toString().trim()
            val h = edth?.text.toString().trim()
            val m = edtm?.text.toString().trim()

            // Nếu 1 trong 3 EditText rỗng -> hiện AlertDialog
            if (work.isEmpty() || h.isEmpty() || m.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Info missing")
                    .setMessage("Please enter all information of the work")
                    .setPositiveButton("Continue", null)
                    .show()
            } else {
                // Lấy nội dung công việc và thời gian, đưa vào mảng & cập nhật Adapter
                val str = "$work - $h:$m"
                arraywork.add(str) // Xóa: arraywork.removeAt(i)
                arrAdapter.notifyDataSetChanged()

                // Clear các ô nhập
                edth?.text?.clear()
                edtm?.text?.clear()
                edtwork?.text?.clear()
            }
        }
    }
}