package com.example.du_an_mau_luatdcph35698.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {
    public static final String db_name = "QLTV";

    public dbHelper(@Nullable Context context) {
        super(context, db_name, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThanhVien = "create table thanhVien(" +
                "maTV integer primary key autoincrement," +
                " hoTen text not null," +
                "namSinh text not null)";
        db.execSQL(createTableThanhVien);

        String createTableThuThu = "create table thuThu(" +
                "maTT text  primary key," +
                " hoTen text not null," +
                "matKhau text not null," +
                "loaiTaiKhoan text)";
        db.execSQL(createTableThuThu);

        String createTableLoaiSach = "create table loaiSach(" +
                "maLoai integer primary key autoincrement," +
                " tenLoai text not null)";
        db.execSQL(createTableLoaiSach);

        String createTableSach = "create table sach(" +
                "maSach integer primary key autoincrement," +
                " tenSach text not null," +
                "giaThue integer not null," +
                "maLoai integer references loaiSach(maLoai))";
        db.execSQL(createTableSach);

        String createTablePhieuMuon = "create table phieuMuon(" +
                "maPM integer primary key autoincrement," +
                "maTT text references thuThu(maTT)," +
                "maTV integer references thanhVien(maTV)," +
                "maSach integer references sach(maSach)," +
                "ngay date not null," +
                "traSach integer not null," +
                "tienThue integer not null)";

        db.execSQL(createTablePhieuMuon);
        db.execSQL("INSERT INTO loaiSach VALUES (1, 'Công nghệ thông tin')," +
                "(2,'Maketing')," +
                "(3, 'Đồ họa')");
        db.execSQL("INSERT INTO sach VALUES (1, 'java', 2500, 1)," +
                " (2, 'Vẽ chân dung', 1000, 1)," +
                " (3, 'Chăm sóc khách hàng', 2000, 3)");
        db.execSQL("insert into loaisach values (1,'d')");
        db.execSQL("INSERT INTO thuThu VALUES ('thuthu01','Đặng Chí Luật','abc','Admin')," +
                "('thuthu02','Trần Văn Hùng','123','thủ thư')");
        db.execSQL("INSERT INTO thanhVien VALUES (1,'Chu Minh Hoàng','2000')," +
                "(2,'Đặng Phương Nam','2000')");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO phieuMuon VALUES (1,'thuthu01',1, 1, '19/03/2022', 1, 2500)," +
                "(2,'thuthu01',2, 3, '19/03/2022', 0, 2000)," +
                "(3,'thuthu02',1, 1, '19/03/2022', 1, 2000)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists thanhVien");
            db.execSQL("drop table if exists thuThu");
            db.execSQL("drop table if exists loaiSach");
            db.execSQL("drop table if exists sach");
            db.execSQL("drop table if exists phieuMuon");
            onCreate(db);
        }

    }
}
