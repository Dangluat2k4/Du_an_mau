package com.example.du_an_mau_luatdcph35698.frm;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.du_an_mau_luatdcph35698.Dao.thongKeDao;
import com.example.du_an_mau_luatdcph35698.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class doanh_thu extends Fragment {
    TextView txtDoanhThu;
    EditText edtTuNgay,edtDenNgay;
    Button btnDoanhThu;
    thongKeDao thongKeDao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edtTuNgay = view.findViewById(R.id.edtTuNgay);
        edtDenNgay = view.findViewById(R.id.edtDenNgay);
        txtDoanhThu = view.findViewById(R.id.txtDoanhThu);
//        btnTuNgay = view.findViewById(R.id.btnTuNgay);
//        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        Calendar calendar = Calendar.getInstance();
        edtTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String ngay ="";
                        String thang ="";
                        if (dayOfMonth<10){
                            ngay = "0"+ dayOfMonth;
                        }else {
                            ngay = String.valueOf(dayOfMonth);
                        }
                        if ((month +1)<10){
                            thang = "0"+(month+1);
                        }else {
                            thang = String.valueOf(month+1);
                        }
                        edtTuNgay.setText(year +"/"+(thang)+"/"+ ngay);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        edtDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String ngay ="";
                        String thang ="";
                        if (dayOfMonth<10){
                            ngay = "0"+ dayOfMonth;
                        }else {
                            ngay = String.valueOf(dayOfMonth);
                        }
                        if ((month +1)<10){
                            thang = "0"+(month+1);
                        }else {
                            thang = String.valueOf(month+1);
                        }
                        edtDenNgay.setText(year +"/"+(thang)+"/"+ ngay);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongKeDao = new thongKeDao(getActivity());
                String ngayBatDau = edtTuNgay.getText().toString();
                String ngayKetThuc = edtDenNgay.getText().toString();
                int doanhThu = thongKeDao.getDoanhThu(ngayBatDau,ngayKetThuc);
                txtDoanhThu.setText(doanhThu+"VND");
            }
        });
        return view;
    }


}
//        btnTuNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                mYear = calendar.get(Calendar.YEAR);
//                mMonth = calendar.get(Calendar.MONTH);
//                mDay = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog dialog = new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
//                dialog.show();
//            }
//        });
//        btnDenNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                mYear = calendar.get(Calendar.YEAR);
//                mMonth = calendar.get(Calendar.MONTH);
//                mDay = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog dialog = new DatePickerDialog(getActivity(),0,mDateDenNgay,mYear,mMonth,mDay);
//                dialog.show();
//            }
//        });
//    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            mYear = year;
//            mMonth = monthOfYear;
//            mDay = dayOfMonth;
//            GregorianCalendar calendar = new GregorianCalendar(mYear,mMonth,mDay);
//            edtTuNgay.setText(dateFormat.format(calendar.getTime()));
//        }
//    };
//    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//            mYear = year;
//            mMonth = monthOfYear;
//            mDay = dayOfMonth;
//            GregorianCalendar calendar = new GregorianCalendar(mYear,mMonth,mDay);
//            edtDenNgay.setText(dateFormat.format(calendar.getTime()));
//        }
//    };