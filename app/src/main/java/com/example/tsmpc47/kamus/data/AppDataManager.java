package com.example.tsmpc47.kamus.data;

import android.content.Context;
import android.util.Log;

import com.example.tsmpc47.kamus.data.local.db.AppDbHelper;
import com.example.tsmpc47.kamus.data.local.db.DbHelper;
import com.example.tsmpc47.kamus.data.local.prefs.PreferencesHelper;
import com.example.tsmpc47.kamus.data.model.Word;

import android.database.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private static final String TAG = "AppDataManager";

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        this.mContext = context;
        this.mDbHelper = dbHelper;
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Observable<List<Word>> fetchDatabaseEngInd() {
        Log.i(TAG, "fetchDatabaseEngInd: Masuk SIni");
        return mDbHelper.fetchDatabaseEngInd();
    }

    @Override
    public Observable<List<Word>> fetchDatabaseIndEng() {
        return mDbHelper.fetchDatabaseIndEng();
    }

    @Override
    public AppDbHelper openDB() throws SQLException {
        return mDbHelper.openDB();
    }

    @Override
    public void closeDb() {
        mDbHelper.closeDb();
    }

    @Override
    public Observable<List<Word>> getBySearchWord(String word, String tableName, String searchWord) {
        return mDbHelper.getBySearchWord(word,tableName,searchWord);
    }

    @Override
    public Boolean getFirstRun() {
        return mPreferencesHelper.getFirstRun();
    }

    @Override
    public void setFirstRun(Boolean isFirstRun) {
        mPreferencesHelper.setFirstRun(isFirstRun);
    }
}
