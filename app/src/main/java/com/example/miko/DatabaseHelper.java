package com.example.miko.laundryonline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by miko on 11/28/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transaksi.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TRANSAKSI_TABLE =
                "CREATE TABLE " + DatabaseContact.TransaksiEntry.TABLE_NAME+" (" +
                        DatabaseContact.TransaksiEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_TANGGAL + " TEXT NOT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_JAM+ " TEXT NOT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_KANTONG+ " TEXT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_BED+ " TEXT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_KARPET+ " TEXT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_TOTAL+ " INT NOT NULL"+
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_TRANSAKSI_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if( i1 > i){
            sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " +
                    DatabaseContact.TransaksiEntry.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}
