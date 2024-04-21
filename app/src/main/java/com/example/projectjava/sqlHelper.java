package com.example.projectjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.logging.Level;

public class sqlHelper extends SQLiteOpenHelper {


    //DataBase Information
    static  final String DB_NAME="POLYTECHNIQUE.DB";

    //Database version

    static  final int DB_version = 1 ;


    // table Name

    public  static  final String Tbale_User ="User" ;

    public  static  final String ID = "id";
    //Table Columns
    public  static final  String Username ="Username";
    public static  final  String email ="Email";
    public static  final  String Password  ="Password";

    public sqlHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tbale_User + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Username + " TEXT NOT NULL, " + email + " TEXT NOT NULL, " + Password + " TEXT NOT NULL);");

    }
    public  void insert(String username , String email ,String password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue =new ContentValues();
        contentValue.put(sqlHelper.Username,username) ;
        contentValue.put(sqlHelper.email,email) ;
        contentValue.put(sqlHelper.Password,password) ;
        db.insert(sqlHelper.Tbale_User ,null,contentValue);
        db.close();

    }
    public boolean updateUserInfo(String id, String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Username, username);
        contentValues.put(email, email);
        contentValues.put(Password, password);


        int updateStatus = db.update(Tbale_User, contentValues, ID + " = ?", new String[] { id });
        db.close();

        return updateStatus > 0;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
