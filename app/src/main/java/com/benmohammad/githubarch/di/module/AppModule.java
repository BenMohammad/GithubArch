package com.benmohammad.githubarch.di.module;

import android.app.Application;

import androidx.room.Room;

import com.benmohammad.githubarch.api.UserWebService;
import com.benmohammad.githubarch.db.MyDatabase;
import com.benmohammad.githubarch.db.dao.UserDao;
import com.benmohammad.githubarch.db.entity.User;
import com.benmohammad.githubarch.repo.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    MyDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                MyDatabase.class, "MyDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(MyDatabase database) {
        return database.userDao();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserWebService webService, UserDao userDao, Executor executor) {
        return new UserRepository(webService, userDao, executor);
    }

    private static String BASE_URL = "https://api.github.com/";

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    UserWebService provideWebservice(Retrofit restAdapter) {
        return restAdapter.create(UserWebService.class);
    }
}
