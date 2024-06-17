package com.example.du_an_mau_luatdcph35698.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau_luatdcph35698.database.dbHelper;
import com.example.du_an_mau_luatdcph35698.model.loaiSach;
import com.example.du_an_mau_luatdcph35698.model.sach;

import java.util.ArrayList;

public class sachDao {
    private final dbHelper dbHelper;

    public sachDao(Context context) {
        dbHelper = new dbHelper(context);
    }
    public ArrayList<sach> selectAll() {
        ArrayList<sach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from sach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                // tao thanh vien
                sach sach = new sach();
                // gan thong tin
                sach.setMaSach(cursor.getInt(0));
                sach.setTenSach(cursor.getString(1));
                sach.setGiaThue(cursor.getInt(2));
                sach.setMaLoai(cursor.getInt(3));
                list.add(sach);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean insert(String tenSach,int giaTien,int maLoai) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maSach",sach.getMaSach());
        values.put("tenSach",tenSach);
        values.put("giaThue",giaTien);
        values.put("maLoai",maLoai);
        long row = db.insert("sach", null, values);
        return (row > 0);
    }
    public boolean delete(int maSach){
        SQLiteDatabase db  = dbHelper.getReadableDatabase();
        long row  = db.delete("sach","maSach=?",new String[]{String.valueOf(maSach)});
        return (row>0);
    }
    public boolean update (sach sach) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maSach",sach.getMaSach());
        values.put("tenSach",sach.getTenSach());
        values.put("giaThue",sach.getGiaThue());
//        values.put("maLoai",sach.getMaLoai());
        long row = db.update("sach", values,"maSach=?",new String[]{String.valueOf(sach.getMaSach())});
        return (row > 0);
    }
}
