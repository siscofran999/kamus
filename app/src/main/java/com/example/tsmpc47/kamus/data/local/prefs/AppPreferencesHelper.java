package com.example.tsmpc47.kamus.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tsmpc47.kamus.di.PreferenceInfo;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private final static String KEY = "first_run";
    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void setFirstRun(Boolean isFirstRun) {
        mPrefs.edit().putBoolean(KEY, isFirstRun).apply();
    }

    @Override
    public Boolean getFirstRun() {
        return mPrefs.getBoolean(KEY, true);
    }

}
