package com.example.lab6;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextText, editTextText2, editTextTextMultiLine;
    CheckBox checkBox, checkBox2, checkBox3;
    Button button;
    RadioGroup idgroup;
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
        editTextText = findViewById(R.id.editTextText);
        editTextText2 = findViewById(R.id.editTextText2);
        editTextTextMultiLine = findViewById(R.id.editTextTextMultiLine);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        button = findViewById(R.id.button);
        idgroup = findViewById(R.id.idgroup);
        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               doShowInformation();
           }
        });
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Question");
                b.setMessage("Are you sure you want to exit?");
                b.setIcon(R.drawable.inform);
                b.setPositiveButton("Yes", (dialog, which) -> finish());
                b.setNegativeButton("No", (dialog, which) -> dialog.cancel());
                b.create().show();
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
    }
    public void doShowInformation()
    {
        String ten=editTextText.getText().toString();
        ten=ten.trim();
        if(ten.length()<3)
        {
            editTextText.requestFocus();
            editTextText.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự",
                    Toast.LENGTH_LONG).show();
            return;
        }
        String cmnd=editTextText2.getText().toString();
        cmnd=cmnd.trim();
        if(cmnd.length()!=9)
        {
            editTextText2.requestFocus();
            editTextText2.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        String bang="";
        idgroup = findViewById(R.id.idgroup);
        int id=idgroup.getCheckedRadioButtonId();// Trả về Id
        if(id==-1)
        {
            Toast.makeText(this, "Phải chọn bằng cấp",
                    Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad= findViewById(id);
        bang=rad.getText()+"";
        String sothich="";
        if(checkBox.isChecked())
            sothich+=checkBox.getText()+"\n";
        if(checkBox2.isChecked())
            sothich+=checkBox2.getText()+"\n";
        if(checkBox3.isChecked())
            sothich+=checkBox3.getText()+"\n";
        String bosung=editTextTextMultiLine.getText()+"";
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new
                DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });
        String msg=ten+"\n";
        msg+= cmnd+"\n";
        msg+=bang+"\n";
        msg+=sothich;
        msg+="—————————–\n";
        msg+="Thông tin bổ sung:\n";
        msg+=bosung+ "\n";
        msg+="—————————–";
        builder.setMessage(msg);
        builder.create().show();
    }
}

