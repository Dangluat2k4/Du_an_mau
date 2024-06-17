package com.example.du_an_mau_luatdcph35698.model;

public class sach {
    private int maSach;
    private String tenSach;
    private int GiaThue;
    private int maLoai;
    private int soLuongDaMuon;

    public sach() {
    }

    public int getMaSach() {
        return maSach;
    }

    public sach(int maSach, String tenSach, int giaThue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        GiaThue = giaThue;
        this.maLoai = maLoai;
    }

    public sach(int maSach, String tenSach, int soLuongDaMuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuongDaMuon = soLuongDaMuon;
    }

    public sach(String tenSach, int giaThue, int maLoai) {
        this.tenSach = tenSach;
        GiaThue = giaThue;
        this.maLoai = maLoai;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return GiaThue;
    }

    public void setGiaThue(int giaThue) {
        GiaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoLuongDaMuon() {
        return soLuongDaMuon;
    }

    public void setSoLuongDaMuon(int soLuongDaMuon) {
        this.soLuongDaMuon = soLuongDaMuon;
    }
}
