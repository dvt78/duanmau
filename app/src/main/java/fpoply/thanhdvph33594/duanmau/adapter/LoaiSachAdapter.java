package fpoply.thanhdvph33594.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoply.thanhdvph33594.duanmau.R;
import fpoply.thanhdvph33594.duanmau.dao.LoaiSachDAO;
import fpoply.thanhdvph33594.duanmau.model.LoaiSach;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<LoaiSach> list;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoai.setText("Tên loại sách là: "+list.get(position).getTenloai());
        holder.txtMaLoai.setText("Mã loại sách là: "+ String.valueOf(list.get(position).getId()));
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO sachDAO = new LoaiSachDAO(context);
                int check =sachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());
                switch (check){
                    case 1:
                        //load lại date
                        list.clear();
                        list = sachDAO.getDanhSachLoaiSach();
                        Toast.makeText(context,"Xóa loại sách thành công",Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context,"Không thể xóa loại sách này vì đã có sách thuộc thể loại này",Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context,"Xóa loại sách không thành công",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaLoai,txtTenLoai;
        ImageView ivDel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai=itemView.findViewById(R.id.txtMaLoaiSach);
            txtTenLoai=itemView.findViewById(R.id.txtTenLoaiSach);
            ivDel=itemView.findViewById(R.id.ivDel);
        }
    }
}
