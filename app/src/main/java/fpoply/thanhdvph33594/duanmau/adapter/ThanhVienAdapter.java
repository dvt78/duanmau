package fpoply.thanhdvph33594.duanmau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoply.thanhdvph33594.duanmau.R;
import fpoply.thanhdvph33594.duanmau.dao.ThanhVienDAO;
import fpoply.thanhdvph33594.duanmau.model.ThanhVien;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.VỉewHolder> {
    private Context context;
    private ArrayList<ThanhVien> list;

    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }

    @NonNull
    @Override
    public VỉewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view= inflater.inflate(R.layout.item_thanhvien,parent,false);
        return new VỉewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VỉewHolder holder, int position) {
        holder.txtMaTV.setText("Mã TV: "+ list.get(position).getMatv());
        holder.txtHoTen.setText("Họ tên: "+ list.get(position).getHoten());
        holder.txtNamSinh.setText("Năm sinh: "+ list.get(position).getNamsinh());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogUpdate(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check =thanhVienDAO.xoaThanhVien(list.get(holder.getAdapterPosition()).getMatv());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadDaTa();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Thành viên tồn tại ở phiếu mượn, không được phép xóa", Toast.LENGTH_SHORT).show();
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

    public class VỉewHolder extends RecyclerView.ViewHolder{
        TextView txtMaTV,txtHoTen,txtNamSinh;
        ImageView ivEdit,ivDelete;
        public VỉewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaTV=itemView.findViewById(R.id.txtMaTv);
            txtHoTen=itemView.findViewById(R.id.txtHoTen);
            txtNamSinh=itemView.findViewById(R.id.txtNamSinh);
            ivEdit=itemView.findViewById(R.id.ivEdit);
            ivDelete=itemView.findViewById(R.id.ivDelete);
        }
    }
    private void showDiaLogUpdate(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.dialog_update_thanhvien,null);
        builder.setView(view);
        TextView txtMaTVUD =view.findViewById(R.id.txtMaTVUD);
        EditText edtHoTen= view.findViewById(R.id.edtTenTV);
        EditText edtNamSinh= view.findViewById(R.id.edtNamSinh);

        txtMaTVUD.setText("Mã Tv: "+thanhVien.getMatv());
        edtHoTen.setText(thanhVien.getHoten());
        edtNamSinh.setText(thanhVien.getNamsinh());
        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten=edtHoTen.getText().toString();
                String namsinh=edtNamSinh.getText().toString();
                int id = thanhVien.getMatv();
                boolean check =thanhVienDAO.capnhatThongTin(id,hoten,namsinh);
                if(check){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    loadDaTa();
                }else{
                    Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }
    private void loadDaTa(){
        list.clear();
        list =thanhVienDAO.getDsThanhVien();
        notifyDataSetChanged();
    }
}
