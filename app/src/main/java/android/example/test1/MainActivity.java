package android.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private EditText Et_usern;
    private EditText Et_passwd;
    private App app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (App) getApplication();
        Et_usern = (EditText) findViewById(R.id.et_username);
        Et_passwd = (EditText) findViewById(R.id.et_passwd);
    }

    public void login(View view){
        String username = Et_usern.getText().toString();
        String password = Et_passwd.getText().toString();
        if(username.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
        }else if(password.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
        } else if (username.equals(getResources().getString(R.string.ip_override_username)) && password.equals(getResources().getString(R.string.ip_override_password))) {
            changeIP();
        } else {
            new SignInActivity(this).execute(username, password, "login", app.getIP());
        }
        Et_passwd.setText("");
        Et_usern.setText("");
    }

    public void verifyAddRequest(View view) {
        newEntry(this);
    }

    public void addUser(String[] arr) {
        if (! (arr[0].equals("#$") && arr[1].equals("#$$") && arr[2].equals("#$$$"))) {
            InsertData(arr);
        }
    }

    public void InsertData(final String... arr) {
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(arr[0], arr[1], arr[2]);
    }


    private void newEntry(final Activity context) {
        View ll = getLayoutInflater().inflate(R.layout.new_user, null, true);
        final EditText et_GetUsername = ll.findViewById(R.id.new_user);
        final EditText et_GetPassword = ll.findViewById(R.id.new_pass1);
        final EditText et_GetPasswordAgain = ll.findViewById(R.id.new_pass2);
        final EditText et_GetName = ll.findViewById(R.id.new_name);

        final String[] arr;
        arr = new String[]{"#$", "#$$", "#$$$"};
        final String[] username = new String[1];
        final String[] password1 = new String[1];
        final String[] password2 = new String[1];
        final String[] name = new String[1];
        final String[] finalArr = arr;
        new AlertDialog.Builder(context)
                .setTitle("Register a new User")
                .setMessage("Please Enter the validation details")
                .setView(ll)
                .setCancelable(false)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        username[0] = et_GetUsername.getText().toString();
                        password1[0] = et_GetPassword.getText().toString();
                        password2[0] = et_GetPasswordAgain.getText().toString();
                        name[0] = et_GetName.getText().toString();

                        if (username[0].equals("")) {
                            Toast.makeText(context, "Please Enter a Valid Username!", Toast.LENGTH_SHORT).show();
                        } else if (password1[0].equals("") || password2[0].equals("")) {
                            Toast.makeText(context, "Please Enter a Valid Password!", Toast.LENGTH_SHORT).show();
                        } else if (!password1[0].equals(password2[0])) {
                            Toast.makeText(context, "Passwords do not Match!", Toast.LENGTH_SHORT).show();
                        } else if (name[0].equals("")) {
                            Toast.makeText(context, "Please Enter a Valid Name", Toast.LENGTH_SHORT).show();
                        } else {
                            finalArr[0] = username[0];
                            finalArr[1] = password1[0];
                            finalArr[2] = name[0];
                            addUser(finalArr);
                        }

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                String username = params[0];
                String password = params[1];
                String name = params[2];

                String link = "http://" + app.getIP() + "/add.php?username=" + username + "&password=" + password + "&name=" + name;
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (UnsupportedEncodingException e) {
                return "Exception";
            } catch (ProtocolException e) {
                return "Exception";
            } catch (MalformedURLException e) {
                return "Exception";
            } catch (IOException e) {
                return "Exception";
            }
        }

            @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }

    private void changeIP() {
        final EditText et_getIP = new EditText(this);
        final Activity context = this;
        et_getIP.setText(app.getIP());

        new AlertDialog.Builder(context)
                .setTitle("Change the IP")
                .setMessage("Please Enter a New IP")
                .setView(et_getIP)
                .setCancelable(false)
                .setNeutralButton("Get IP Log", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent show_ip_log = new Intent(getApplicationContext(), Ip_Log.class);
                        startActivity(show_ip_log);
                    }
                })
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newIP = et_getIP.getText().toString();
                        app.setIp(newIP);
                        Toast.makeText(getApplicationContext(), "IP changed succesfully to "+newIP, Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .show();
    }
}

