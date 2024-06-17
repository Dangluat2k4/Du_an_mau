package com.example.du_an_mau_luatdcph35698.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau_luatdcph35698.Dao.thongKeDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.model.sach;

import java.util.ArrayList;

public class top10Adapter extends RecyclerView.Adapter<top10Adapter.viewHolder> {
    private  final Context context;
    private final ArrayList<sach> list;
    thongKeDao thongKeDao;

    public top10Adapter(Context context, ArrayList<sach> list) {
        this.context = context;
            this.list = list;
        thongKeDao = new thongKeDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.txtMaSachT10.setText("Mã sách :"+String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSachT10.setText("Tên sách :"+list.get(position).getTenSach());
        holder.txtSoLuongMuon.setText("Số lượng :"+String.valueOf(list.get(position).getSoLuongDaMuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaSachT10,txtTenSachT10,txtSoLuongMuon;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSachT10 = itemView.findViewById(R.id.txtMaSachT10);
            txtTenSachT10 = itemView.findViewById(R.id.txtTenSachT10);
            txtSoLuongMuon = itemView.findViewById(R.id.txtSoLuongMuon);
        }
    }
}
