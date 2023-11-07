package fpoply.thanhdvph33594.duanmau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoply.thanhdvph33594.duanmau.R;
import fpoply.thanhdvph33594.duanmau.adapter.LoaiSachAdapter;
import fpoply.thanhdvph33594.duanmau.dao.LoaiSachDAO;
import fpoply.thanhdvph33594.duanmau.model.LoaiSach;

public class QLLoaiSachFragment extends Fragment {
    RecyclerView recyclerLoaiSach;
    LoaiSachDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaisach,container,false);
        recyclerLoaiSach= view.findViewById(R.id.recyclerLoaiSach);
        EditText edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThemLoaiSach);
         dao = new LoaiSachDAO(getContext());
         loadData();


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai= edtLoaiSach.getText().toString();

                if(dao.themLoaiSach(tenLoai)){
                    Toast.makeText(getContext(),"Thêm loại sách thành công",Toast.LENGTH_SHORT).show();
                    loadData();
                    edtLoaiSach.setText("");
                }else{
                    Toast.makeText(getContext(),"Thêm loại sách thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);
        ArrayList<LoaiSach> list = dao.getDanhSachLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(),list);
        recyclerLoaiSach.setAdapter(adapter);
    }
}
