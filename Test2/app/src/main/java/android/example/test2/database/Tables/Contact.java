package android.example.test2.database.Tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Contacts")
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "phoneNumber")
    private long phoneNumber = -1;

    @ColumnInfo(name = "alternatePhoneNumber")
    private long alternatePhoneNumber;

    @ColumnInfo(name = "mailId")
    private String mailId = null;

    @ColumnInfo(name = "address_line")
    private String address_line = null;

    @ColumnInfo(name = "district")
    private String district = null;

    @ColumnInfo(name = "city")
    private String city = null;

    @ColumnInfo(name = "state")
    private String state = null;

    @ColumnInfo(name = "pincode")
    private int pincode = -1;

    // get and set functions
    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}
    public String getAddress_line(){return this.address_line;}
    public void setAddress_line(String address_line){this.address_line = address_line;}
    public String getDistrict() {return this.district;}
    public void setDistrict(String district) {this.district = district;}
    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}
    public String getState() {return this.state;}
    public void setState(String state) {this.state = state;}
    public int getPincode() {return this.pincode;}
    public void setPincode(int pincode) {this.pincode = pincode;}
    public long getPhoneNumber() {return this.phoneNumber;}
    public void setPhoneNumber(long phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setAlternatePhoneNumber(long phoneNumber) {this.alternatePhoneNumber = phoneNumber;}
    public long getAlternatePhoneNumber() {return this.alternatePhoneNumber;}
    public String getMailId() {return this.mailId;}
    public void setMailId(String mailId) {this.mailId = mailId;}

    // returns complete address
    public String completeAddress() {
        return address_line + ", " + district + ", " + city + "-" + String.valueOf(pincode) + ".\nContact Details: " + phoneNumber + " / " + alternatePhoneNumber + ".";
    }
}
