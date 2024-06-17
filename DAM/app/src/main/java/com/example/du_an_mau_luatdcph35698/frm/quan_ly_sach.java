package com.example.du_an_mau_luatdcph35698.frm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_mau_luatdcph35698.Dao.loaiSachDao;
import com.example.du_an_mau_luatdcph35698.Dao.sachDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.adapter.sachAdapter;
import com.example.du_an_mau_luatdcph35698.model.loaiSach;
import com.example.du_an_mau_luatdcph35698.model.sach;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class quan_ly_sach extends Fragment {
    RecyclerView rcvSach;
    FloatingActionButton fltAddSach;
    sachDao sachDao;
    sachAdapter adapter;
    EditText edtATenSach, edtAGiaThue;
    Spinner spnMaLoai;
    Button btnASach, btnACancelS;
    private ArrayList<sach> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
        rcvSach = view.findViewById(R.id.rscDSSach);
        fltAddSach = view.findViewById(R.id.fltAddSach);
        sachDao = new sachDao(getActivity());
        list = sachDao.selectAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvSach.setLayoutManager(layoutManager);
        adapter = new sachAdapter(getActivity(), list);
        rcvSach.setAdapter(adapter);
        fltAddSach.setOnClickListener(new View.OnClickListener() {
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
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_sach, null);
        // gan layout len view
        builder.setView(view);
        // tao hop thoai
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
//        edtAMaSach = view.findViewById(R.id.edtAMaSach);
        edtATenSach = view.findViewById(R.id.edtATenSach);
        edtAGiaThue = view.findViewById(R.id.edtAgiaThue);
        spnMaLoai = view.findViewById(R.id.spnMaLoai);
        btnASach = view.findViewById(R.id.btnASach);
        btnACancelS = view.findViewById(R.id.btnACancelS);
        btnACancelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLS(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1}
        );
        spnMaLoai.setAdapter(simpleAdapter);
        btnASach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSach = edtATenSach.getText().toString();
                String tienT = edtAGiaThue.getText().toString();


                if (TextUtils.isEmpty(tenSach)) {
                    Toast.makeText(getActivity(), "vui lòng nhập vào tên sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tienT)) {
                    Toast.makeText(getActivity(), "vui lòng nhập vào giá ", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int giaThue =Integer.parseInt(tienT);
//                    int tien = Integer.parseInt(edtAGiaThue.getText().toString());
                    HashMap<String, Object> hsLS = (HashMap<String, Object>) spnMaLoai.getSelectedItem();
                    int maLoai = (int) hsLS.get("maLoai");
                    boolean check = sachDao.insert(tenSach, giaThue, maLoai);
                    if (check) {
                        list.clear();
                        list.addAll(sachDao.selectAll());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "thêm thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "giá phải là số", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    ;

    private ArrayList<HashMap<String, Object>> getDSLS() {
        loaiSachDao loaiSachDao = new loaiSachDao(getContext());
        ArrayList<loaiSach> list1 = loaiSachDao.selectAll();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (loaiSach loaiSach : list1) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maLoai", loaiSach.getMaLoai());
            hs.put("tenLoai", loaiSach.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }
}
//        btnASach.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HashMap<String,Object> hsLS = (HashMap<String, Object>) spnMaLoai.getSelectedItem();
//                int maLoai = (int) hsLS.get("maLoai");
//                String maSach = edtAMaSach.getText().toString();
//                String tenSach = edtATenSach.getText().toString();
//                String giaThue = edtAGiaThue.getText().toString();
//                SharedPreferences sharedPreferences = getContext().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
//                String maTT = sharedPreferences.getString("maTT","");
//                sach sach = new sach(Integer.parseInt(maSach),tenSach,Integer.parseInt(giaThue),maLoai);
//                if(TextUtils.isEmpty(maSach)||TextUtils.isEmpty(tenSach)||TextUtils.isEmpty(giaThue)){
//                    Toast.makeText(getActivity(), "vui lòng nhập vào đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//                }else {
//                    if (sachDao.insert(sach)){
//                        list.clear();
//                        list.addAll(sachDao.selectAll());
//                        adapter.notifyDataSetChanged();
//                        dialog.dismiss();
//                        Toast.makeText(getActivity(), "thêm thành công", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(getActivity(), "thêm thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                }
////                String maLoai = edtAMaLoai.getText().toString();
////                sach  sach = new sach(Integer.parseInt(maSach),tenSach,Integer.parseInt(giaThue),Integer.parseInt(maLoai));
//
//            }
//        });