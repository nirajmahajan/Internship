package android.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.example.test1.Utilities.App;
import android.example.test1.Utilities.SignInActivity;
import android.example.test1.database.AppDatabase;
import android.example.test1.database.Tables.SocialWorker;
import android.example.test1.signIn.MainActivity;
import android.example.test1.takeSurvey.InputDetails;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Welcome extends AppCompatActivity {
    String name;
    String username;
    App app;
    SocialWorker socialWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent in = getIntent();
        name = in.getStringExtra("NAME");
        username = in.getStringExtra("USERNAME");

        Welcome();
        app = (App) getApplication();
        List<SocialWorker> list = AppDatabase.getAppDatabase(getApplicationContext()).socialWorkerDao().findSocialWorkerByUsername(username);


        if (list.size() == 0) {
            socialWorker = new SocialWorker();
            socialWorker.setUsername(username);
            socialWorker.setName(name);
            AppDatabase.getAppDatabase(getApplicationContext()).socialWorkerDao().Insert(socialWorker);
        }
        else if (list.size() == 1) {
            socialWorker = list.get(0);
        }
        else {
            Toast.makeText(getApplicationContext(), "Number of social workers with the same username are " + String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Cannot go Back At this stage.\nPlease Log Out", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_delete) {
            final Activity context = this;
            new AlertDialog.Builder(context)
                    .setTitle("Are you sure you want to delete your account?")
                    .setMessage("This action will delete all your user data and cannot be undone.")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            new SignInActivity(context).execute(username, "NOT_NEEDED", "delete", app.getIP());

                            Intent intent = new Intent(context, MainActivity.class);
                            dialog.cancel();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .show();
        }
        else if (id == R.id.menu_signOut) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else  if (id == R.id.menu_newSurvey) {
            Intent intent = new Intent(getApplicationContext(), InputDetails.class);
            intent.putExtra("SOCIAL_WORKER_USERNAME", username);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



        private void Welcome() {
        TextView tv = findViewById(R.id.tv_welcome);
        tv.setText(getResources().getString(R.string.welcome, name));
    }
}
