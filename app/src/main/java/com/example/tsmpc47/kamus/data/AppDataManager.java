package com.example.tsmpc47.kamus.data;

import android.content.Context;

import com.example.tsmpc47.kamus.data.local.db.DbHelper;
import com.example.tsmpc47.kamus.data.local.prefs.PreferencesHelper;
import com.example.tsmpc47.kamus.data.model.Words;
import com.example.tsmpc47.kamus.data.model.db.Eng;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        this.mContext = context;
        this.mDbHelper = dbHelper;
        this.mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Observable<List<Eng>> fetchDatabaseEngInd() {
        return mDbHelper.fetchDatabaseEngInd();
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