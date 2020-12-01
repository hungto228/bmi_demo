package com.example.bmi_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class bmiindexActivity extends AppCompatActivity {
EditText edt_chieucao,edt_cannang;
Button btn_tinhchiso;
TextView tv_chisobmi,tv_danhgia;
float chiso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmiindex);
        Init();
        btn_tinhchiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_cannang.getText().toString().equals("")||edt_chieucao.getText().toString().equals("")){
                    Toast.makeText(bmiindexActivity.this, "Bạn phải nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();

                }  else {
                    chiso=Float.parseFloat(edt_chieucao.getText().toString())/(Float.parseFloat(edt_cannang.getText().toString())*Float.parseFloat(edt_cannang.getText().toString()));
                    tv_chisobmi.setText("Chỉ số BMI: "+chiso);
                    if(chiso<18.5){
                        tv_danhgia.setText("Đánh giá: GẦY");
                    }else {
                        if(18.5<=chiso&&chiso<=24.9){
                            tv_danhgia.setText("Đánh giá: BÌNH THƯỜNG");
                        } else {
                            if(chiso>24.9){
                                tv_danhgia.setText("Đánh giá: BÉO PHÌ");
                            }
                        }
                    }


                }
            }
        });
    }
    private  void Init(){
        edt_chieucao=findViewById(R.id.edt_chieucao);
        edt_cannang=findViewById(R.id.edt_cannang);
        btn_tinhchiso=findViewById(R.id.btn_tinhchiso);
        tv_chisobmi=findViewById(R.id.tv_chisobmi);
        tv_danhgia=findViewById(R.id.tv_danhgia);
    }
}
