package com.example.bmi_demo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class GuestActivity extends AppCompatActivity {
    final String DATABASE_NAME = "BMIGuestManager1.sqlite";
    //    final String DATABASE_NAME = "BMIGuestManager.db";
    SQLiteDatabase database;
    Button btn_insert, btn_delete, btn_edit;
    Button btn_call, btn_sms;
    ListView liv_khachhang;
   public ArrayList<Guest> arrayList_guest;
    khachhangAdapter khachhangAdapter;
    Guest guestIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guest);
        Init();
        readkhachhang();
        clickListview();
    }

    private void Init() {
        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        btn_edit = findViewById(R.id.btn_edit);
        btn_call = findViewById(R.id.btn_call);
        btn_sms = findViewById(R.id.btn_sms);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guestIntent.getGuestMoblie() == null) {
                    Toast.makeText(GuestActivity.this, "Bạn chưa chọn khách hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(("tel:" + guestIntent.getGuestMoblie())));


                    if (ActivityCompat.checkSelfPermission(GuestActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);


                }
            }

        });
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guestIntent.getGuestMoblie() == null) {
                    Toast.makeText(GuestActivity.this, "Bạn chưa chọn khách hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setData(Uri.parse("smsto:"));
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", new String(guestIntent.getGuestMoblie()));
                    smsIntent.putExtra("sms_body", "Import content  ");
                    startActivity(smsIntent);
                    finish();
                }
            }
        });
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GuestActivity.this, GuestInsert.class);
                startActivity(intent);
//                if(guestIntent.getGuestMoblie()==null)
//                {
//                    Toast.makeText(getApplication(), "Bạn chưa chọn khách hàng", Toast.LENGTH_SHORT).show();
//                }else {

                //Toast.makeText(getApplication(), "Called", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Intent.ACTION_DIAL);
//                    intent.setData(Uri.parse("tel:"+guestIntent.getGuestMoblie()));
//                    startActivity(intent);
//                }
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guestIntent == null) {
                    Toast.makeText(getApplication(), "Bạn chưa chọn sinh viên", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GuestActivity.this);
                    builder.setIcon(android.R.drawable.ic_delete);
                    builder.setTitle("Xóa khách hàng"+guestIntent.getGuestId());
                    builder.setMessage("Bạn có thực sự muốn xóa khách hàng này ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            delete(guestIntent.getGuestId());
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }

        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guestIntent != null) {
                    Intent intent = new Intent(GuestActivity.this, GuestEdit_Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", guestIntent.getGuestId());
                    bundle.putString("name", guestIntent.getGuestName());
                    bundle.putString("birth", guestIntent.getGuestBirth());
                    bundle.putString("gender", guestIntent.getGuestGender());
                    bundle.putString("mobile", guestIntent.getGuestMoblie());
                 //   bundle.putString("mobile", guestIntent.getGuestMoblie());
                    //Đưa Bundle vào Intent
                    intent.putExtra("GuestIntent", bundle);
                    startActivity(intent, bundle);
                } else {
                    Toast.makeText(GuestActivity.this, "Ban chua chon khach hang.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        liv_khachhang = findViewById(R.id.lv_khachhang);
        arrayList_guest = new ArrayList<Guest>();
        khachhangAdapter = new khachhangAdapter(this, arrayList_guest);
        liv_khachhang.setAdapter(khachhangAdapter);
    }

    public void readkhachhang() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM Guest", null);
        arrayList_guest.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Guest guest = new Guest();
            guest.setGuestId(cursor.getInt(0));
            guest.setGuestName(cursor.getString(1));
            guest.setGuestBirth(cursor.getString(2));
            guest.setGuestGender(cursor.getString(3));
            guest.setGuestMoblie(cursor.getString(4));
            arrayList_guest.add(guest);
         //   Toast.makeText(this, "load", Toast.LENGTH_SHORT).show();
        }

        khachhangAdapter.notifyDataSetChanged();
    }

    public void clickListview() {
        liv_khachhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GuestActivity.this, "Đã chọn sv: " + arrayList_guest.get(i).getGuestName() + ", mã sv: " + arrayList_guest.get(i).getGuestId(), Toast.LENGTH_SHORT).show();
                guestIntent = new Guest(arrayList_guest.get(i).getGuestId(), arrayList_guest.get(i).getGuestName(), arrayList_guest.get(i).getGuestBirth(), arrayList_guest.get(i).getGuestGender(), arrayList_guest.get(i).getGuestMoblie());

            }
        });
    }

    public void delete(int id) {
        SQLiteDatabase database = Database.initDatabase(this, "BMIGuestManager1.sqlite");
     //   database.delete("Guest", "GuestId=?", new String[]{id + ""});
        database.delete("Guest", "GuestId=?", new String[]{String.valueOf(id)});
       arrayList_guest.clear();
        readkhachhang();
    }
}
