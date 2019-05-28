package android.example.test1.database.Tables.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.example.test1.database.Tables.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("Select * from Contacts")
    List<Contact> getAllContacts();

    @Query("Select COUNT(*) from Contacts")
    int countContacts();

    @Query("Select * from Contacts where id LIKE :id LIMIT 1")
    Contact findContactById(int id);

    @Insert
    void Insert(Contact... contacts);

    @Delete
    void Delete(Contact... contacts);

    @Query("DELETE FROM Contacts")
    void purge();
}
