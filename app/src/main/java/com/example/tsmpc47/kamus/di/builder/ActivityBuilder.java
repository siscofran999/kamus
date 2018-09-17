package com.example.tsmpc47.kamus.di.builder;

import com.example.tsmpc47.kamus.ui.main.MainActivity;
import com.example.tsmpc47.kamus.ui.main.MainActivityModule;
import com.example.tsmpc47.kamus.ui.splash.SplashActivity;
import com.example.tsmpc47.kamus.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();
}
