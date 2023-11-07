package fpoply.thanhdvph33594.duanmau.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import fpoply.thanhdvph33594.duanmau.R;
import fpoply.thanhdvph33594.duanmau.adapter.SachAdapter;
import fpoply.thanhdvph33594.duanmau.dao.LoaiSachDAO;
import fpoply.thanhdvph33594.duanmau.dao.SachDAO;
import fpoply.thanhdvph33594.duanmau.model.LoaiSach;
import fpoply.thanhdvph33594.duanmau.model.Sach;

public class QLSachFragment extends Fragment {
    SachDAO sachDAO;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sach_fragment,container,false);
        recyclerView=view.findViewById(R.id.recyclerSach);
        FloatingActionButton floatAdd=view.findViewById(R.id.floatAddSach);

        sachDAO = new SachDAO(getContext());
        loadDaTa();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLog();
            }
        });
        return view;
    }
    private void loadDaTa(){
        ArrayList<Sach> list=sachDAO.getDSDauSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        SachAdapter sachAdapter=new SachAdapter(getContext(),list,getDSLoaiSach(),sachDAO);
        recyclerView.setAdapter(sachAdapter);
    }
    private void showDiaLog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_themsach,null);
        builder.setView(view);
        EditText edtTenSach=view.findViewById(R.id.edtTenSach);
        EditText edtGiaThue=view.findViewById(R.id.edtTien);
        Spinner spnLoaiSach=view.findViewById(R.id.spnLoaiSach);
        SimpleAdapter simpleAdapter=new SimpleAdapter(getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1});
        spnLoaiSach.setAdapter(simpleAdapter);
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tensach=edtTenSach.getText().toString();
                int tien=Integer.parseInt(edtGiaThue.getText().toString());
                HashMap<String,Object> hs=(HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai=(int)hs.get("maloai");
                boolean check=sachDAO.themSachMoi(tensach,tien,maloai);
                    if(check){
                        Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                        loadDaTa();

                    }else{
                        Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();


    }
    private ArrayList<HashMap<String,Object>> getDSLoaiSach(){
        LoaiSachDAO loaiSachDAO=new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list=loaiSachDAO.getDanhSachLoaiSach();
        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (LoaiSach loai: list){
            HashMap<String,Object> hs=new HashMap<>();
            hs.put("maloai",loai.getId());
            hs.put("tenloai",loai.getTenloai());
            listHM.add(hs);
        }

        return listHM;
    }

}
