package com.example.bmi_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
Button btn_thongtinkh,btn_tinhchiso,btn_trogiup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }
    private void Init(){
        btn_thongtinkh=findViewById(R.id.btn_thongtin);
        btn_tinhchiso=findViewById(R.id.btn_chiso);
        btn_trogiup=findViewById(R.id.btn_trogiup);
        btn_thongtinkh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,GuestActivity.class);
                startActivity(intent);
            }
        });
        btn_tinhchiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,bmiindexActivity.class);
                startActivity(intent);
            }
        });
        btn_trogiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
