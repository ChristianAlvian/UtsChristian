package com.ahmfarisi.aplikasiagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context ctx;
    private static final String DATABASE_NAME = "db_kampus_0081";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_kampus_0081";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAMA = "namakampus";
    private static final String FIELD_KOTA = "kotakampus";
    private static final String FIELD_ALAMAT = "alamatkampus";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FIELD_NAMA + " VARCHAR(16), " +
                FIELD_KOTA + " VARCHAR(5), " +
                FIELD_ALAMAT + " VARCHAR(150) ); "
                ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long tambahAgenda(String tanggal, String jam, String kegiatan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, tanggal);
        cv.put(FIELD_KOTA, jam);
        cv.put(FIELD_ALAMAT, kegiatan);

        long eksekusi = db.insert(TABLE_NAME, null, cv);
        return eksekusi;
    }

    public Cursor bacaDataAgenda(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor varCursor = null;
        if(db != null){
            varCursor = db.rawQuery(query, null);
        }

        return varCursor;
    }

    public long hapusAgenda(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long eksekusi = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return eksekusi;
    }

    public long ubahAgenda(String id, String tanggal, String jam, String kegiatan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_NAMA, tanggal);
        cv.put(FIELD_KOTA, jam);
        cv.put(FIELD_ALAMAT, kegiatan);

        long eksekusi = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
        return eksekusi;
    }


}
