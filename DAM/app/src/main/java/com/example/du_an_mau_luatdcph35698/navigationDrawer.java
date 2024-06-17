package com.example.du_an_mau_luatdcph35698;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.du_an_mau_luatdcph35698.Dao.thuThuDao;
import com.example.du_an_mau_luatdcph35698.frm.doanh_thu;
import com.example.du_an_mau_luatdcph35698.frm.frm_quan_ly_loai_sach;
import com.example.du_an_mau_luatdcph35698.frm.frm_quan_ly_phieu_muon;
import com.example.du_an_mau_luatdcph35698.frm.quan_ly_sach;
import com.example.du_an_mau_luatdcph35698.frm.quan_ly_thanh_vien;
import com.example.du_an_mau_luatdcph35698.frm.doi_password;
import com.example.du_an_mau_luatdcph35698.frm.thong_ke_top_10;
import com.example.du_an_mau_luatdcph35698.model.thuThu;
import com.google.android.material.navigation.NavigationView;

public class navigationDrawer extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout layout;
    NavigationView nav;
    TextView txtWelcome;
    thuThuDao thuDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = findViewById(R.id.toorbar);
        layout = findViewById(R.id.drawerLayout);

        nav = findViewById(R.id.nav);
        View headerView  =nav.getHeaderView(0);
        txtWelcome = headerView.findViewById(R.id.txtWelcome);
        Intent intent= getIntent();
        String user = intent.getStringExtra("username");
        thuDao = new thuThuDao(this);
        thuThu thuThu = thuDao.getID(user);
        String username = thuThu.getHoTen();
        txtWelcome.setText("Welcome :"+user+" !");


        setSupportActionBar(toolbar);
        toolbar.setTitle("thư viện phương nam");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.open_app, R.string.close_app);
        layout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            frm_quan_ly_phieu_muon defaultFragment = new frm_quan_ly_phieu_muon();
            replaceFrg(defaultFragment);
        }
        nav.setItemIconTintList(null);
        // xu ly su kien khi click chon item tren nav
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.quanlyphieumuon) {
                    setTitle("Quản lý phiếu mượn");
                    frm_quan_ly_phieu_muon phieumuon = new frm_quan_ly_phieu_muon();
                    replaceFrg(phieumuon);
                }
                if (item.getItemId() == R.id.quanlyloaisach) {
                    setTitle("Quản lý loại sách");
                    frm_quan_ly_loai_sach loaiSach = new frm_quan_ly_loai_sach();
                    replaceFrg(loaiSach);
                }
                if (item.getItemId() == R.id.quanlysach) {
                    setTitle("Quản lý sách");
                    quan_ly_sach sach = new quan_ly_sach();
                    replaceFrg(sach);
                }
                if (item.getItemId() == R.id.quanlythanhvien) {
                    setTitle("Quản lý thành viên");
                    quan_ly_thanh_vien thanhvien = new quan_ly_thanh_vien();
                    replaceFrg(thanhvien);
                }
                if (item.getItemId() == R.id.top10) {
                    setTitle("Top 10");
                    thong_ke_top_10 thongKeTop10 = new thong_ke_top_10();
                    replaceFrg(thongKeTop10);
                }
                if (item.getItemId() == R.id.doanhthu) {
                    setTitle("Doanh thu");
                    doanh_thu doanhThu = new doanh_thu();
                    replaceFrg(doanhThu);
                }
                if (item.getItemId() == R.id.doimatkhau) {
                    setTitle("Đổi mật khẩu");
                    doi_password doiPassword = new doi_password();
                    replaceFrg(doiPassword);
                }
                if (item.getItemId() == R.id.dangxuat) {
                    Intent intent = new Intent(navigationDrawer.this, dangNhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                if(item.getItemId() ==R.id.dangki){
                    Intent intent = new Intent(navigationDrawer.this, dangKi.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                return true;
            }
        });
        // hien thi 1 so chuc nang cho Admim
        SharedPreferences sharedPreference= getSharedPreferences("thongtin",MODE_PRIVATE);
        String loaiTK = sharedPreference.getString("loaiTaiKhoan","");
        if (!loaiTK.equals("Admin")){
            Menu menu = nav.getMenu();
            menu.findItem(R.id.taotk).setVisible(false);
        }
    }

    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav, frg).commit();
    }
}