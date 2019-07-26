package com.reading.startrecovery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.os.ConditionVariable;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.reading.startrecovery.database.AppDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

public class AsyncCopy extends AsyncTask<String, String, String> {

    // *TODO* Add your own package name
    private String packageName = "com.reading.start";


    private Context context;
    private Context basecontext;
    private Activity activity;
    private ProgressDialog pDialog;
    private String backupPath = "/data/user/0/com.reading.startrecovery/com.reading.start/";
    private String appPath = "/data/user/0/" + packageName + "/";
//    String appPath = Environment.getExternalStorageDirectory().toString() + "/Dbbbbb/";
//    String backupPath = Environment.getExternalStorageDirectory().toString() + "/Dbbvvackup/";


    public AsyncCopy(Context context, Context basecontext, Activity activity){
        this.context = context;
        this.activity = activity;
        this.basecontext = basecontext;
    }

    /**
     * Before starting background thread
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Copying... Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String  doInBackground(String... operationMode) {
        String mode = operationMode[0];
        if (mode.equals("backup")) {
            return backup();
        } else if (mode.equals("restore")) {
            return restore();
        } else {
            return "failure";
        }
    }

    /**
     * After completing background task
     * **/
    @Override
    protected void onPostExecute(String result) {
        pDialog.setMessage(result + "! Finishing ...");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
            }
        }, 3000);
        if(result.endsWith("Successful")){
            Date currentTime = Calendar.getInstance().getTime();
            String timestamp = currentTime.toString();
            AppDatabase.addTimeStamp(timestamp);
            TextView tv = activity.findViewById(R.id.History);
            tv.setText("Last Database Backup Taken On " + timestamp);
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        String log = values[0];
        pDialog.setMessage(log);
    }

    private String restore(){
        try {
            File backup = new File(backupPath);
            File current = new File(appPath);
            copyDirectory(backup, current);
            return "Data Restored Successful";
        } catch (Exception e) {
            return e.toString();
        }
    }

    private String backup() {
        try {
            File current = new File(appPath);
            File backup = new File(backupPath);
            copyDirectory(current, backup);
            return "Data Back Up Successful";
        } catch (Exception e) {
            return e.toString();
        }
    }

    private void copyDirectory(File sourceLocation, File targetLocation)
            throws IOException {
        if (!(sourceLocation.getName().endsWith(".realm.lock") ||
                sourceLocation.getName().endsWith(".realm.note") ||
                (sourceLocation.getName().endsWith(".realm.management")))) {
            if (sourceLocation.isDirectory()) {
                if (!targetLocation.exists()) {
                    targetLocation.mkdirs();
                } else {
                    deleteDirectory(targetLocation);
                    targetLocation.mkdirs();
                }


                String[] children = sourceLocation.list();
                for (int i = 0; i < children.length; i++) {
                    copyDirectory(new File(sourceLocation, children[i]), new File(
                            targetLocation, children[i]));
                }
            } else {
                copyFile(sourceLocation, targetLocation);
            }
        }
    }

    /**
     * @param sourceLocation
     * @param targetLocation
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void copyFile(File sourceLocation, File targetLocation) {
        if (!(sourceLocation.getName().equals("access_control.new_commit.cv") ||
                sourceLocation.getName().equals("access_control.control.mx") ||
                sourceLocation.getName().equals("access_control.pick_writer.cv") ||
                sourceLocation.getName().equals("access_control.write.mx") ||
                sourceLocation.getName().endsWith("realm.note"))){
            try {
                publishProgress(sourceLocation.getName());
                InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                publishProgress(e.toString());
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 3000);
            } catch (IOException e) {
                e.printStackTrace();
                publishProgress(e.toString());
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 3000);
            }
        }
    }

    static public boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
}

