package com.example.du_an_mau_luatdcph35698.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau_luatdcph35698.database.dbHelper;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;

import java.util.ArrayList;

public class thanhVienDao {
    private final dbHelper dbHelper;

    public thanhVienDao(Context context) {
        dbHelper = new dbHelper(context);
    }

    public ArrayList<thanhVien> selectAll() {
        ArrayList<thanhVien> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from thanhVien", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                // tao thanh vien
                thanhVien thanhVien = new thanhVien();
                // gan thong tin
                thanhVien.setMaTV(cursor.getInt(0));
                thanhVien.setHoTen(cursor.getString(1));
                thanhVien.setNamSinh(cursor.getString(2));
                list.add(thanhVien);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(thanhVien tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put("maTV", tv.getMaTV());
        values.put("hoTen", tv.getHoTen());
        values.put("namSinh", tv.getNamSinh());
        long row = db.insert("thanhVien", null, values);
        return (row > 0);
    }
    public boolean delete(int maTV){
        SQLiteDatabase db  = dbHelper.getReadableDatabase();
        long row  = db.delete("thanhVien","maTV =?",new String[]{String.valueOf(maTV)});
        return (row>0);
    }
    public boolean update (thanhVien tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTV", tv.getMaTV());
        values.put("hoTen", tv.getHoTen());
        values.put("namSinh", tv.getNamSinh());
        long row = db.update("thanhVien", values,"maTV=?",new String[]{String.valueOf(tv.getMaTV())});
        return (row > 0);
    }

}
