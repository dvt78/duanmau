package fpoply.thanhdvph33594.duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoply.thanhdvph33594.duanmau.database.DbHelper;

public class ThuThuDAO {
    DbHelper dbHelper;

    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    //viết hàm đăng nhập
    public boolean checkDangNhap(String matt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean capNhatMatKhau(String username, String oldpass, String newPass) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{username, oldpass});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check = sqLiteDatabase.update("THUTHU", contentValues, "matt = ?", new String[]{username});
            if(check == -1)
                return false;
                return true;
            }
            return false;
        }
    }


