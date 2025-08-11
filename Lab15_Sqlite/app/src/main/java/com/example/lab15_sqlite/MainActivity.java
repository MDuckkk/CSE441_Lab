package com.example.lab15_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextNumber, editTextText, editTextText2;
    Button btnInsert, btnUpdate, btnDelete, btnQuery;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myAdapter;
    SQLiteDatabase myDatabase;
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
        editTextText = findViewById(R.id.editTextText);
        editTextText2 = findViewById(R.id.editTextText2);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnQuery = findViewById(R.id.btnQuery);
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myAdapter);

        myDatabase = openOrCreateDatabase("QLSV", MODE_PRIVATE, null);
        try {
            String sql = "CREATE TABLE tbllop(malop TEXT PRIMARY KEY, tenlop TEXT, siso INTEGER)";
            myDatabase.execSQL(sql);
        } catch (Exception e) {
            Log.e("Error", "Table already exists");
        }

        // Xử lý nút Insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = editTextText.getText().toString().trim();
                String tenlop = editTextText2.getText().toString().trim();
                int siso = Integer.parseInt(editTextNumber.getText().toString().trim());
                ContentValues values = new ContentValues();
                values.put("malop", malop);
                values.put("tenlop", tenlop);
                values.put("siso", siso);
                long result = myDatabase.insert("tbllop", null, values);
                String msg = (result == -1) ? "Fail to Insert Record!" : "Insert record Successfully";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Update check
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = editTextNumber.getText().toString().trim();
                int siso = Integer.parseInt(editTextText2.getText().toString().trim());
                ContentValues values = new ContentValues();
                values.put("siso", siso);
                int count = myDatabase.update("tbllop", values, "malop = ?", new String[]{malop});
                String msg = (count == 0) ? "No record to Update" : count + " record(s) updated";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = editTextNumber.getText().toString().trim();
                int count = myDatabase.delete("tbllop", "malop = ?", new String[]{malop});
                String msg = (count == 0) ? "No record to Delete" : count + " record(s) deleted";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút Query
        btnQuery.setOnClickListener(v -> {
            mylist.clear();
            Cursor c = myDatabase.query("tbllop", null, null, null, null, null, null);
            if (c.moveToFirst()) {
                do {
                    String item = c.getString(c.getColumnIndexOrThrow("malop"))
                            + " - " + c.getString(c.getColumnIndexOrThrow("tenlop"))
                            + " - " + c.getInt(c.getColumnIndexOrThrow("siso"));
                    mylist.add(item);
                } while (c.moveToNext());
            }
            c.close();
            myAdapter.notifyDataSetChanged();
        });
    }
}