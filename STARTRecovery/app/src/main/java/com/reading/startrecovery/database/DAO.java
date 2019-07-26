package com.reading.startrecovery.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DAO {

    @Query("Select * from dbstatus")
    List<DBStatus> getStatus();

    @Query("Delete from dbstatus")
    void purge();

    @Insert
    void add(DBStatus... dbStatuses);

    @Query("Select COUNT(*) from dbstatus")
    int count();
}
