package com.example.tsmpc47.kamus.ui.main;

import com.example.tsmpc47.kamus.data.DataManager;
import com.example.tsmpc47.kamus.ui.splash.SplashViewModel;
import com.example.tsmpc47.kamus.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager,SchedulerProvider schedulerProvider){
        return new MainViewModel(dataManager,schedulerProvider);
    }
}
