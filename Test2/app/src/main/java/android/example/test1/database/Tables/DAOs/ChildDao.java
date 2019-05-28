package android.example.test1.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.example.test1.database.Tables.Child;

import java.util.List;

@Dao
public interface ChildDao {

    @Query("Select * from Children")
    List<Child> getAllChildren();

    @Query("Select COUNT(*) from Children")
    int countChildren();

    @Query("Select * from Children where id LIKE :id LIMIT 1")
    Child findChildById(int id);

    @Insert
    void Insert(Child... children);

    @Delete
    void Delete(Child... children);

    @Query("DELETE FROM Children")
    void purge();
}
