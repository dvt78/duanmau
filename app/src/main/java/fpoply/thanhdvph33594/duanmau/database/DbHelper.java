package fpoply.thanhdvph33594.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context,"DUANMAU",null,1 );
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, hoten text, matkhau text)";
        sqLiteDatabase.execSQL(dbThuThu);

        String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement, hoten text, namsinh text)";
        sqLiteDatabase.execSQL(dbThanhVien);

        String dbLoaiSach= "CREATE TABLE LOAISACH(maloai integer primary key autoincrement, tenloai text)";
        sqLiteDatabase.execSQL(dbLoaiSach);

        String dbSach = "CREATE TABLE SACH(masach integer primary key autoincrement, tensach text, giathue integer," +
                "maloai integer references LOAISACH(maloai))";
        sqLiteDatabase.execSQL(dbSach);

        String dbPhieuMuon= "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, matt text references THUTHU(matt)," +
                "matv integer references THANHVIEN(matv), masach integer references SACH(masach), " +
                "ngay date, trasach integer,tienthue integer)";
        sqLiteDatabase.execSQL(dbPhieuMuon);
        //data mẫu
        sqLiteDatabase.execSQL("INSERT INTO LOAISACH VALUES (1, 'Thiếu nhi'),(2,'Giáo khoa'),(3,'Tình cảm')");
        sqLiteDatabase.execSQL("INSERT INTO SACH VALUES (1, 'Tý Quậy', 2500, 1), (2, 'Lập trình java', 1000, 2), (3, 'Hạnh phúc', 2000, 3)");
        sqLiteDatabase.execSQL("INSERT INTO THUTHU  VALUES ('thuthu01','DO VAN THANH','thanh123'),('thuthu02','NGUYEN THU HA','ha123'),('thuthu03','DO THU GIANG','giang123')");
        sqLiteDatabase.execSQL("INSERT INTO THANHVIEN VALUES (1,'Đỗ Văn Thành','2004'),(2,'Nguyễn Thu Hà','2004'),(3,'Đỗ Thu Giang','2008')");
        //trả sách: 1: đã trả - 0: chưa trả
        sqLiteDatabase.execSQL("INSERT INTO PHIEUMUON VALUES (1,'thuthu01',1, 1, '04/10/2023', 1, 2500),(2,'thuthu02',2, 3, '03/10/2023', 0, 2000),(3,'thuthu03',3, 2, '04/10/2023', 1, 2000)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THUTHU");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAISACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SACH");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(sqLiteDatabase);
        }

    }
}
