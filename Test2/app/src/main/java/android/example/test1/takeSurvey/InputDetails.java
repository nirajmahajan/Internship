package android.example.test1.takeSurvey;

import android.app.Activity;
import android.arch.persistence.room.ColumnInfo;
import android.content.DialogInterface;
import android.content.Intent;
import android.example.test1.R;
import android.example.test1.Utilities.SignInActivity;
import android.example.test1.database.AppDatabase;
import android.example.test1.database.Tables.Child;
import android.example.test1.database.Tables.Contact;
import android.example.test1.database.Tables.Parent;
import android.example.test1.database.Tables.Person;
import android.example.test1.database.Tables.SocialWorker;
import android.example.test1.database.Tables.Survey;
import android.example.test1.signIn.MainActivity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputDetails extends AppCompatActivity {

    private String childFirstName = "";
    private String childLastName = "";
    private String parentFirstName = "";
    private String parentLastName = "";
    private int childGender = -1;
    private int parentGender = -1;
    private int childAge = -1;
    private int parentAge = -1;
    private String parentOccupation = "";
//    private Image childImage = null;
//    private Image parentSignature = null;
    private long phoneNumber = -1;
    private long alternatePhoneNumber = -1;
    private String mailId = "";
    private String addressLine = "";
    private String district = "";
    private String state = "";
    private String city = "";
    private int pincode = -1;

    private SocialWorker socialWorker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_details);

        Intent intent = getIntent();
        String username = intent.getStringExtra("SOCIAL_WORKER_USERNAME");
        Toast.makeText(getApplicationContext(), username, 1).show();
        socialWorker = AppDatabase.getAppDatabase(getApplicationContext()).socialWorkerDao().findSocialWorkerByUsername(username).get(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_done) {

            childFirstName = ((EditText)findViewById(R.id.et_input_child_first_name)).getText().toString();
            childLastName = ((EditText)findViewById(R.id.et_input_child_last_name)).getText().toString();
            parentFirstName = ((EditText)findViewById(R.id.et_input_parent_first_name)).getText().toString();
            parentLastName = ((EditText)findViewById(R.id.et_input_parent_last_name)).getText().toString();
            int pGender = ((RadioGroup)findViewById(R.id.rg_input_parent_gender)).getCheckedRadioButtonId();
            if(pGender == R.id.radio_parent_female) {
                parentGender = 0;
            } else if (pGender == R.id.radio_parent_male){
                parentGender = 1;
            } else if (pGender == R.id.radio_parent_other) {
                parentGender = 2;
            }
            int cGender = ((RadioGroup)findViewById(R.id.rg_input_child_gender)).getCheckedRadioButtonId();
            if(cGender == R.id.radio_parent_female) {
                childGender = 0;
            } else if (cGender == R.id.radio_parent_male){
                childGender = 1;
            } else if (cGender == R.id.radio_parent_other) {
                childGender = 2;
            }

            childAge = Integer.parseInt(((EditText) findViewById(R.id.et_input_child_age)).getText().toString());
            parentAge = Integer.parseInt(((EditText) findViewById(R.id.et_input_parent_age)).getText().toString());

            phoneNumber = Long.parseLong(((EditText) findViewById(R.id.et_input_phone)).getText().toString());
            alternatePhoneNumber = Long.parseLong(((EditText) findViewById(R.id.et_input_alternate_phone)).getText().toString());

            mailId = ((EditText)findViewById(R.id.et_input_mail)).getText().toString();
            addressLine = ((EditText)findViewById(R.id.et_input_address_first)).getText().toString() + ((EditText)findViewById(R.id.et_input_address_second)).getText().toString();;
            district = ((EditText)findViewById(R.id.et_input_address_district)).getText().toString();
            city = ((EditText)findViewById(R.id.et_input_address_city)).getText().toString();
            state = ((EditText)findViewById(R.id.et_input_address_state)).getText().toString();
            pincode = Integer.parseInt(((EditText)findViewById(R.id.et_input_address_pincode)).getText().toString());

            if(!allFilled()) {
                Toast.makeText(getApplicationContext(), "Cannot Proceed Untill All Information has been filled", Toast.LENGTH_LONG).show();
            }
            else {
                Person pChild = new Person();
                Person pParent = new Person();
                Child child = new Child();
                Parent parent = new Parent();
                Survey survey = new Survey();
                Contact contact = new Contact();

                pChild.setFirstName(childFirstName);
                pChild.setLastName(childLastName);
                pChild.setAge(childAge);
                pChild.setGender(childGender);

                pParent.setFirstName(parentFirstName);
                pParent.setLastName(parentLastName);
                pParent.setAge(parentAge);
                pParent.setGender(parentGender);

                child.setPersonId(pChild.getId());
                child.setParentId(parent.getId());
                child.setSurveyId(survey.getId());

                parent.setChildId(child.getId());
                parent.setPersonId(pParent.getId());
                parent.setEmployment(parentOccupation);
                parent.setContactId(contact.getId());

                survey.setChildId(child.getId());
                survey.setSocialWorkerUsername(socialWorker.getUsername());
//                survey.setSurveyData(null);

                contact.setAddress_line(addressLine);
                contact.setPhoneNumber(phoneNumber);
                contact.setAlternatePhoneNumber(alternatePhoneNumber);
                contact.setCity(city);
                contact.setDistrict(district);
                contact.setState(state);
                contact.setMailId(mailId);
                contact.setPincode(pincode);

//                Integer[] initialSurveys = socialWorker.getSurveyIds();
//                ArrayList<Integer> temp = new ArrayList<>(Arrays.asList(initialSurveys));
//
//                temp.add(survey.getId());
//                socialWorker.setSurveyIds(temp.toArray(new Integer[temp.size()]));


//                AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
//                db.personDao().Insert(pParent, pChild);
//                db.surveyDao().Insert(survey);
//                db.contactDao().Insert(contact);
//                db.childDao().Insert(child);
//                db.parentDao().Insert(parent);
//                db.socialWorkerDao().Update(socialWorker);

                Toast.makeText(getApplicationContext(), "Data Added to database", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), TakeSurvey.class);
//                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean allFilled() {
        return !((childFirstName.equals("")) ||
                (childLastName.equals("")) ||
                (parentFirstName.equals("")) ||
                (parentLastName.equals("")) ||
                (childGender == -1) ||
                (parentGender == -1) ||
                (childAge == -1) ||
                (parentAge == -1) ||
                (phoneNumber == -1) ||
                addressLine.equals("") ||
                district.equals("") ||
                state.equals("") ||
                city.equals("") ||
                (pincode == -1));
    }
}
