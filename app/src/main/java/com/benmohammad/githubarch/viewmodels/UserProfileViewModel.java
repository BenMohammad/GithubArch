package com.benmohammad.githubarch.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.benmohammad.githubarch.db.dao.UserDao;
import com.benmohammad.githubarch.db.entity.User;
import com.benmohammad.githubarch.repo.UserRepository;

import javax.inject.Inject;

public class UserProfileViewModel extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepo;

    @Inject
    public UserProfileViewModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(String userId) {
        if(this.user != null) {
            return;
        }
        user = userRepo.getUser(userId);
    }

    public LiveData<User> getUser() {
        return this.user;
    }
}
