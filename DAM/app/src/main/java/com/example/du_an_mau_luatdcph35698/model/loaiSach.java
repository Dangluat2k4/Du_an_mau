package com.example.du_an_mau_luatdcph35698.model;

public class loaiSach {
    private int maLoai;
    private String tenLoai;

    public loaiSach() {
    }

    public loaiSach(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
