package com.example.tsmpc47.kamus.ui.splash;

import android.annotation.SuppressLint;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.example.tsmpc47.kamus.data.DataManager;
import com.example.tsmpc47.kamus.data.model.Word;
import com.example.tsmpc47.kamus.ui.base.BaseViewModel;
import com.example.tsmpc47.kamus.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SplashViewModel extends BaseViewModel<SplashNavigator>{

    public ObservableInt progressObs = new ObservableInt(0);

    public ObservableInt showProgress;
    private static final String TAG = "SplashViewModel";

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

    public void startProgress() {
        showProgress = new ObservableInt();
        showProgress.set(View.INVISIBLE);

        loadDB();
    }

    private void loadDB() {
        Boolean start = getDataManager().getFirstRun();
        Log.i(TAG, "loadDB: "+start);
        if (start){
            showProgress.set(View.VISIBLE);

            Observable<Boolean> insertEngInd = getDataManager().fetchDatabaseEngInd()
                    .map(words -> {
                        Log.i(TAG, "loadDB: Ini load 1");
                        return words.size() > 0;
                    });

            Observable<Boolean> insertIndEng = getDataManager().fetchDatabaseIndEng()
                    .map(words -> {
                        Log.i(TAG, "loadDB: Ini load 2");
                        for (int i = 0; i < words.size(); i++) {
                            if (i % 50 == 0 || i == words.size()){
                                progressObs.set(i);
                            }
                        }
                        return words.size() > 0;
                    });

            getCompositeDisposable().add(Observable.merge(insertEngInd,insertIndEng)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(Boolean -> {

                    }, throwable -> Log.e(TAG, "accept: "+throwable.getMessage()),
                        () -> {
                            showProgress.set(View.INVISIBLE);
                            getDataManager().setFirstRun(false);
                            getNavigator().gotoTransleteActivity();
                        }));

        }else{
            getNavigator().gotoTransleteActivity();
        }
    }
}
