package android.example.test2.database.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Persons")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "firstName")
    private String firstName = null;

    @ColumnInfo(name = "lastName")
    private String lastName = null;

    // 0 -> female
    // 1 -> male
    // 2 -> other
    @ColumnInfo(name = "gender")
    private int gender = -1;

    @ColumnInfo(name = "age")
    private int age = -1;

//    @ColumnInfo(name = "image")
//    Image image = null;

    public void makePerson(String firstName, String lastName, int gender, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
//        this.image = image;
    }

    public int getId() {return this.id;}
    public void setId (int id) {this.id = id;}
    public String getFirstName() {return this.firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public int getGender(){return this.gender;}
    public void setGender(int gender){this.gender = gender;}
    public int getAge(){return this.age;}
    public void setAge(int age){this.age = age;}
//    public Image image(){return this.image;}
//    public void set_image(Image image){this.image = image;}

}
