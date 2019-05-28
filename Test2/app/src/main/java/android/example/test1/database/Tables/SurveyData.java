package android.example.test1.database.Tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "SurveyDatas")
public class SurveyData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}
}
