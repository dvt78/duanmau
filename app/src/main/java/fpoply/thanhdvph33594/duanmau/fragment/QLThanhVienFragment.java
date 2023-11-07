package fpoply.thanhdvph33594.duanmau.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoply.thanhdvph33594.duanmau.R;
import fpoply.thanhdvph33594.duanmau.adapter.ThanhVienAdapter;
import fpoply.thanhdvph33594.duanmau.dao.ThanhVienDAO;
import fpoply.thanhdvph33594.duanmau.model.ThanhVien;

public class QLThanhVienFragment extends Fragment {
    ThanhVienDAO thanhVienDAO;
    ArrayList<ThanhVien> list;
    RecyclerView recyclerThanhVien;
    @Nullable
    @Override
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_qlthanhvien,container,false);
        recyclerThanhVien = view.findViewById(R.id.recyclerThanhVien);
         FloatingActionButton floatAdd= view.findViewById(R.id.floatThem);
         thanhVienDAO = new ThanhVienDAO(getContext());
         loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;
    }
    private void loadData(){
        list = thanhVienDAO.getDsThanhVien();
        LinearLayoutManager  linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerThanhVien.setLayoutManager(linearLayoutManager);
        ThanhVienAdapter thanhVienAdapter= new ThanhVienAdapter(getContext(),list,thanhVienDAO);
        recyclerThanhVien.setAdapter(thanhVienAdapter);
    }
    private void showDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        LayoutInflater inflater= getLayoutInflater();
        View view= inflater.inflate(R.layout.dialog_them_thanhvien,null);
        builder.setView(view);
        EditText edtHoTen =view.findViewById(R.id.edtTenTV);
        EditText edtNamSinh =view.findViewById(R.id.edtNamSinh);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten =edtHoTen.getText().toString();
                String namsinh =edtNamSinh.getText().toString();
                boolean check =thanhVienDAO.themThanhVien(hoten,namsinh);
                if(check==true){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else{
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
