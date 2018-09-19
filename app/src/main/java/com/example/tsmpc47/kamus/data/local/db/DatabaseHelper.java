package com.example.tsmpc47.kamus.data.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsEngInd.RESULT_WORD_ENG_IND;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsEngInd.SEARCH_WORD_ENG_IND;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsIndEng.RESULT_WORD_IND_ENG;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsIndEng.SEARCH_WORD_IND_ENG;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.TABLE_NAME_ENG_IND;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.TABLE_NAME_IND_ENG;

/**
 * Created by irmansyah on 06/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "kamus.db";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_KAMUS_ENG_IND = "create table " + TABLE_NAME_ENG_IND +
            " (" + _ID + " integer primary key autoincrement, " +
            SEARCH_WORD_ENG_IND + " text not null, " +
            RESULT_WORD_ENG_IND + " text not null);";

    public static String CREATE_TABLE_KAMUS_IND_ENG = "create table " + TABLE_NAME_IND_ENG +
            " (" + _ID + " integer primary key autoincrement, " +
            SEARCH_WORD_IND_ENG + " text not null, " +
            RESULT_WORD_IND_ENG + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KAMUS_ENG_IND);
        db.execSQL(CREATE_TABLE_KAMUS_IND_ENG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ENG_IND);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_IND_ENG);
        onCreate(db);
    }
}
