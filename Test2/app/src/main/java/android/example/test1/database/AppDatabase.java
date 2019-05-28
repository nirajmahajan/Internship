package android.example.test1.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.example.test1.database.Tables.Child;
import android.example.test1.database.Tables.Contact;
import android.example.test1.database.Tables.DAOs.ChildDao;
import android.example.test1.database.Tables.DAOs.ContactDao;
import android.example.test1.database.Tables.DAOs.ParentDao;
import android.example.test1.database.Tables.DAOs.PersonDao;
import android.example.test1.database.Tables.DAOs.SocialWorkerDao;
import android.example.test1.database.Tables.DAOs.SurveyDao;
import android.example.test1.database.Tables.DAOs.SurveyDataDao;
import android.example.test1.database.Tables.Parent;
import android.example.test1.database.Tables.Person;
import android.example.test1.database.Tables.SocialWorker;
import android.example.test1.database.Tables.Survey;
import android.example.test1.database.Tables.SurveyData;

@Database(entities = {Contact.class, Person.class, Parent.class, Child.class, SocialWorker.class, Survey.class, SurveyData.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;

    public abstract ChildDao childDao();
    public abstract PersonDao personDao();
    public abstract ContactDao contactDao();
    public abstract SocialWorkerDao socialWorkerDao();
    public abstract SurveyDao surveyDao();
    public abstract SurveyDataDao surveyDataDao();
    public abstract ParentDao parentDao();

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
        INSTANCE.purgeAll();
        INSTANCE = null;
    }

    private void purgeAll(){
        INSTANCE.childDao().purge();
        INSTANCE.contactDao().purge();
        INSTANCE.socialWorkerDao().purge();
        INSTANCE.surveyDao().purge();
        INSTANCE.surveyDataDao().purge();
        INSTANCE.personDao().purge();
        INSTANCE.parentDao().purge();
    }
}
