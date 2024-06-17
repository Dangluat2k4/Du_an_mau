package com.example.du_an_mau_luatdcph35698.model;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

public class phieuMuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int maSach;
    private String ngay;
    private int traSach;
    private int tienThue;
    private  String tenTV;
    private String tenTT;
    private String tenSach;

    public phieuMuon() {
    }
    //SELECT pm.mapm,pm.matv,tv.hoten,pm.matt,tt.hoten,pm.masach,sc.tensach,pm.ngay,pm.trasach,pm.tienthue FROM phieuMuon pm,thanhVien tv,thuThu tt,sach sc WHERE pm.matv = tv.matv AND pm.matt = tt.matt AND pm.masach = sc.masach;
    public phieuMuon(int maPM, int maTV, String tenTV, String maTT, String tenTT, int maSach, String tenSach, String ngay, int traSach, int tienThue) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
        this.tenTV = tenTV;
        this.tenTT = tenTT;
        this.tenSach = tenSach;
    }

    public phieuMuon(int maPM, String maTT, int maTV, int maSach, String ngay, int traSach, int tienThue) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }

    public phieuMuon(String maTT, int maTV, int maSach, String ngay, int traSach, int tienThue) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngay = ngay;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
