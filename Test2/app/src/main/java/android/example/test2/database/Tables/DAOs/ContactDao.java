package android.example.test2.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.example.test2.database.Tables.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("Select * from Contacts")
    List<Contact> getAllContacts();

    @Query("Select COUNT(*) from Contacts")
    int countContacts();

    @Query("Select * from Contacts where id LIKE :id LIMIT 1")
    Contact findContactById(int id);

    @Update
    void Update(Contact... contacts);

    @Insert
    long[] Insert(Contact... contacts);

    @Delete
    void Delete(Contact... contacts);

    @Query("DELETE FROM Contacts")
    void purge();
}
