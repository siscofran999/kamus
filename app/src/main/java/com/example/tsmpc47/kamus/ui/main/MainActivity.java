package com.example.tsmpc47.kamus.ui.main;

import android.os.Bundle;

import com.example.tsmpc47.kamus.BR;
import com.example.tsmpc47.kamus.R;
import com.example.tsmpc47.kamus.databinding.ActivityMainBinding;
import com.example.tsmpc47.kamus.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    @Inject
    MainViewModel mMainViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
