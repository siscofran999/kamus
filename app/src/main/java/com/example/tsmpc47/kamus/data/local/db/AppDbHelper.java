package com.example.tsmpc47.kamus.data.local.db;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.tsmpc47.kamus.R;
import com.example.tsmpc47.kamus.data.model.Word;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.database.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static android.provider.BaseColumns._ID;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsEngInd.RESULT_WORD_ENG_IND;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsEngInd.SEARCH_WORD_ENG_IND;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsIndEng.RESULT_WORD_IND_ENG;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.KamusColumnsIndEng.SEARCH_WORD_IND_ENG;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.TABLE_NAME_ENG_IND;
import static com.example.tsmpc47.kamus.data.local.db.DatabaseContract.TABLE_NAME_IND_ENG;

@Singleton
public class AppDbHelper implements DbHelper {

    private final Context mContext;
    private SQLiteDatabase mSQLiteDatabase;
    private DatabaseHelper mDatabaseHelper;
    private static final String TAG = "AppDbHelper";

    @Inject
    public AppDbHelper(Context context, DatabaseHelper databaseHelper){
        this.mContext = context;
        this.mDatabaseHelper = databaseHelper;
    }

    @Override
    public Observable<List<Word>> fetchDatabaseEngInd() {
        return Observable.create(emitter -> {
            List<Word> wordList = preLoadRawEngInd();
            Log.i(TAG, "fetchDatabaseEngInd: "+wordList.size());
            openDB();
            beginTransaction();
            try {
                for (Word word: wordList) {
                    insertTransaction(word,true);
                }
                mSQLiteDatabase.setTransactionSuccessful();
            }catch (Exception e){
                Log.e(TAG, "fetchDatabaseEngInd: "+e.getMessage());
            }

            mSQLiteDatabase.endTransaction();
            mDatabaseHelper.close();

            emitter.onNext(wordList);
            emitter.onComplete();
        });
    }

    @Override
    public Observable<List<Word>> fetchDatabaseIndEng() {
        return Observable.create(emitter -> {
            List<Word> wordList = preLoadRawIndEng();
            Log.i(TAG, "fetchDatabaseIndEng: "+wordList.size());
            openDB();
            beginTransaction();
            try {
                for (Word word: wordList) {
                    insertTransaction(word,false);
                }
                mSQLiteDatabase.setTransactionSuccessful();
            }catch (Exception e){
                Log.e(TAG, "fetchDatabaseEngInd: "+e.getMessage());
            }

            mSQLiteDatabase.endTransaction();
            mDatabaseHelper.close();

            emitter.onNext(wordList);
            emitter.onComplete();
        });
    }

    private void beginTransaction() {
        mSQLiteDatabase.beginTransaction();
    }

    @Override
    public AppDbHelper openDB() throws SQLException {
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
        return this;
    }

    @Override
    public void closeDb() {
        mDatabaseHelper.close();
    }

    @Override
    public Observable<List<Word>> getBySearchWord(String word, String tableName, String searchWord, String resultWord) {

        Log.i(TAG, "Word: "+word);
        Log.i(TAG, "Table Name: "+tableName);
        Log.i(TAG, "Search Word: "+searchWord);

        Cursor cursor = mSQLiteDatabase.query(tableName,null, searchWord +" LIKE ?",
                new String[]{word + "%"},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        Log.i(TAG, "getBySearchWord: "+cursor.getCount());
        final ArrayList<Word> arrayList = new ArrayList<>();
        Word words;
        if (cursor.getCount() > 0) {
            do {
                words = new Word();
                words.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                words.setWords(cursor.getString(cursor.getColumnIndexOrThrow(searchWord)));
                words.setTranslation(cursor.getString(cursor.getColumnIndexOrThrow(resultWord)));

                Log.i(TAG, "getBySearchWord: "+cursor.getString(cursor.getColumnIndexOrThrow(searchWord)));

                arrayList.add(words);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return Observable.fromCallable(() -> arrayList);
    }

    private void insertTransaction(Word word, Boolean english) {
        String sql;
        if (english){
            sql = "INSERT INTO "+ TABLE_NAME_ENG_IND +" ("+ SEARCH_WORD_ENG_IND +", "+ RESULT_WORD_ENG_IND
                    +") VALUES (?, ?)";
        }else{
            sql = "INSERT INTO "+ TABLE_NAME_IND_ENG +" ("+ SEARCH_WORD_IND_ENG +", "+ RESULT_WORD_IND_ENG
                    +") VALUES (?, ?)";
        }
        SQLiteStatement stmt = mSQLiteDatabase.compileStatement(sql);
        stmt.bindString(1, word.getWords());
        stmt.bindString(2, word.getTranslation());
        stmt.execute();
        stmt.clearBindings();
    }

    private List<Word> preLoadRawEngInd() {
        ArrayList<Word> words = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = mContext.getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                if (line != null){
                    String[] splitStr = line.split("\t");
                    Word word = new Word(count,splitStr[0],splitStr[1]);
                    words.add(word);
                }
                count++;
            }while (line != null);
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i(TAG, "preLoadRaw: "+words.size());
        return words;
    }

    private List<Word> preLoadRawIndEng() {
        ArrayList<Word> words = new ArrayList<>();
        String line;
        BufferedReader reader;
        try {
            Resources res = mContext.getResources();
            InputStream raw_dict = res.openRawResource(R.raw.indonesia_english);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                if (line != null){
                    String[] splitStr = line.split("\t");
                    Word word = new Word(count,splitStr[0],splitStr[1]);
                    words.add(word);
                }
                count++;
            }while (line != null);
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i(TAG, "preLoadRaw: "+words.size());
        return words;
    }
}
