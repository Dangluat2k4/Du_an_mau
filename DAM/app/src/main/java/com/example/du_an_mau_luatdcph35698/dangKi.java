package com.example.du_an_mau_luatdcph35698;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_mau_luatdcph35698.Dao.thuThuDao;
import com.example.du_an_mau_luatdcph35698.model.thuThu;
import com.google.android.material.textfield.TextInputEditText;

public class dangKi extends AppCompatActivity {
    TextInputEditText edtDKTK, edtDKMK, edtDKNLMK, txtTenTT;
    Button btnDK;
    thuThuDao ttDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        edtDKTK = findViewById(R.id.edtDKTK);
        edtDKMK = findViewById(R.id.edtDKMK);
        edtDKNLMK = findViewById(R.id.edtNLMK);
        txtTenTT = findViewById(R.id.txtTenTT);
        btnDK = findViewById(R.id.btnDK);
        ttDao = new thuThuDao(dangKi.this);
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTT = txtTenTT.getText().toString();
                String userName = edtDKTK.getText().toString();
                String password = edtDKMK.getText().toString();
                String passwordNL = edtDKNLMK.getText().toString();
                if (TextUtils.isEmpty(tenTT) ) {
                    Toast.makeText(dangKi.this, "vui lòng nhâp vào tên thủ thư", Toast.LENGTH_SHORT).show();
                }else if ( TextUtils.isEmpty(userName)){
                    Toast.makeText(dangKi.this, "vui lòng nhập vào mã  thủ thử", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)){
                    Toast.makeText(dangKi.this, "vui lòng nhập vào mật khẩu", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(passwordNL)){
                    Toast.makeText(dangKi.this, "vui lòng nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(passwordNL)){
                    Toast.makeText(dangKi.this, "mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        thuThu thuThu = new thuThu(userName, tenTT, password);
                        if (ttDao.insert(thuThu)) {
                            Toast.makeText(dangKi.this, "đăng ký thành công", Toast.LENGTH_SHORT).show();
                            // gửi dữ liệu đi
                            Intent intent = new Intent(dangKi.this, dangNhap.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(dangKi.this, "đăng ký thấy bại", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(dangKi.this, "đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}