package android.example.test1.database;

import android.media.Image;

public class Person extends Contact{
    private String firstName = null;
    private String lastName = null;
    private String fatherName = null;
    private String motherName = null;
    private Contact contact = null;
    private int gender = -1;
    private int age = -1;
    Image image = null;
    Image signature = null;

    public Person(String firstName, String lastName, String fatherName, String motherName, Contact contact, int gender, int age, Image image, Image signature){
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.contact = contact;
        this.gender = gender;
        this.age = age;
        this.image = image;
        this.signature = signature;
    }

    public String get_firstName() {return this.firstName;}
    public void set_firstName(String firstName) {this.firstName = firstName;}
    public String get_lastName() {return this.lastName;}
    public void set_lastName(String lastName) {this.lastName = lastName;}
    public String get_fatherName() {return this.fatherName;}
    public void set_fatherName(String fatherName) {this.fatherName = fatherName;}
    public String get_motherName() {return this.motherName;}
    public void set_motherName(String motherName) {this.motherName = motherName;}
    public Contact get_contact(){return this.contact;}
    public void set_contact(Contact contact){this.contact = contact;}
    public int get_gender(){return this.gender;}
    public void set_gender(int gender){this.gender = gender;}
    public int get_age(){return this.age;}
    public void set_age(int age){this.age = age;}
    public Image image(){return this.image;}
    public void set_image(Image image){this.image = image;}
    public Image signature(){return this.signature;}
    public void set_signature(Image signature){this.signature = signature;}


}
