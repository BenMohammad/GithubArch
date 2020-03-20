package com.benmohammad.githubarch.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.benmohammad.githubarch.db.converter.DateConverter;
import com.benmohammad.githubarch.db.dao.UserDao;
import com.benmohammad.githubarch.db.entity.User;

@Database(entities = {User.class}, version = 1)
@TypeConverters(DateConverter.class)

public abstract class MyDatabase extends RoomDatabase {

    private static volatile MyDatabase INSTANCE;

    public abstract UserDao userDao();
}
