package com.example.du_an_mau_luatdcph35698.frm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.du_an_mau_luatdcph35698.Dao.thanhVienDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.adapter.thanhvienAdapter;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class quan_ly_thanh_vien extends Fragment {
    RecyclerView rcvDSTV;
    FloatingActionButton fltAddTV;
    thanhVienDao TVDao;
    thanhvienAdapter TVAdapter;

    EditText edtAMaTV, edtATenTV, edtANamSinh;
    Button btnATV, btnACancel;
    private ArrayList<thanhVien> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
        rcvDSTV = view.findViewById(R.id.rscDSTV);
        fltAddTV = view.findViewById(R.id.fltAddTV);
        TVDao = new thanhVienDao(getActivity());
        list = TVDao.selectAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvDSTV.setLayoutManager(layoutManager);
        TVAdapter = new thanhvienAdapter(getActivity(), list);
        rcvDSTV.setAdapter(TVAdapter);
        fltAddTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogAdd();
            }
        });
        return view;
    }

    public void openDiaLogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // taolay out
        LayoutInflater inflater =getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_thanh_vien,null);
        // gan layout len view
        builder.setView(view);
        // tao hop thoai
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
//        edtAMaTV = view.findViewById(R.id.edtAMaTV);
        edtATenTV = view.findViewById(R.id.edtATenTV);
        edtANamSinh = view.findViewById(R.id.edtANamSinh);
        btnATV = view.findViewById(R.id.btnATV);
        btnACancel = view.findViewById(R.id.btnACancel);
        btnACancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnATV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String maTV =edtAMaTV.getText().toString();
                String tenTV = edtATenTV.getText().toString();
                String namsinh = edtANamSinh.getText().toString();

                thanhVien tv = new thanhVien(tenTV,namsinh);
                if(TextUtils.isEmpty(tenTV)||TextUtils.isEmpty(namsinh)){
                    Toast.makeText(getActivity(), "vui lòng nhập vào đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (TVDao.insert(tv)){
                        list.clear();
                        list.addAll(TVDao.selectAll());
                        TVAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "thêm thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}