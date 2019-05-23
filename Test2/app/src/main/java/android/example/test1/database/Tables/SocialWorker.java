package android.example.test1.database.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "SocialWorkers")
public class SocialWorker {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "personId")
    private int personId;

    @ColumnInfo(name = "contactId")
    private int contactId;

    @ColumnInfo
    private ArrayList<Integer> surveyIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public ArrayList<Integer> getSurveyIds() {
        return surveyIds;
    }

    public void setSurveyIds(ArrayList<Integer> surveyIds) {
        this.surveyIds = surveyIds;
    }
}
