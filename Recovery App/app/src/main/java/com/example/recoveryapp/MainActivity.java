package com.example.recoveryapp;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "local-database")
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                .allowMainThreadQueries()
                .build();
    }

    public void importDB(View view) {
        // TODO Auto-generated method stub

        try {

//                if (sd.canWrite()) {
            String  currentDBPath= "/data/user/0/com.example.myfirstapp/";
            String backupDBPath  = "/data/user/0/com.example.recoveryapp/databases/";
//            String currentDBPath = Environment.getExternalStorageDirectory().toString() + "/Dbbbbb/";
//            String backupDBPath = Environment.getExternalStorageDirectory().toString() + "/Dbbackup/";
            File  backupDB= new File(backupDBPath);
            File currentDB  = new File(currentDBPath);
            Util.copyDirectory(backupDB, currentDB);
            Toast.makeText(getBaseContext(), "Import Successful", Toast.LENGTH_LONG).show();

//                }
        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
    //exporting database
    public void exportDB(View view) {
        // TODO Auto-generated method stub

        try {
// a           AppDatabase.getAppDatabase(getApplicationContext()).close();
// a               if (sd.canWrite()) {
            String  currentDBPath= "/data/user/0/com.example.myfirstapp/";
            String backupDBPath  = "/data/user/0/com.example.recoveryapp/databases/";
//            String backupDBPath = Environment.getExternalStorageDirectory().toString() + "/DbbackupOLD";
            File currentDB = new File(currentDBPath);
            File backupDB = new File(backupDBPath);
            Toast.makeText(getBaseContext(), "Export Successful", Toast.LENGTH_LONG).show();
            Util.copyDirectory(currentDB, backupDB);
//                }

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG)
                    .show();

        }
    }
}
