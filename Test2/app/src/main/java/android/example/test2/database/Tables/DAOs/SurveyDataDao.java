package android.example.test2.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.example.test2.database.Tables.SurveyData;

import java.util.List;

@Dao
public interface SurveyDataDao {

    @Query("Select * from SurveyDatas")
    List<SurveyData> getAllSurveyDatas();

    @Query("Select COUNT(*) from SurveyDatas")
    int countSurveyDatas();

    @Query("Select * from SurveyDatas where id LIKE :id LIMIT 1")
    SurveyData findSurveyDataById(int id);

    @Update
    void Update(SurveyData... surveyDatas);

    @Insert
    long[] Insert(SurveyData... contacts);

    @Delete
    void Delete(SurveyData... contacts);

    @Query("DELETE FROM SurveyDatas")
    void purge();
}
