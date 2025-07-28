package com.example.lab5_pt;

import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextNumber, editTextNumber2, editTextNumber3;
    Button btnTiep, btnGiaiPT, btnThoat;
    TextView textView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextNumber3 = findViewById(R.id.editTextNumber3);
        btnTiep = findViewById(R.id.btnTiep);
        btnGiaiPT = findViewById(R.id.btnGiaiPT);
        btnThoat = findViewById(R.id.btnThoat);
        textView5 = findViewById(R.id.textView5);
        btnTiep.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editTextNumber.setText("");
                editTextNumber2.setText("");
                editTextNumber3.setText("");
                editTextNumber.requestFocus();
            }
        });
        btnGiaiPT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String sa=editTextNumber.getText()+"";
                String sb=editTextNumber2.getText()+"";
                String sc=editTextNumber3.getText()+"";
                int a=Integer.parseInt(sa);
                int b=Integer.parseInt(sb);
                int c=Integer.parseInt(sc);
                String kq="";
                DecimalFormat dcf=new DecimalFormat("0.00");
                if(a==0)
                {
                    if(b==0)
                    {
                        if(c==0)
                            kq="PT vô số nghiệm";
                        else
                            kq="PT vô nghiệm";
                    }
                    else
                    {
                        kq="Pt có 1 No, x="+dcf.format(-c/b);
                    }
                }
                else
                {
                    double delta=b*b-4*a*c;
                    if(delta<0)
                    {
                        kq="PT vô nghiệm";
                    }
                    else if(delta==0)
                    {
                        kq="Pt có No kép x1=x2="+dcf.format(-b/(2*a));
                    }
                    else
                    {
                        kq = "Pt có 2 No: x1=" + dcf.format((-b + Math.sqrt(delta)) / (2 * a)) +
                                "; x2=" + dcf.format((-b - Math.sqrt(delta)) / (2 * a));

                    }
                }
                textView5.setText(kq);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
    }
}