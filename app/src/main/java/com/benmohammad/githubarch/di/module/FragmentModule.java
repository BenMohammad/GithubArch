package com.benmohammad.githubarch.di.module;

import com.benmohammad.githubarch.fragments.UserProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract UserProfileFragment contributeUserProfileFragment();
}
