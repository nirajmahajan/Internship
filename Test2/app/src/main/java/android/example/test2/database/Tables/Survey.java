package android.example.test2.database.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Surveys")
public class Survey {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "childId")
    private int childId;

    @ColumnInfo(name = "socialWorkerUsername")
    private String socialWorkerUsername;

//    @ColumnInfo(name = "surveyData")
//    private SurveyData surveyData;

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    public int getChildId() {return this.childId;}
    public void setChildId(int childId) {this.childId = childId;}
    public String getSocialWorkerUsername() {return this.socialWorkerUsername;}
    public void setSocialWorkerUsername(String socialWorkerUsername) {this.socialWorkerUsername = socialWorkerUsername;}
//    public SurveyData getSurveyData() {return this.surveyData;}
//    public void setSurveyData(SurveyData surveyData) {this.surveyData = surveyData;}
}
