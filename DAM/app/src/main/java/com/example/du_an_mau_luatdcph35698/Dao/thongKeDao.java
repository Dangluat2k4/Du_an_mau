package com.example.du_an_mau_luatdcph35698.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau_luatdcph35698.database.dbHelper;
import com.example.du_an_mau_luatdcph35698.model.sach;

import java.util.ArrayList;

public class thongKeDao {
    private final dbHelper dbHelper;

    public thongKeDao(Context context) {
        dbHelper = new dbHelper(context);
    }

    public ArrayList<sach> getTop10() {
        ArrayList<sach> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = database.rawQuery("SELECT pm.masach,sc.tensach, COUNT (pm.masach) FROM phieuMuon pm, sach sc WHERE pm.masach = sc.masach GROUP by pm.masach,sc.tensach order by COUNT(pm.masach) DESC LIMIT 10", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                sach sach = new sach();
                sach.setMaSach(cursor.getInt(0));
                sach.setTenSach(cursor.getString(1));
                sach.setSoLuongDaMuon(cursor.getInt(2));
                list.add(sach);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getDoanhThu(String ngayBatDau, String ngayKetThuc) {
        ngayBatDau = ngayBatDau.replace("/", "");
        ngayKetThuc = ngayKetThuc.replace("/", "");
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT SUM(tienthue) FROM PHIEUMUON WHERE substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2)" +
                " between ? and ?", new String[]{ngayBatDau, ngayKetThuc});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
