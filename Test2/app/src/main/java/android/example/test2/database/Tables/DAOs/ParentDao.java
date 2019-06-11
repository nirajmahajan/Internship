package android.example.test2.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.example.test2.database.Tables.Parent;

import java.util.List;

@Dao
public interface ParentDao {

    @Query("Select * from Parents")
    List<Parent> getAllParents();

    @Query("Select COUNT(*) from Parents")
    int countParents();

    @Query("Select * from Parents where id LIKE :id LIMIT 1")
    Parent findParentById(int id);

    @Update
    void Update(Parent... parents);

    @Insert
    long[] Insert(Parent... parents);

    @Delete
    void Delete(Parent... parents);

    @Query("DELETE FROM Parents")
    void purge();
}
