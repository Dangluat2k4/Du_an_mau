package com.example.du_an_mau_luatdcph35698.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau_luatdcph35698.Dao.loaiSachDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.model.loaiSach;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;

import java.util.ArrayList;

public class loaiSachAdapter extends RecyclerView.Adapter<loaiSachAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<loaiSach> list;
    loaiSachAdapter adapter;
    loaiSachDao lsDao;
    EditText edtUDMaLS, edtUDTenLS;
    Button btnUDLS,btnUDCancelLS;
    public loaiSachAdapter(Context context, ArrayList<loaiSach> list) {
        this.context = context;
        this.list = list;
        lsDao = new loaiSachDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai_sach,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
            holder.txtMaLoai.setText(String.valueOf("mã loại sách :"+list.get(position).getMaLoai()));
            holder.txtTenLoai.setText("tên loại sách :"+list.get(position).getTenLoai());
            loaiSach ls = list.get(position);
            holder.btnDeleteLS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder =new AlertDialog.Builder(context);
                    builder.setTitle("chú ý");
                    builder.setIcon(R.drawable.baseline_warning_24);
                    builder.setMessage("bạn có chắc chăn muốn xóa không");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (lsDao.delete(ls.getMaLoai())){
                                list.clear();
                                list.addAll(lsDao.selectAll());
                                notifyDataSetChanged();
                                Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            });
            holder.btnUpdateLS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDiaLogUD(ls);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaLoai, txtTenLoai;
        Button btnDeleteLS,btnUpdateLS;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            btnDeleteLS = itemView.findViewById(R.id.btnDeleteLS);
            btnUpdateLS = itemView.findViewById(R.id.btnUPdateLS);
        }
    }
    public void openDiaLogUD(loaiSach ls) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // tao lay out , gan view vao lay out
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_loai_sach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
        edtUDMaLS = view.findViewById(R.id.edtUDMaLS);
        edtUDTenLS = view.findViewById(R.id.edtUDTenLS);
        btnUDLS = view.findViewById(R.id.btnUDLS);
        btnUDCancelLS= view.findViewById(R.id.btnUDACancelLS);
        btnUDCancelLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // gan du lieu
        edtUDMaLS.setText(String.valueOf(ls.getMaLoai()));
        edtUDTenLS.setText(ls.getTenLoai());
        btnUDLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tv.setMaTV(Integer.parseInt(edtMaTV.getText().toString()));
//                ls.setMaLoai(Integer.parseInt(edtUDMaLS.getText().toString()));
                ls.setTenLoai((edtUDTenLS.getText().toString()));

                if (lsDao.update(ls)) {
                    list.clear();
                    list.addAll(lsDao.selectAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "update thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "update thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
