package fpoply.thanhdvph33594.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpoply.thanhdvph33594.duanmau.dao.ThuThuDAO;

public class DangNhap extends AppCompatActivity {
EditText edtUser,edtPass;
Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtUser= findViewById(R.id.edtUsername);
        edtPass= findViewById(R.id.edtPassword);
        btnDangNhap=findViewById(R.id.btnLogin);

        ThuThuDAO thuThuDAO=new ThuThuDAO(this);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= edtUser.getText().toString();
                String pass=edtPass.getText().toString();
                if(thuThuDAO.checkDangNhap(user,pass)){
                    //.lưu database ở đây
                    SharedPreferences sharedPreferences= getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharedPreferences.edit();
                    editor.putString("matt",user);//lưu giá trị
                    editor.commit();//lưu dữ liệu
                    startActivity(new Intent(DangNhap.this, MainActivity.class));
                }else{
                    Toast.makeText(DangNhap.this,"Đăng nhập thất bại !! Vui lòng kiểm tra lại",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}