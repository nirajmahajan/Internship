package com.reading.startrecovery.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.widget.Toast;

@Database(entities = {DBStatus.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;

    public abstract DAO dao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "local-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE.dao().purge();
        INSTANCE = null;
    }

    public static void addTimeStamp(String timestamp){
        INSTANCE.dao().purge();
        DBStatus dbStatus = new DBStatus();
        dbStatus.setTimestamp(timestamp);
        INSTANCE.dao().add(dbStatus);
    }

    public static boolean backupExists(){
        return (INSTANCE.dao().count() == 1);
    }

    public static String getTimeStamp(){
        if(backupExists()){
            DBStatus dbStatus = INSTANCE.dao().getStatus().get(0);
            return dbStatus.getTimestamp();
        }
        else {
            String ans = "NOT FOUND";
            return ans;
        }
    }
}
