package com.benmohammad.githubarch.api;

import com.benmohammad.githubarch.db.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserWebService {

    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}
