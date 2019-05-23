package android.example.test1.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.example.test1.database.Tables.Child;
import android.example.test1.database.Tables.Contact;
import android.example.test1.database.Tables.Parent;
import android.example.test1.database.Tables.Person;
import android.example.test1.database.Tables.SocialWorker;
import android.example.test1.database.Tables.Survey;

@Database(entities = {Contact.class, Person.class, Parent.class, Child.class, SocialWorker.class, Survey.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;

    public abstract Dao dao();

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
        INSTANCE.clearAllTables();
        INSTANCE = null;
    }

    @Override
    public void clearAllTables() {
        INSTANCE.dao().purgeAll();
    }
}
