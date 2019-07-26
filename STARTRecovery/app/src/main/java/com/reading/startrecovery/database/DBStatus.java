package com.reading.startrecovery.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "DBStatus")
public class DBStatus {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "timestamp")
    private String timestamp = "";

    @NonNull
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NonNull String timestamp) {
        this.timestamp = timestamp;
    }
}
