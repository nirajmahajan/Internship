package android.example.test1.database;

public class Contact {
    // ALL Except mail id will be mandatory
    private long phoneNumber = -1;
    private String mailId = null;
    private String address_line = null;
    private String district = null;
    private String city = null;
    private String state = null;
    private int pincode = -1;

    // default constructor
    public Contact() {};
    public Contact(String address_line, String district, String city, String state, int pincode, long phoneNumber, String mailId) {
        this.address_line = address_line;
        this.district = district;

        this.state = state;
        this.phoneNumber = phoneNumber;
        this.pincode = pincode;
        this.mailId = mailId;
    }

    // get and set functions
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
    public String getMailId() {return this.mailId;}
    public void setMailId(String mailId) {this.mailId = mailId;}

    // returns complete address
}
