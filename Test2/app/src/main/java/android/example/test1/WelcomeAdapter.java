package android.example.test1;

import android.app.Activity;
import android.example.test1.database.AppDatabase;
import android.example.test1.database.Tables.Child;
import android.example.test1.database.Tables.Person;
import android.example.test1.database.Tables.Survey;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeAdapter extends ArrayAdapter {
    private final Activity context;
    ArrayList<Integer> names;

    WelcomeAdapter(Activity context, ArrayList<Integer> names) {
        super(context, R.layout.welcome_object, names);
        this.names = names;
        this.context = context;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.welcome_object, null, true);

        TextView tv_id = rowView.findViewById(R.id.tv_welcome_object_id);
        TextView tv_name = rowView.findViewById(R.id.tv_welcome_object_name);
        TextView tv_age = rowView.findViewById(R.id.tv_welcome_object_age);
        TextView tv_gender = rowView.findViewById(R.id.tv_welcome_object_gender);
        ImageView imageView = rowView.findViewById(R.id.iv_welcome_pic);
        ImageButton imageButton = rowView.findViewById(R.id.imageView);

        int SurveyId = names.get(position);
        final Survey survey = AppDatabase.getAppDatabase(context).surveyDao().findSurveyById(SurveyId);

        int childID = survey.getChildId();
        Child child = AppDatabase.getAppDatabase(context).childDao().findChildById(childID);
        final Person childPerson = AppDatabase.getAppDatabase(context).personDao().findPersonById(child.getPersonId());
        tv_name.setText("Name : " + childPerson.getFirstName() + " " + childPerson.getLastName());
        tv_age.setText("Age : " + String.valueOf(childPerson.getAge()));
        String sex = new String();
        if (childPerson.getGender() == 1) {
            sex = "M";
        }else if (childPerson.getGender() == 0) {
            sex = "F";
        }else if (childPerson.getGender() == 2) {
            sex = "Other";
        }

        tv_gender.setText("Gender : " + sex);
        tv_id.setText(String.valueOf(position + 1));
//        Toast.makeText(context, String.valueOf(null == child.getImage()), 10).show();
        setPic(imageView, child.getImage());

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase.purgeSurveyById(survey.getId());
                notifyDataSetChanged();
            }
        });
        return rowView;
    }

    private void setPic(ImageView imageView, byte[] BLOB) {
        // Get the dimensions of the View
//        int targetW = imageView.getWidth();
//        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(BLOB, 0, BLOB.length, bmOptions);
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
//        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeByteArray(BLOB, 0, BLOB.length, bmOptions);
        imageView.setImageBitmap(bitmap);
    }
}
