package com.example.du_an_mau_luatdcph35698.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau_luatdcph35698.Dao.phieuMuonDao;
import com.example.du_an_mau_luatdcph35698.Dao.sachDao;
import com.example.du_an_mau_luatdcph35698.Dao.thanhVienDao;
import com.example.du_an_mau_luatdcph35698.R;
import com.example.du_an_mau_luatdcph35698.model.phieuMuon;
import com.example.du_an_mau_luatdcph35698.model.sach;
import com.example.du_an_mau_luatdcph35698.model.thanhVien;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class phieuMuonAdapter extends RecyclerView.Adapter<phieuMuonAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<phieuMuon> list;
    phieuMuonDao pmDao;
    EditText edtUDMaSach, edtNgayPM, edtUDGiaThue;
    Spinner spnUDMaThanhVien, spnUDTenSach;
    CheckBox chkCheckTraSach;
    Button btnUDPM, btnUDCancelPM;

    public phieuMuonAdapter(Context context, ArrayList<phieuMuon> list) {
        this.context = context;
        this.list = list;
        pmDao = new phieuMuonDao(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_phieu_muon, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaPhieuMuon.setText("Mã phiếu mượn :" + (list.get(position).getMaPM()));
        holder.txtMaTV.setText("Mã thành viên :" + (list.get(position).getMaTV()));
        holder.txtTT.setText("Mã thủ thư :" + list.get(position).getMaTT());
        holder.txtMaSach.setText("Mã sách :" + (list.get(position).getMaSach()));
        holder.txtNgay.setText("Ngày :" + list.get(position).getNgay());
//        holder.txtTraSach.setText(String.valueOf("trạng thái :"+list.get(position).getTraSach()));
//        String trangThai = "";
        if (list.get(position).getTraSach() == 1) {
            holder.txtTraSach.setText("Đã trả sách");
            holder.txtTraSach.setTextColor(context.getColor(com.google.android.material.R.color.design_default_color_error));
//            trangThai = "";
            holder.btnTraSach.setVisibility(View.GONE);
        } else {
            holder.txtTraSach.setText("chưa trả sách");
            holder.txtTraSach.setTextColor(context.getColor(com.google.android.material.R.color.design_dark_default_color_secondary));
//            trangThai = "chưa trả sách";
            holder.btnTraSach.setVisibility(View.VISIBLE);
        }
//        holder.txtTraSach.setText("Trạng thái :" + trangThai);
        holder.txtTienThue.setText(String.valueOf("Tiền thuê:" + list.get(position).getTienThue()));
        holder.txtTienThue.setTextColor(context.getColor(com.google.android.material.R.color.design_default_color_error));
        holder.txtTenTV.setText("Tên thành viên :" + list.get(position).getTenTV());
        holder.txtTenTT.setText("Tên thủ thư :" + list.get(position).getTenTT());
        holder.txtTenSach.setText("Tên thành sách :" + list.get(position).getTenSach());

        phieuMuon pm = list.get(position);
        holder.btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phieuMuonDao dao = new phieuMuonDao(context);
                boolean kiemTra = dao.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMaPM());
                if (kiemTra == true) {
                    // load lai du lieu
                    list.clear();
                    list = dao.selectAll();
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "thay đổi trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.btnDeletePhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("chú ý");
                builder.setIcon(R.drawable.baseline_warning_24);
                builder.setMessage("bạn có chắc chăn muốn xóa không");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (pmDao.delete(pm.getMaPM())) {
                            list.clear();
                            list.addAll(pmDao.selectAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaPhieuMuon, txtTT, txtMaTV, txtMaSach, txtNgay, txtTraSach, txtTienThue,txtTenTV,txtTenTT,txtTenSach;
        Button btnDeletePhieuMuon, btnUDPhieuMuon, btnTraSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPhieuMuon = itemView.findViewById(R.id.txtMaPhieuMuon);
            txtTT = itemView.findViewById(R.id.txtMaTTPM);
            txtMaTV = itemView.findViewById(R.id.txtMaTVPM);
            txtMaSach = itemView.findViewById(R.id.txtMaSachPM);
            txtNgay = itemView.findViewById(R.id.txtNgayPM);
            txtTraSach = itemView.findViewById(R.id.txtTraSachPM);
            txtTienThue = itemView.findViewById(R.id.txtTienThuePM);
            txtTenTV = itemView.findViewById(R.id.txtTenTV);
            txtTenTT = itemView.findViewById(R.id.txtTenTT);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            btnDeletePhieuMuon = itemView.findViewById(R.id.btnDeletePhieuMuon);
//            btnUDPhieuMuon = itemView.findViewById(R.id.btnUPdatePhieuMuon);
            btnTraSach = itemView.findViewById(R.id.btnTraSach);
        }

    }

    public void OpenDiaLogAdd(phieuMuon pm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_update_phieu_muon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        //anh xa
        edtNgayPM = view.findViewById(R.id.edtNgayPM);
        edtUDGiaThue = view.findViewById(R.id.edtUDGiaThuePM);
        edtUDMaSach = view.findViewById(R.id.edtUDMaSach);
        spnUDMaThanhVien  = view.findViewById(R.id.spnUDMaThanhVien);
        spnUDTenSach = view.findViewById(R.id.spnUDTenSach);
        btnUDPM = view.findViewById(R.id.btnUDPM);
        btnUDCancelPM = view.findViewById(R.id.btnUDCancelPM);
        // gan du lieu
        edtNgayPM.setText(pm.getNgay());
        edtUDGiaThue.setText(String.valueOf(pm.getTienThue()));
        edtUDMaSach.setText(String.valueOf(pm.getMaSach()));
        getDataTV(spnUDMaThanhVien);
        getDataMaSach(spnUDTenSach);
//        btnUDPM.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pm.setNgay(edtNgayPM.getText().toString());
//                pm.setTienThue(Integer.parseInt(edtUDGiaThue.getText().toString()));
//                pm.setMaSach(Integer.parseInt(edtUDMaSach.getText().toString()));
//
//                boolean check =pmDao.update(pm);
//                if (check){
//                    list.clear();
//                    list.addAll(pmDao.selectAll());
//                    notifyDataSetChanged();
//                    dialog.dismiss();
//                    Toast.makeText(context, "cập nhật dữ liệu thành công", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context, "cập nhật dữ liệu thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
    private void getDataTV(Spinner spnAddTV){
        thanhVienDao thanhVienDao = new thanhVienDao(context);
        ArrayList<thanhVien> list1 = thanhVienDao.selectAll();
        ArrayList<HashMap<String,Object>>listHM = new ArrayList<>();
        // duyet thong tin
        for (thanhVien tv: list1){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maTV",tv.getMaTV());
            hs.put("hoTen",tv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(context,listHM, android.R.layout.simple_list_item_1,new String[]{"hoTen"},new int[]{android.R.id.text1});
        spnAddTV.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();

    }
    private  void getDataMaSach(Spinner spnAMS){
        sachDao sachDao = new sachDao(context);
        ArrayList<sach> list1 = sachDao.selectAll();
        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        // duyet thong tin
        for (sach sach: list1){
            HashMap<String,Object> hs = new HashMap<>();
            hs.put("maSach",sach.getMaSach());
            hs.put("tenSach",sach.getTenSach());
            hs.put("giaThue",sach.getGiaThue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(context,listHM, android.R.layout.simple_list_item_1,new String[]{"tenSach"}, new int[]{android.R.id.text1});
        spnAMS.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }
}

//    HashMap<String, Object> hsTV = (HashMap<String, Object>) spnUDMaThanhVien.getSelectedItem();
//    int maTV = (int) hsTV.get("maTV");
//    HashMap<String, Object> hsMS = (HashMap<String, Object>) spnUDTenSach.getSelectedItem();
//    int maS = (int) hsMS.get("maSach");