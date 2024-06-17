package com.example.du_an_mau_luatdcph35698.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau_luatdcph35698.database.dbHelper;
import com.example.du_an_mau_luatdcph35698.model.loaiSach;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;

import java.util.ArrayList;

public class loaiSachDao {
    private final dbHelper dbHelper;

    public loaiSachDao(Context context) {
        dbHelper = new dbHelper(context);
    }
    public ArrayList<loaiSach> selectAll() {
        ArrayList<loaiSach> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from loaiSach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                // tao thanh vien
                loaiSach ls = new loaiSach();
                // gan thong tin
                ls.setMaLoai(cursor.getInt(0));
                ls.setTenLoai(cursor.getString(1));
                list.add(ls);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean insert(loaiSach ls) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maLoai", ls.getMaLoai());
        values.put("tenLoai", ls.getTenLoai());
        long row = db.insert("loaiSach", null, values);
        return (row > 0);
    }
    public boolean delete(int maLoai){

        SQLiteDatabase db  = dbHelper.getReadableDatabase();
        long row  = db.delete("loaiSach","maLoai =?",new String[]{String.valueOf(maLoai)});
        return (row>0);
    }
    public boolean update (loaiSach ls) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maLoai", ls.getMaLoai());
        values.put("tenLoai", ls.getTenLoai());
        long row = db.update("loaiSach", values,"maLoai=?",new String[]{String.valueOf(ls.getMaLoai())});
        return (row > 0);
    }
}
