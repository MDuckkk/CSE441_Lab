package com.example.lab13_autocomplete

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var selection: TextView
    private lateinit var singleComplete: AutoCompleteTextView
    private lateinit var multiComplete: MultiAutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        selection = findViewById(R.id.selection)
        singleComplete = findViewById(R.id.editauto)
        multiComplete = findViewById(R.id.multiAutoCompleteTextView1)

        // Dữ liệu gợi ý
        val arr = arrayOf(
            "hà nội", "Huế", "Sài gòn",
            "hà giang", "Bắc an", "Kiên giang",
            "Lâm đồng", "Long khánh"
        )

        // Adapter cho AutoCompleteTextView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
        singleComplete.setAdapter(adapter)

        // Adapter cho MultiAutoCompleteTextView
        val multiAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
        multiComplete.setAdapter(multiAdapter)
        multiComplete.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        // Cập nhật TextView 'selection' khi text thay đổi
        singleComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                selection.text = singleComplete.text
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}