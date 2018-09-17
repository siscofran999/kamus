package com.example.tsmpc47.kamus.di.component;

import android.app.Application;
import com.example.tsmpc47.kamus.Kamus;
import com.example.tsmpc47.kamus.di.builder.ActivityBuilder;
import com.example.tsmpc47.kamus.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(Kamus app);
}
