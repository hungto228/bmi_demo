package com.example.bmi_demo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class GuestEdit_Activity extends AppCompatActivity {
    final String DATABASE_NAME = "BMIGuestManager.sqlite";
 //   final String DATABASE_NAME = "BMIGuestManager.db";
    Button btn_update;
    EditText edt_id, edtname, edtbirth, edtgender, edtmobile;
    String id, name, birth, gender, mobile;
Guest guestinten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest_edit);
        Init();
        getData();
    }

    private void Init() {
        btn_update = findViewById(R.id.btn_update);
        edt_id = findViewById(R.id.edt_guestid);
        edtname = findViewById(R.id.edt_guestname);
        edtbirth = findViewById(R.id.edt_guestbirth);
        edtgender = findViewById(R.id.edt_guestgender);
        edtmobile = findViewById(R.id.edt_guestmobile);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

    }

    public void getData() {
        //lấy intent gọi Activity này
        Intent callerIntent = getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller =
                callerIntent.getBundleExtra("GuestIntent");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob

        id = packageFromCaller.getString("id");
        name = packageFromCaller.getString("name");
        birth = packageFromCaller.getString("birth");
        gender = packageFromCaller.getString("gender");
        mobile = packageFromCaller.getString("mobile");


        edt_id.setText(id);
        edtname.setText(name);
        edtbirth.setText(birth);
        edtgender.setText(gender);
        edtmobile.setText(mobile);


    }

    public void update() {
        // update dulieu
        ContentValues contentValues = new ContentValues();
        contentValues.put("GuestId",Integer.parseInt(edt_id.getText().toString()));
        contentValues.put("GuestName", edtname.getText().toString());
        contentValues.put("GuestBirth", edtbirth.getText().toString());
        contentValues.put("GuestGender", edtgender.getText().toString());
        contentValues.put("GuestMobile", edtmobile.getText().toString());

        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        database.update("Guest", contentValues, "GuestId = ?", new String[]{edt_id.getText().toString() + ""});
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
