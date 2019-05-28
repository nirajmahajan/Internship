package android.example.test1.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.example.test1.database.Tables.SocialWorker;

import java.util.List;

@Dao
public interface SocialWorkerDao {

    @Query("Select * from SocialWorkers")
    List<SocialWorker> getAllSocialWorkers();

    @Query("Select COUNT(*) from SocialWorkers")
    int countSocialWorkers();

//    @Query("Select * from SocialWorkers where id LIKE :id LIMIT 1")
//    SocialWorker findSocialWorkerById(int id);

    @Query("Select * from SocialWorkers where username LIKE :username")
    List<SocialWorker> findSocialWorkerByUsername(String username);

    @Update
    void Update(SocialWorker... socialWorkers);

    @Insert
    long[] Insert(SocialWorker... socialWorkers);

    @Delete
    void Delete(SocialWorker... socialWorkers);

    @Query("DELETE FROM SocialWorkers")
    void purge();
}
