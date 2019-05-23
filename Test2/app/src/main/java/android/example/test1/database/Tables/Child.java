package android.example.test1.database.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Children")
public class Child {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "personId")
    private int personId;

    @ColumnInfo(name = "surveyId")
    private int surveyId;

    @ColumnInfo(name = "parentId")
    private int parentId;

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    public int getPersonId() {return this.personId;}
    public void setPersonId(int personId) {this.personId = personId;}
    public int getSurveyId() {return this.surveyId;}
    public void setSurveyId(int surveyId) {this.surveyId = surveyId;}
    public int getParentId() {return this.parentId;}
    public void setParentId(int parentId) {this.parentId = parentId;}

}
