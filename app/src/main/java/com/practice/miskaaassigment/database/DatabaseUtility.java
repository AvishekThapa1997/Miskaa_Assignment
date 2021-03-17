package com.practice.miskaaassigment.database;

import android.content.Context;

import androidx.room.Room;

import com.practice.miskaaassigment.utility.Constants;

public class DatabaseUtility {
    private DatabaseUtility() {
    }

    private static AppDatabase appDatabase;
    private static final Object mLock = new Object();

    public static AppDatabase getAppDatabase(Context context) {
        synchronized (mLock) {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        Constants.DB_NAME
                ).build();
            }
        }
        return appDatabase;
    }
}
