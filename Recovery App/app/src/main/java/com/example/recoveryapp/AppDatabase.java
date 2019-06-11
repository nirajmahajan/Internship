package com.example.recoveryapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Temp.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
}
