package com.reading.startrecovery;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.reading.startrecovery.database.AppDatabase;


public class MainActivity extends AppCompatActivity {
    AsyncCopy asyncCopy;
    boolean backupExists;
    TextView history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        asyncCopy = new AsyncCopy(getApplicationContext(), getBaseContext(), this);
        AppDatabase db = AppDatabase.getAppDatabase(getApplicationContext());
        backupExists = AppDatabase.backupExists();
        history = findViewById(R.id.History);

        if (backupExists){
            String timestamp = AppDatabase.getTimeStamp();
            history.setText("Last Database Backup Taken On " + timestamp);
        }
        else {
            history.setText("No Backup taken");
        }
    }

    public void restoreDB(View view) {
        if (backupExists) {
            asyncCopy.execute("restore");
            asyncCopy = new AsyncCopy(getApplicationContext(), getBaseContext(), this);
        } else {
            Toast.makeText(getApplicationContext(), "Backup Does not Exist", Toast.LENGTH_LONG).show();
        }
    }
    //exporting database
    public void backupDB(View view) {
        asyncCopy.execute("backup");
        asyncCopy = new AsyncCopy(getApplicationContext(), getBaseContext(), this);
    }
}
