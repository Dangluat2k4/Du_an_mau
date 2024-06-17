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
import android.widget.Toast;

import com.example.du_an_mau_luatdcph35698.Dao.loaiSachDao;
import com.example.du_an_mau_luatdcph35698.Dao.thanhVienDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.adapter.loaiSachAdapter;
import com.example.du_an_mau_luatdcph35698.model.loaiSach;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class frm_quan_ly_loai_sach extends Fragment {
    RecyclerView rcvDSLS;
    FloatingActionButton fltAddLS;
    loaiSachDao lsDao;
    loaiSachAdapter adapter;
    private ArrayList<loaiSach> list = new ArrayList<>();
    EditText edtAMaLoai, edtATenSach;
    Button btnALoaiSach, btnACancelLS;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view = inflater.inflate(R.layout.fragment_frm_quan_ly_loai_sach, container, false);
         rcvDSLS = view.findViewById(R.id.rscDSLS);
         fltAddLS = view.findViewById(R.id.fltAddlS);
         lsDao = new loaiSachDao(getActivity());
         list = lsDao.selectAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvDSLS.setLayoutManager(layoutManager);
        adapter = new loaiSachAdapter(getActivity(),list);
        rcvDSLS.setAdapter(adapter);
        fltAddLS.setOnClickListener(new View.OnClickListener() {
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
        View view = inflater.inflate(R.layout.item_add_loai_sach,null);
        // gan layout len view
        builder.setView(view);
        // tao hop thoai
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
        edtAMaLoai = view.findViewById(R.id.edtAMaLoaiSach);
        edtATenSach = view.findViewById(R.id.edtATenLoaiSach);
        btnALoaiSach = view.findViewById(R.id.btnALoaiSach);
        btnACancelLS = view.findViewById(R.id.btnACancelLS);
        btnACancelLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnALoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String maLoai =edtAMaLoai.getText().toString();
                String tenLoai = edtATenSach.getText().toString();
                loaiSach ls = new loaiSach(tenLoai);
                if(TextUtils.isEmpty(tenLoai)){
                    Toast.makeText(getActivity(), "vui lòng nhập vào đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if (lsDao.insert(ls)){
                        list.clear();
                        list.addAll(lsDao.selectAll());
                        adapter.notifyDataSetChanged();
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