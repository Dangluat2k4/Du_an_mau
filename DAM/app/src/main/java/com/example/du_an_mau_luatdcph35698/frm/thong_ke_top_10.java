package com.example.du_an_mau_luatdcph35698.frm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_mau_luatdcph35698.Dao.thongKeDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.adapter.top10Adapter;
import com.example.du_an_mau_luatdcph35698.model.sach;

import java.util.ArrayList;


public class thong_ke_top_10 extends Fragment {
    RecyclerView rcvTop10;
    thongKeDao thongKeDao;
    top10Adapter adapter;
    ArrayList<sach> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_top_10, container, false);
        rcvTop10  =view.findViewById(R.id.rcvTop10);
        thongKeDao = new thongKeDao(getActivity());
        list = thongKeDao.getTop10();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvTop10.setLayoutManager(layoutManager);
        adapter = new top10Adapter(getActivity(),list);
        rcvTop10.setAdapter(adapter);
        return view;
    }
}