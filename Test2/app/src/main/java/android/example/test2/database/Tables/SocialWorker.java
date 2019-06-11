package android.example.test2.database.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "SocialWorkers")
public class SocialWorker {

//    @PrimaryKey(autoGenerate = true)
//    private int id;

//    @ColumnInfo(name = "personId")
//    private int personId;
//
//    @ColumnInfo(name = "contactId")
//    private int contactId;

    @ColumnInfo(name = "name")
    private String name;

//    @ColumnInfo(name = "surveyIds")
//    private int[] surveyIds;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String username;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

//    public int getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(int personId) {
//        this.personId = personId;
//    }
//
//    public int getContactId() {
//        return contactId;
//    }
//
//    public void setContactId(int contactId) {
//        this.contactId = contactId;
//    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
//    public int[] getSurveyIds() {
//        return surveyIds;
//    }
//
//    public void setSurveyIds(int[] surveyIds) {
//        this.surveyIds = surveyIds;
//    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}
}
