package android.example.test1.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.example.test1.database.Tables.Person;
import android.example.test1.database.Tables.SurveyData;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("Select * from Persons")
    List<Person> getAllPersons();

    @Query("Select COUNT(*) from Persons")
    int countPersons();

    @Query("Select * from Persons where id LIKE :id LIMIT 1")
    Person findPersonById(int id);

    @Update
    void Update(Person... persons);

    @Insert
    long[] Insert(Person... persons);

    @Delete
    void Delete(Person... persons);

    @Query("DELETE FROM Persons")
    void purge();
}
