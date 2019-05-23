package android.example.test1.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Children")
public class Child {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "First Name")
    private String firstName;

    @ColumnInfo(name = "Last Name")
    private String lastName;
}
