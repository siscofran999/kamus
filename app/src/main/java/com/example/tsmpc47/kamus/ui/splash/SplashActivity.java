package com.example.tsmpc47.kamus.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.tsmpc47.kamus.BR;
import com.example.tsmpc47.kamus.R;
import com.example.tsmpc47.kamus.databinding.ActivitySplashBinding;
import com.example.tsmpc47.kamus.ui.base.BaseActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mSplashViewModel;

    ActivitySplashBinding mActivitySplashBinding;
    private static final String TAG = "SplashActivity";

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySplashBinding = getViewDataBinding();
        mSplashViewModel.setNavigator(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public void gotoTransleteActivity() {
        Log.i(TAG, "gotoTransleteActivity: Masuk sini bro");
    }
}
