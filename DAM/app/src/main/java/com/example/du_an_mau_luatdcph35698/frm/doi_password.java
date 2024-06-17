package com.example.du_an_mau_luatdcph35698.frm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_mau_luatdcph35698.Dao.thuThuDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.model.thuThu;

public class doi_password extends Fragment {
    EditText edtNMK, edtMKM, edtNLMKM;
    Button btnDMKM;
    thuThuDao dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new thuThuDao(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_password, container, false);
        edtNMK = view.findViewById(R.id.edtNMK);
        edtMKM = view.findViewById(R.id.edtMKM);
        edtNLMKM = view.findViewById(R.id.edtNLMKN);
        btnDMKM = view.findViewById(R.id.btnDMKM);
        btnDMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtNMK.getText().toString();
                String newPass = edtMKM.getText().toString();
                String reNewPass = edtNLMKM.getText().toString();
                if (newPass.equals(reNewPass)){
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("thongtin",Context.MODE_PRIVATE);
                    String matt = sharedPreferences.getString("maTT","");
                    // cap nhat
                    thuThuDao thuThuDao =new thuThuDao(getActivity());
                    boolean check = thuThuDao.capNhatMatKhau(matt,oldPass,newPass);
                    if (check){
                        Toast.makeText(getActivity(), "cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public int validate() {
        int check = 1;
        if (edtNMK.getText().length() == 0 || edtMKM.getText().length() == 0 || edtNLMKM.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập thông tin đầy đủ", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            SharedPreferences preferences = getActivity().getSharedPreferences("user_file", Context.MODE_PRIVATE);
            String passOld = preferences.getString("password", "");
            String pass = edtMKM.getText().toString();
            String repass = edtNLMKM.getText().toString();
            if (!passOld.equals(edtNMK.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(repass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
