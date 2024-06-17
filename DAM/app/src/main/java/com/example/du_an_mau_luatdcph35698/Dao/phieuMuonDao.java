package com.example.du_an_mau_luatdcph35698.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.example.du_an_mau_luatdcph35698.database.dbHelper;
import com.example.du_an_mau_luatdcph35698.model.loaiSach;
import com.example.du_an_mau_luatdcph35698.model.phieuMuon;

import java.util.ArrayList;
import java.util.Date;

public class phieuMuonDao {
    private final dbHelper dbHelper;

    public phieuMuonDao(Context context) {
        dbHelper = new dbHelper(context);
    }
    public ArrayList<phieuMuon> selectAll() {
        ArrayList<phieuMuon> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT pm.mapm,pm.matv,tv.hoten,pm.matt,tt.hoten,pm.masach,sc.tensach,pm.ngay,pm.trasach,pm.tienthue FROM phieuMuon pm,thanhVien tv,thuThu tt,sach sc WHERE pm.matv = tv.matv AND pm.matt = tt.matt AND pm.masach = sc.masach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                phieuMuon pm = new phieuMuon();
                pm.setMaPM(cursor.getInt(0));
                pm.setMaTV(cursor.getInt(1));
                pm.setTenTV(cursor.getString(2));
                pm.setMaTT(cursor.getString(3));
                pm.setTenTT(cursor.getString(4));
                pm.setMaSach(cursor.getInt(5));
                pm.setTenSach(cursor.getString(6));
                pm.setNgay(cursor.getString(7));
                pm.setTraSach(cursor.getInt(8));
                pm.setTienThue(cursor.getInt(9));

                list.add(pm);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean insert(phieuMuon pm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maPM", pm.getMaPM());
        values.put("maTT", pm.getMaTT());
        values.put("maTV", pm.getMaTV());
        values.put("maSach", pm.getMaSach());
        values.put("ngay", pm.getNgay());
        values.put("traSach", pm.getTraSach());
        values.put("tienThue", pm.getTienThue());
        long row = db.insert("phieuMuon", null, values);
        return (row > 0);
    }
    public boolean delete(int maPM){
        SQLiteDatabase db  = dbHelper.getReadableDatabase();
        long row  = db.delete("phieuMuon","maPM =?",new String[]{String.valueOf(maPM)});
        return (row>0);
    }
    public boolean update (@NonNull phieuMuon pm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maPM", pm.getMaPM());
        values.put("maTT", pm.getMaTT());
        values.put("maTV", pm.getMaTV());
        values.put("maSach", pm.getMaSach());
        values.put("ngay", pm.getNgay());
        values.put("traSach", pm.getTraSach());
        values.put("tienThue", pm.getTienThue());
        long row = db.update("phieuMuon", values,"maPM=?",new String[]{String.valueOf(pm.getMaPM())});
        return (row > 0);
    }
    public boolean thayDoiTrangThai(int maPM){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("traSach",1);
        long row =database.update("phieuMuon",values,"maPM=?",new String[]{String.valueOf(maPM)});
        if (row ==-1){
            return false;
        }
        return true;
    }
}
