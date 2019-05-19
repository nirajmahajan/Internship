package android.example.test1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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


import static android.support.v4.content.ContextCompat.startActivity;

public class SignInActivity extends AsyncTask <String, Void, String[]> {
    private Context ctx;
    public SignInActivity(Context context) {
        ctx = context;
    }

    public String ip = "192.168.43.148";

    protected void onPreExecute(){
    }

    @Override
    protected void onPostExecute(String[]  o) {
        String name = o[0];
        String username = o[1];

        if (o[0].equals("Exception: ")) {
            Toast.makeText(ctx, o[0] + o[1], Toast.LENGTH_LONG);
        } else if (o[0].equals("DELETED")) {
            Toast.makeText(ctx, "User '" + username + "' has been deleted", Toast.LENGTH_SHORT);
        } else if(!name.equals("NOT_FOUND")) {
            Intent allow = new Intent(ctx, Welcome.class);
            allow.putExtra("NAME", name);
            allow.putExtra("USERNAME", username);
            startActivity(ctx, allow, Bundle.EMPTY);
        } else {
            Toast.makeText(ctx, "Invalid Username or Password", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected String[] doInBackground(String... args) {
        try {
            String username = args[0];
            String password = args[1];
            String mode = args[2];
            String link = null;

            if (mode.equals("login")) {
                link = "http://" + ip + "/login.php?username=" + username + "&password=" + password;
            } else if (mode.equals("delete")){
                link = "http://" + ip + "/delete.php?username=" + username;
            }

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
            String[] ans = new String[2];
            ans[0] = result;
            ans[1] = username;
            return ans;
        } catch (UnsupportedEncodingException e) {
            String[] ans = {"Exception: " , e.getMessage()};
            return ans;
        } catch (ProtocolException e) {
            String[] ans = {"Exception: " , e.getMessage()};
            return ans;
        } catch (MalformedURLException e) {
            String[] ans = {"Exception: " , e.getMessage()};
            return ans;
        } catch (IOException e) {
            String[] ans = {"Exception: " , e.getMessage()};
            return ans;
        }

    }
}
