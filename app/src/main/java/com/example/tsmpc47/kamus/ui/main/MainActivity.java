package com.example.tsmpc47.kamus.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tsmpc47.kamus.BR;
import com.example.tsmpc47.kamus.R;
import com.example.tsmpc47.kamus.databinding.ActivityMainBinding;
import com.example.tsmpc47.kamus.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    LinearLayoutManager mLayoutManager;

    private MainViewModel mMainViewModel;

    private ActivityMainBinding mActivityMainBinding;

    public static Intent getStartMain(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);

        setUp();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mActivityMainBinding.rc.setLayoutManager(mLayoutManager);
        mActivityMainBinding.rc.setItemAnimator(new DefaultItemAnimator());
    }

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
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel.class);
        return mMainViewModel;
    }
}
