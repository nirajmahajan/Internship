package android.example.test2.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.example.test2.database.Tables.Survey;

import java.util.List;

@Dao
public interface SurveyDao {

    @Query("Select * from Surveys")
    List<Survey> getAllSurveys();

    @Query("Select COUNT(*) from Surveys")
    int countSurveys();

    @Query("Select * from Surveys where id LIKE :id LIMIT 1")
    Survey findSurveyById(int id);

    @Update
    void Update(Survey... surveys);

    @Insert
    long[] Insert(Survey... surveys);

    @Delete
    void Delete(Survey... surveys);

    @Query("DELETE FROM Surveys")
    void purge();
}
