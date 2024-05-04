package com.example.ProjectJava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlhelper extends SQLiteOpenHelper {


    //DataBase Information
    static  final String DB_NAME="POLY.DB";

    //Database version

    static  final int DB_version = 1 ;


    // table Name

    public  static  final String Table_User ="User" ;
    public  static  final String Table_rendv ="rdv" ;
    public  static  final String Table_fav ="fav" ;

    public  static  final String ID = "id";
    public  static  final String IDrdv = "idrdv";
    public  static  final String IDfav = "idrdv";
    public static  final  String Datefav  ="Datefav";
    public static  final  String Timefav  ="Timefav";
    public static  final  String Descriptionfav  ="Descriptionfav";

    //Table Columns
    public  static final  String Username ="Username";
    public static  final  String email ="Email";
    public static  final  String Password  ="Password";
    public static  final  String Date  ="Date";
    public static  final  String Time  ="Time";
    public static  final  String Description  ="Description";


    public sqlhelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table_rendv + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Date + " TEXT , " + Time + " TEXT, " + Description + " TEXT );");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table_rendv + "(" + IDrdv + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Date + " TEXT , " + Time + " TEXT , " + Description + " TEXT );");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table_rendv + "(" + IDfav + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Datefav + " TEXT , " + Timefav + " TEXT , " + Descriptionfav + " TEXT );");
    }

    public  void insert(String username , String email ,String password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue =new ContentValues();
        contentValue.put(sqlhelper.Username,username) ;
        contentValue.put(sqlhelper.email,email) ;
        contentValue.put(sqlhelper.Password,password) ;
        db.insert(sqlhelper.Table_User ,null,contentValue);
        db.close();

    }
    public  void addrendezvous(String Date , String Time ,String Description ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue =new ContentValues();
        contentValue.put(sqlhelper.Date,Date) ;
        contentValue.put(sqlhelper.Time,Time) ;
        contentValue.put(sqlhelper.Description,Description) ;
        db.insert(sqlhelper.Table_rendv ,null,contentValue);
        db.close();

    }
    public  void addrendezvousfav(String Datefav , String Timefav ,String Descriptionfav ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue =new ContentValues();
        contentValue.put(sqlhelper.Datefav,Datefav) ;
        contentValue.put(sqlhelper.Timefav,Timefav) ;
        contentValue.put(sqlhelper.Descriptionfav,Descriptionfav) ;
        db.insert(sqlhelper.Table_fav ,null,contentValue);
        db.close();

    }
    public boolean updateUserInfo(String id, String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Username, username);
        contentValues.put(email, email);
        contentValues.put(Password, password);


        int updateStatus = db.update(Table_User, contentValues, ID + " = ?", new String[] { id });
        db.close();

        return updateStatus > 0;
    }

    // Change this from a static method to an instance method
    public Cursor getAllURdv() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id AS _id, Date, Time, Description FROM " + Table_rendv, null);
    }

    public Cursor getAllfavURdv() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT id AS _id, Datefav, Timefav, Descriptionfav FROM " +Table_fav , null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_User);
        db.execSQL("DROP TABLE IF EXISTS " + Table_rendv);
        db.execSQL("DROP TABLE IF EXISTS " + Table_fav);
        onCreate(db);
    }

}
