package com.benmohammad.githubarch.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.benmohammad.githubarch.di.key.ViewModelKey;
import com.benmohammad.githubarch.fragments.UserProfileFragment;
import com.benmohammad.githubarch.viewmodels.FactoryViewModel;
import com.benmohammad.githubarch.viewmodels.UserProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel.class)
    abstract ViewModel bindUserProfileViewModel(UserProfileViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
