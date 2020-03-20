package com.benmohammad.githubarch.di.component;

import android.app.Activity;
import android.app.Application;

import com.benmohammad.App;
import com.benmohammad.githubarch.di.module.ActivityModule;
import com.benmohammad.githubarch.di.module.AppModule;
import com.benmohammad.githubarch.di.module.FragmentModule;
import com.bumptech.glide.module.AppGlideModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class, AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
