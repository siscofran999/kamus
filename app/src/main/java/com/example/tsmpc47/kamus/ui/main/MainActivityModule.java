package com.example.tsmpc47.kamus.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tsmpc47.kamus.ViewModelProviderFactory;
import com.example.tsmpc47.kamus.data.DataManager;
import com.example.tsmpc47.kamus.data.model.Word;
import com.example.tsmpc47.kamus.ui.splash.SplashViewModel;
import com.example.tsmpc47.kamus.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager,SchedulerProvider schedulerProvider){
        return new MainViewModel(dataManager,schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideSearchLinearLayoutManager(MainActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    MainAdapter provideMainAdapter() {
        return new MainAdapter(new ArrayList<Word>());
    }

    @Provides
    ViewModelProvider.Factory provideSearchViewModelProviderFactory(MainViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
