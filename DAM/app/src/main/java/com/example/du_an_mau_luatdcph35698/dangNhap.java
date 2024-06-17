package com.example.du_an_mau_luatdcph35698;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.du_an_mau_luatdcph35698.Dao.thuThuDao;
import com.example.du_an_mau_luatdcph35698.frm.frm_quan_ly_phieu_muon;
import com.google.android.material.textfield.TextInputEditText;

public class dangNhap extends AppCompatActivity {
    Button btnDN, btnCancel;
    TextInputEditText edtTK, edtMK;
    CheckBox chkRMB;
    thuThuDao ttDao;
    String user, pass;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btnDN = findViewById(R.id.btnDN);
        edtTK = findViewById(R.id.edtTK);
        edtMK = findViewById(R.id.edtMK);
        chkRMB = findViewById(R.id.chkRMB);
        btnCancel = findViewById(R.id.btnCancel);
        ttDao = new thuThuDao(dangNhap.this);
        preferences = getSharedPreferences("thongtin",MODE_PRIVATE);
        boolean rememberTK = preferences.getBoolean("luu_tk",false);
        chkRMB.setChecked(rememberTK);
        if (rememberTK){
            String savedName= preferences.getString("username","");
            String savedPass= preferences.getString("password","");
            //ten

            edtTK.setText(savedName);
            edtMK.setText(savedPass);
        }
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = edtTK.getText().toString();
                pass = edtMK.getText().toString();
                if (TextUtils.isEmpty(user)){
                    Toast.makeText(dangNhap.this, "vui lòng điền nhập vào tài khoản", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(pass)){
                    Toast.makeText(dangNhap.this, "Vui lòng nhập vào mật khẩu", Toast.LENGTH_SHORT).show();
                }
                try {
                    if (ttDao.checkLogin(user, pass)){
                        Intent intent = new Intent(dangNhap.this,navigationDrawer.class);
                        intent.putExtra("username",user);
                        startActivity(intent);
                        if (chkRMB.isChecked()){
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("username",user);
                            editor.putString("password",pass);
                            editor.putBoolean("luu_tk",true);
                            editor.apply();
                        }else {
                            SharedPreferences.Editor editor  = preferences.edit();
                            editor.putString("username",user);
                            editor.putString("password",pass);
                            editor.putBoolean("luu_tk",false);
                            editor.apply();
                        }
                        Toast.makeText(dangNhap.this, "đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(dangNhap.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(dangNhap.this, "đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMK.setText("");
                edtTK.setText("");
            }
        });
    }
}

