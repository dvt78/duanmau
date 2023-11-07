package fpoply.thanhdvph33594;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import fpoply.thanhdvph33594.duanmau.DangNhap;
import fpoply.thanhdvph33594.duanmau.R;

public class ManHinhChaoActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        imageView=findViewById(R.id.ivLogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ManHinhChaoActivity.this, DangNhap.class));

            }
        },3000);
    }
}