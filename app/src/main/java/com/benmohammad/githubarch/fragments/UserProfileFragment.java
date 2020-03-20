package com.benmohammad.githubarch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.benmohammad.githubarch.R;
import com.benmohammad.githubarch.db.entity.User;
import com.benmohammad.githubarch.viewmodels.UserProfileViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class UserProfileFragment extends Fragment {

    public static final String UID_KEY = "uid";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UserProfileViewModel viewModel;

    @BindView(R.id.fragment_user_profile_image)
    ImageView imageView;

    @BindView(R.id.fragment_user_profile_username)
    TextView username;

    @BindView(R.id.fragment_user_profile_company)
    TextView company;

    @BindView(R.id.fragment_user_profile_website)
    TextView website;

    public UserProfileFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.configureDagger();
        this.configureViewModel();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        String userLogin = getArguments().getString(UID_KEY);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserProfileViewModel.class);
        viewModel.init(userLogin);
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> updateUI(user));
    }

    private void updateUI(@Nullable User user) {
        if(user != null) {
            Glide.with(this).load(user.getAvatar_url()).apply(RequestOptions.circleCropTransform()).into(imageView);
            this.username.setText(user.getName());
            this.company.setText(user.getCompany());
            this.website.setText(user.getBlog());
        }
    }
}
