package com.example.du_an_mau_luatdcph35698.Dao;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau_luatdcph35698.database.dbHelper;
import com.example.du_an_mau_luatdcph35698.model.thuThu;

import java.util.ArrayList;
import java.util.List;

public class thuThuDao {
    private final dbHelper dbHelper;
    SharedPreferences sharedPreferences;
    public thuThuDao(Context context) {
        dbHelper = new dbHelper(context);
        sharedPreferences = context.getSharedPreferences("thongtin",MODE_PRIVATE);
    }
        public boolean checkLogin(String matt,String matkhau){
        SQLiteDatabase db =  dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from thuThu where maTT=? and matKhau=?", new String[]{matt,matkhau});
        if(cursor.getCount()!=0){
            //luu
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            // luu gia tri
            // lay gia tri cua ma thu thu
            editor.putString("maTT",cursor.getString(0));
            // luu loai tai khoan
            editor.putString("loaiTaiKhoan", cursor.getString(3));
            editor.commit();
            return true;
        }else {
            return false;
        }
    }
    public boolean capNhatMatKhau(String username, String oldPass, String newPass){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM thuThu WHERE maTT=? and matKhau=?",new String[]{username,oldPass});
        if (cursor.getCount()>0){
            ContentValues values = new ContentValues();
            values.put("matKhau",newPass);
            long row =  database.update("thuThu",values,"maTT=?",new String[]{username});
            if (row==-1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
    public boolean insert(thuThu tt) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maTT", tt.getMaTT());
        values.put("hoTen", tt.getHoTen());
        values.put("matKhau", tt.getMatKhau());
        long row = db.insert("thuThu", null, values);
        return (row > 0);
    }
    public ArrayList<thuThu> selectAll(String sql,String id) {
        ArrayList<thuThu> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select * from thuThu", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                // tao thanh vien
                thuThu tt = new thuThu();
                // gan thong tin
                tt.setMaTT(cursor.getString(0));
                tt.setHoTen(cursor.getString(1));
                tt.setMatKhau(cursor.getString(2));
                list.add(tt);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public thuThu getID(String id) {
        String sql = "SELECT * FROM thuThu WHERE maTT=?";
        ArrayList<thuThu> list = selectAll(sql,id);
        return list.get(0);
    }

}
