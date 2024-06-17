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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau_luatdcph35698.Dao.thanhVienDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;

import java.util.ArrayList;

public class thanhvienAdapter extends RecyclerView.Adapter<thanhvienAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<thanhVien> list;
    thanhVienDao tvDao;
    EditText edtMaTV, edtTenTV, edtNamSinh;
    Button btnSave, btnCancel;

    public thanhvienAdapter(Context context, ArrayList<thanhVien> list) {
        this.context = context;
        this.list = list;
        tvDao = new thanhVienDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_thanh_vien, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenTV.setText("Họ tên :"+list.get(position).getHoTen());
        holder.txtMaTV.setText("Mã thành viên :"+String.valueOf(list.get(position).getMaTV()));
        holder.txtNamSinh.setText("Năm sinh :"+ list.get(position).getNamSinh());
        thanhVien tv = list.get(position);
        holder.btnDeleteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setTitle("chú ý");
                builder.setIcon(R.drawable.baseline_warning_24);
                builder.setMessage("bạn có chắc chăn muốn xóa không");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (tvDao.delete(tv.getMaTV())){
                            list.clear();
                            list.addAll(tvDao.selectAll());
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
        holder.btnUpdateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLogUD(tv);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenTV, txtMaTV, txtNamSinh;
        Button btnUpdateTV,btnDeleteTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtMaTV = itemView.findViewById(R.id.txtMaTV);
            txtNamSinh = itemView.findViewById(R.id.txtNamSinh);
            btnUpdateTV = itemView.findViewById(R.id.btnUPdateTV);
            btnDeleteTV = itemView.findViewById(R.id.btnDeleteTV);
        }
    }

    public void openDiaLogUD(thanhVien tv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // tao lay out , gan view vao lay out
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_thanh_vien, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        // anh xa
        edtMaTV = view.findViewById(R.id.edtMaTV);
        edtTenTV = view.findViewById(R.id.edtTenTV);
        edtNamSinh = view.findViewById(R.id.edtNamSinh);
        btnSave = view.findViewById(R.id.btnSaveTV);
        btnCancel = view.findViewById(R.id.btnCancelTV);
        // gan du lieu
        edtMaTV.setText(String.valueOf(tv.getMaTV()));
        edtTenTV.setText(tv.getHoTen());
        edtNamSinh.setText(tv.getNamSinh());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = edtTenTV.getText().toString();
                String namSinh = edtNamSinh.getText().toString();

                // Kiểm tra xem các trường có rỗng hay không
                if (tenTV.isEmpty() || namSinh.isEmpty()) {
                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return; // Kết thúc phương thức nếu có trường nào đó rỗng
                }

                // Nếu không có trường nào rỗng, tiến hành cập nhật
                tv.setHoTen(tenTV);
                tv.setNamSinh(namSinh);

                if (tvDao.update(tv)) {
                    list.clear();
                    list.addAll(tvDao.selectAll());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
