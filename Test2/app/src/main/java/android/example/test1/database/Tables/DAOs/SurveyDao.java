package android.example.test1.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.example.test1.database.Tables.Survey;

import java.util.List;

@Dao
public interface SurveyDao {

    @Query("Select * from Surveys")
    List<Survey> getAllSurveys();

    @Query("Select COUNT(*) from Surveys")
    int countSurveys();

    @Query("Select * from Surveys where id LIKE :id LIMIT 1")
    Survey findSurveyById(int id);

    @Insert
    void Insert(Survey... surveys);

    @Delete
    void Delete(Survey... surveys);

    @Query("DELETE FROM Surveys")
    void purge();
}
