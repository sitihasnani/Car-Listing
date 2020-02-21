package com.example.carlisting.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final  String DATABASE_NAME ="carlisting.db";
    public static  String DATABASE_PATH ="";
    public static  final  String TABLE_NAME ="car_table";
    public static  final  String COL_1 ="id";
    public static  final  String COL_2 ="year";
    public static  final  String COL_3 ="name";
    public static  final  String COL_4 ="image";
    public static  final  String COL_5 ="price";
    public static  final  String COL_6 ="fuel_save";
    public static  final  String COL_7 ="description";
    private final Context myContext;
    SQLiteDatabase db;


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        if (Build.VERSION.SDK_INT >= 15){
            DATABASE_PATH = context.getApplicationInfo().dataDir + "/database/";
        } else {
            DATABASE_PATH = Environment.getDataDirectory() + "/data/data/" + context.getPackageName() + "/database/";
        }

        SQLiteDatabase db = this.getWritableDatabase();
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE table " + TABLE_NAME + "("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT, "+COL_3+" TEXT, "+COL_4+" BLOB, "+COL_5+" TEXT, "+COL_6+" INTEGER, "+COL_7+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(sqLiteDatabase);

    }

    public DatabaseHelper open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();

        return this;
    }

    public Cursor loadData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }


    public boolean insertData(String year, String name,String price, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, year);
        contentValues.put(COL_3, name);
        contentValues.put(COL_5, price);
        contentValues.put(COL_7, description);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
            else
                return true;
    }

    public Cursor updateData(String year, String name, String price,String description, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE "+ TABLE_NAME+ " SET " + COL_2 + "="+year+"," + COL_3 + "="+name+"," + COL_5 + "="+price+"," + COL_7 + "="+description+"  WHERE  "+COL_1+"="+id, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
//
//    public Cursor updateDownvote(int downvote, int id){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("UPDATE "+ TABLE_NAME+ " SET " + COL_5 + "="+downvote+" WHERE "+COL_1+"="+id, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }


}
