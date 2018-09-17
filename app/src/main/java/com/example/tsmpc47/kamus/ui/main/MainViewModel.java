package com.example.tsmpc47.kamus.ui.main;

import com.example.tsmpc47.kamus.data.DataManager;
import com.example.tsmpc47.kamus.ui.base.BaseViewModel;
import com.example.tsmpc47.kamus.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {


    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }
}
