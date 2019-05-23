package android.example.test1.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.example.test1.database.Tables.Child;
import android.example.test1.database.Tables.Contact;
import android.example.test1.database.Tables.Parent;
import android.example.test1.database.Tables.Person;
import android.example.test1.database.Tables.SocialWorker;
import android.example.test1.database.Tables.Survey;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface dao {

    @Query("Select * from Children")
    List<Child> getAllChildren();

    @Query("Select * from Parents")
    List<Parent> getAllParents();

    @Query("Select * from Persons")
    List<Person> getAllPersons();

    @Query("Select * from Contacts")
    List<Contact> getAllContacts();

    @Query("Select COUNT(*) from Persons")
    int countPersons();

    @Query("Select COUNT(*) from Children")
    int countChildren();

    @Query("Select COUNT(*) from Parents")
    int countParents();

    @Query("Select COUNT(*) from Contacts")
    int countContacts();

    @Query("Select * from Parents where id LIKE :id LIMIT 1")
    Parent findParentById(int id);

    @Query("Select * from Children where id LIKE :id LIMIT 1")
    Child findChildById(int id);

    @Query("Select * from Persons where id LIKE :id LIMIT 1")
    Person findPersonById(int id);

    @Query("Select * from Contacts where id LIKE :id LIMIT 1")
    Contact findContactById(int id);

    @Query("Select * from SocialWorkers where id LIKE :id LIMIT 1")
    SocialWorker findSocialWorkerById(int id);

    @Query("Select * from Surveys where id LIKE :id LIMIT 1")
    Survey findSurveyById(int id);

    @Query("Select * from Surveys where socialWorkerId LIKE :id")
    ArrayList<Survey> findSurveysOfWorkerId(int id);



    @Insert

}
