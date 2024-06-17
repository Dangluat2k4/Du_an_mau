package com.example.du_an_mau_luatdcph35698.frm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_mau_luatdcph35698.Dao.phieuMuonDao;
import com.example.du_an_mau_luatdcph35698.Dao.sachDao;
import com.example.du_an_mau_luatdcph35698.Dao.thanhVienDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.adapter.phieuMuonAdapter;
import com.example.du_an_mau_luatdcph35698.model.phieuMuon;
import com.example.du_an_mau_luatdcph35698.model.sach;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class frm_quan_ly_phieu_muon extends Fragment {
    RecyclerView rcvPhieuMuon;
    FloatingActionButton  fltAddPM;
    phieuMuonDao pmDao;
    phieuMuonAdapter adapter;
    Spinner spnAddTV, spnAddSach;
    Button btnAddPhieuMuon, btnCancel;
//    EditText edtAddTienThue;
    private ArrayList<phieuMuon> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_frm_quan_ly_phieu_muon, container, false);
       rcvPhieuMuon = view.findViewById(R.id.rscPhieuMuon);
       fltAddPM = view.findViewById(R.id.fltAddPhieuMuon);
       pmDao = new phieuMuonDao(getActivity());
       list = pmDao.selectAll();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvPhieuMuon.setLayoutManager(layoutManager);
        adapter = new phieuMuonAdapter(getActivity(),list);
        rcvPhieuMuon.setAdapter(adapter);
        fltAddPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogAddPM();
            }
        });
       return view;
    }
    private void openDiaLogAddPM(){
        AlertDialog.Builder  builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_phieu_muon,null);
        spnAddTV = view.findViewById(R.id.spnAddTV);
        spnAddSach = view.findViewById(R.id.spnAddSach);
//        edtAddTienThue = view.findViewById(R.id.edtAddTienThue);
        btnAddPhieuMuon = view.findViewById(R.id.btnAddPM);
        btnCancel = view.findViewById(R.id.btnCancelPM);

        getDataTV(spnAddTV);
        getDataSach(spnAddSach);
        builder.setView(view);
        AlertDialog  dialog = builder.create();
        dialog.show();
        btnAddPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lay thong tin
                // lay ma thanh vien
                HashMap<String,Object> hsTV = (HashMap<String, Object>) spnAddTV.getSelectedItem();
                int maTV = (int) hsTV.get("maTV");
                // lay ma sach
                HashMap<String,Object> hsS = (HashMap<String, Object>) spnAddSach.getSelectedItem();
                int maSach = (int) hsS.get("maSach");
//                int tien  = Integer.parseInt(edtAddTienThue.getText().toString());
                int tien = (int) hsS.get("giaThue");
                themPM(maTV,maSach,tien);
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    private void getDataTV(Spinner spnAddTV){
        thanhVienDao thanhVienDao = new thanhVienDao(getActivity());
        ArrayList<thanhVien> list1 = thanhVienDao.selectAll();
        ArrayList<HashMap<String,Object>>listHM = new ArrayList<>();
        // duyet thong tin
        for (thanhVien tv: list1){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maTV",tv.getMaTV());
            hs.put("hoTen",tv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter  simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"hoTen"},new int[]{android.R.id.text1});
        spnAddTV.setAdapter(simpleAdapter);
    }
    private  void getDataSach(Spinner spnAddSach){
        sachDao sachDao = new sachDao(getActivity());
        ArrayList<sach> list1 = sachDao.selectAll();
        ArrayList<HashMap<String,Object>>listHM = new ArrayList<>();
        // duyet thong tin
        for (sach sach: list1){
            HashMap<String,Object>hs = new HashMap<>();
            hs.put("maSach",sach.getMaSach());
            hs.put("tenSach",sach.getTenSach());
            hs.put("giaThue",sach.getGiaThue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"tenSach"},new int[]{android.R.id.text1});
        spnAddSach.setAdapter(simpleAdapter);
    }
    private void themPM(int maTV,int maSach,int tien){
            // lay ma tt
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("thongtin", Context.MODE_PRIVATE);
        String maTT = sharedPreferences.getString("maTT","");
        // lay ngay hien tai
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(date);
        phieuMuon phieuMuon = new phieuMuon(maTT,maTV,maSach,ngay,0,tien);
        boolean kiemTra = pmDao.insert(phieuMuon);
        if (kiemTra){
            Toast.makeText(getActivity(), "thêm thành công", Toast.LENGTH_SHORT).show();
            pmDao = new phieuMuonDao(getActivity());
            list = pmDao.selectAll();
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rcvPhieuMuon.setLayoutManager(layoutManager);
            adapter = new phieuMuonAdapter(getActivity(),list);
            rcvPhieuMuon.setAdapter(adapter);
        }else {
            Toast.makeText(getActivity(), "thêm thấy bại", Toast.LENGTH_SHORT).show();
        }
    }
}