package com.example.tsmpc47.kamus.di.module;

import android.app.Application;
import android.content.Context;

import com.example.tsmpc47.kamus.data.AppDataManager;
import com.example.tsmpc47.kamus.data.DataManager;
import com.example.tsmpc47.kamus.data.local.db.AppDbHelper;
import com.example.tsmpc47.kamus.data.local.db.DatabaseHelper;
import com.example.tsmpc47.kamus.data.local.db.DbHelper;
import com.example.tsmpc47.kamus.data.local.prefs.AppPreferencesHelper;
import com.example.tsmpc47.kamus.data.local.prefs.PreferencesHelper;
import com.example.tsmpc47.kamus.di.DatabaseInfo;
import com.example.tsmpc47.kamus.di.PreferenceInfo;
import com.example.tsmpc47.kamus.utils.AppConstants;
import com.example.tsmpc47.kamus.utils.rx.AppSchedulerProvider;
import com.example.tsmpc47.kamus.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelper(Context context){
        return new DatabaseHelper(context);
    }

}
