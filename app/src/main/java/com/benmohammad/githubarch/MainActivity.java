package com.benmohammad.githubarch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.benmohammad.githubarch.fragments.UserProfileFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private static String USER_LOGIN = "JakeWharton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureDagger();
        this.showFragment(savedInstanceState);
    }


    private void showFragment(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            UserProfileFragment fragment = new UserProfileFragment();

            Bundle b = new Bundle();
            b.putString(UserProfileFragment.UID_KEY, USER_LOGIN);
            fragment.setArguments(b);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, null)
                    .commit();
        }
    }

    private void configureDagger() {
        AndroidInjection.inject(this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
