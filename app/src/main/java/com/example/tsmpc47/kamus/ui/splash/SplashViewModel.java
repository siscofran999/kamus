package com.example.tsmpc47.kamus.ui.splash;

import android.annotation.SuppressLint;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.example.tsmpc47.kamus.data.DataManager;
import com.example.tsmpc47.kamus.data.model.Words;
import com.example.tsmpc47.kamus.ui.base.BaseViewModel;
import com.example.tsmpc47.kamus.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SplashViewModel extends BaseViewModel<SplashNavigator>{

    public ObservableInt progressObs = new ObservableInt(0);

    public ObservableInt showProgress;
    private static final String TAG = "SplashViewModel";

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        startProgress();
    }

    private void startProgress() {
        showProgress = new ObservableInt();
        showProgress.set(View.INVISIBLE);

        loadDB();
    }

    @SuppressLint("CheckResult")
    private void loadDB() {
        Boolean start = getDataManager().getFirstRun();

        if (start){
            showProgress.set(View.VISIBLE);
            getDataManager().fetchDatabaseEngInd()
            .flatMap(words -> {
                Log.i(TAG, "apply: "+words.size());
                return Observable.fromIterable(words);
            })
            .subscribeOn(getSchedulerProvider().io())
            .observeOn(getSchedulerProvider().ui())
            .subscribe(words -> {
                Log.i(TAG, "loadDB: ");
                showProgress.set(View.INVISIBLE);
                getNavigator().gotoTransleteActivity();
            });

        }
    }
}
