package android.example.test2.database.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Parents")
public class Parent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "personId")
    private int personId;

    @ColumnInfo(name = "childId")
    private int childId;

    @ColumnInfo(name = "contactId")
    private int contactId;

    @ColumnInfo(name = "employment")
    private String employment;

//    @ColumnInfo(name = "signature")
//    private Image signature;

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    public int getPersonId() {return this.personId;}
    public void setPersonId(int personId) {this.personId = personId;}
    public int getChildId() {return this.childId;}
    public void setChildId(int childId) {this.childId = childId;}
    public int getContactId() {return contactId;}
    public void setContactId(int contactId) {this.contactId = contactId;}
    public String getEmployment() {return this.employment;}
    public void setEmployment(String employment) {this.employment = employment;}
//    public Image getSignature() {return this.signature;}
//    public void setSignature(Image signature) {this.signature = signature;}


}
