package com.example.bmi_demo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GuestInsert extends AppCompatActivity {

    final String DATABASE_NAME = "BMIGuestManager.sqlite";
    //final String DATABASE_NAME = "BMIGuestManager.db";
    Button bt_isert;
    EditText edt_id,edtname,edtbirth,edtgender,edtmobile;
    String id,name,birth,gender,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_insert);
        bt_isert = findViewById(R.id.btn_update1);
        edt_id = findViewById(R.id.edt_guestid1);
        edtname = findViewById(R.id.edt_guestname1);
        edtbirth = findViewById(R.id.edt_guestbirth1);
        edtgender = findViewById(R.id.edt_guestgender1);
        edtmobile = findViewById(R.id.edt_guestmobile1);
        bt_isert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insert();
            }
        });
    }
    public  void Insert(){
        ContentValues contentValues = new ContentValues();
        //contentValues.put("GuestId",Integer.parseInt(edt_id.getText().toString()));
        contentValues.put("GuestName", edtname.getText().toString());
        contentValues.put("GuestBirth", edtbirth.getText().toString());
        contentValues.put("GuestGender", edtgender.getText().toString());
        contentValues.put("GuestMobile", edtmobile.getText().toString());

        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        database.insert("Guest",null,contentValues);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
