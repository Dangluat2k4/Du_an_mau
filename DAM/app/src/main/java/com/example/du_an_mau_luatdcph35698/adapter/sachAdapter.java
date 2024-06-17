package com.example.du_an_mau_luatdcph35698.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau_luatdcph35698.Dao.sachDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.model.sach;

import java.util.ArrayList;

public class sachAdapter extends RecyclerView.Adapter<sachAdapter.ViewHolder> {
    private  final Context context;
    private final ArrayList<sach> list;
    sachDao sDao;
    EditText edtUDMaSach,edtUDTenSach,edtUDGiaThue,edtUDMaLoaiSach;
    Button btnUDSach, btnCancelSach;

    public sachAdapter(Context context, ArrayList<sach> list) {
        this.context = context;
        this.list = list;
        sDao = new sachDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText("Mã sách :"+String.valueOf(list.get(position).getMaSach()));
        holder.txtTenSach.setText("Tên sách :"+list.get(position).getTenSach());
        holder.txtGiaThue.setText("Giá Thuê :"+String.valueOf(list.get(position).getGiaThue()));
        holder.txtMaLoai.setText("Mã Loại :" +String.valueOf(list.get(position).getMaLoai()));
        sach sach = list.get(position);
        holder.btnDeleteS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setTitle("chú ý");
                builder.setIcon(R.drawable.baseline_warning_24);
                builder.setMessage("bạn có chắc chăn muốn xóa không");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (sDao.delete(sach.getMaSach())){
                            list.clear();
                            list.addAll(sDao.selectAll());
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

        holder.btnUpdateS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogUDSach(sach);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaSach,txtTenSach,txtGiaThue,txtMaLoai;
        Button btnDeleteS,btnUpdateS;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThue);
            txtMaLoai = itemView.findViewById(R.id.txtMaloai);
            btnDeleteS = itemView.findViewById(R.id.btnDeleteS);
            btnUpdateS = itemView.findViewById(R.id.btnUPdateS);
        }
    }
    public void openDiaLogUDSach(sach sach){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // tao lay out , gan view vao lay out
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_sach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
        edtUDMaSach = view.findViewById(R.id.edtUDMaSach);
        edtUDTenSach = view.findViewById(R.id.edtUDTenSach);
        edtUDGiaThue = view.findViewById(R.id.edtUDgiaThue);
        edtUDMaLoaiSach = view.findViewById(R.id.edtUDMaLoaiSach);
        btnUDSach = view.findViewById(R.id.btnUDSach);
        btnCancelSach = view.findViewById(R.id.btnUDCancelSach);
        // gan du lieu
        edtUDMaSach.setText(String.valueOf(sach.getMaSach()));
        edtUDTenSach.setText(sach.getTenSach());
        edtUDGiaThue.setText(String.valueOf(sach.getGiaThue()));
        edtUDMaLoaiSach.setText(String.valueOf(sach.getMaLoai()));
        btnUDSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSach = edtUDTenSach.getText().toString();
                String giaThue = edtUDGiaThue.getText().toString();
                if ( TextUtils.isEmpty(giaThue)){
                    Toast.makeText(context, "vui lòng nhập giá thuê", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tenSach) ){
                    Toast.makeText(context, "vui lòng nhập tên sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                try{

                    int giaThue1 = Integer.parseInt(giaThue);
                    sach.setTenSach(tenSach);
                    sach.setGiaThue(giaThue1);
                    if (sDao.update(sach)) {
                        list.clear();
                        list.addAll(sDao.selectAll());
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "update thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "update thất bại", Toast.LENGTH_SHORT).show();
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(context, "giá thuê phải là số", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancelSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
