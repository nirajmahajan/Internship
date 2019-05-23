package android.example.test1.database.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.media.Image;

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

    @ColumnInfo(name = "image")
    Image image = null;

    public void makePerson(String firstName, String lastName, String fatherName, String motherName, Contact contact, int gender, int age, Image image, Image signature){
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.image = image;
        }

    public int getId() {return this.id;}
    public void setId (int id) {this.id = id;}
    public String get_firstName() {return this.firstName;}
    public void set_firstName(String firstName) {this.firstName = firstName;}
    public String get_lastName() {return this.lastName;}
    public void set_lastName(String lastName) {this.lastName = lastName;}
    public int get_gender(){return this.gender;}
    public void set_gender(int gender){this.gender = gender;}
    public int get_age(){return this.age;}
    public void set_age(int age){this.age = age;}
    public Image image(){return this.image;}
    public void set_image(Image image){this.image = image;}

}
