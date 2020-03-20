package com.benmohammad.githubarch.repo;

import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.benmohammad.githubarch.api.UserWebService;
import com.benmohammad.githubarch.db.dao.UserDao;
import com.benmohammad.githubarch.db.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final UserWebService webService;
    private final UserDao userDao;
    private final Executor executor;

    @Inject
    public UserRepository(UserWebService webService, UserDao userDao, Executor executor) {
        this.webService = webService;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(final String userLogin) {
        refreshUser(userLogin);
        return userDao.load(userLogin);
    }

    private void refreshUser(final String userLogin) {
        executor.execute(() -> {
            boolean userExists = (userDao.hasUser(userLogin, getMaxRefreshTime(new Date())) != null);
            if (!userExists) {
                webService.getUser(userLogin).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        executor.execute(() -> {
                            User user = response.body();
                            user.setLastRefresh(new Date());
                            userDao.save(user);
                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }


    private Date getMaxRefreshTime(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
