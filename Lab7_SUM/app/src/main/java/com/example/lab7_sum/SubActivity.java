package com.example.lab7_sum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    EditText editTextNumber4, editTextNumber5;
    Button button2, button3;
    Intent myintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextNumber4 = findViewById(R.id.editTextNumber4);
        editTextNumber5 = findViewById(R.id.editTextNumber5);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        // Nhận Intent
        myintent = getIntent();
        // lấy dữ liệu khỏi Intent
        int a = myintent.getIntExtra("soa",0);
        int b = myintent.getIntExtra("sob",0);
        editTextNumber4.setText(a+"");
        editTextNumber5.setText(b+"");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xử lý kết quả
                int sum = a + b;
                // Đẩy kết quả trở lại Intent
                myintent.putExtra("kq",sum);
                // Trả intent trở về
                setResult(33,myintent);
                //thoát Activity này để quay về
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xử lý kết quả
                int sub = a - b;
                myintent.putExtra("kq",sub);
                setResult(34, myintent);
                finish();
            }
        });
    }
}