package com.example.lab8_call;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallPhoneActivity extends AppCompatActivity {
    EditText edtcall;
    ImageButton btncall;
    Button btnback1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call_phone);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.call_phone), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtcall = findViewById(R.id.edtcall);
        btncall = findViewById(R.id.btncall);
        btnback1 = findViewById(R.id.btnback1);
        btncall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent callintent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +edtcall.getText().toString()));
                if (ActivityCompat.checkSelfPermission(CallPhoneActivity.this,
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CallPhoneActivity.this, new
                            String[]{android.Manifest.permission.CALL_PHONE},1);
                    return;
                }
                startActivity(callintent);
             }

        });
        btnback1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

    }
}