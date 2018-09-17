package com.example.tsmpc47.kamus.ui.splash;

import com.example.tsmpc47.kamus.data.DataManager;
import com.example.tsmpc47.kamus.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager,SchedulerProvider schedulerProvider){
        return new SplashViewModel(dataManager,schedulerProvider);
    }
}
