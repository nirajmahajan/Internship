package android.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.example.test1.Utilities.App;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Ip_Log extends AppCompatActivity {

    private TextView ip_log_view;
    ArrayList<String> log;
    App app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip__log);
        ip_log_view = findViewById(R.id.tv_ip_log);
        app = (App) getApplication();
        log = app.getIpLog();
        populate();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ip_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_ip_update) {
            final EditText et_getIP = new EditText(this);
            final Activity context = this;
            et_getIP.setText(app.getIP());

            new AlertDialog.Builder(context)
                    .setTitle("Change the IP")
                    .setMessage("Please Enter a New IP")
                    .setView(et_getIP)
                    .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String newIP = et_getIP.getText().toString();
                            app.setIp(newIP);
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    })
                    .setCancelable(false)
                    .show();
        }
        else if (id == R.id.menu_home){
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(home);
            finish();
        }
        else if (id == R.id.menu_ip_reset) {
            app.setIp(app.getIpLog().get(0));
            Toast.makeText(getApplicationContext(), "IP Reset to Default Value", Toast.LENGTH_SHORT).show();
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(home);
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    private void populate() {
        ip_log_view.append(log.get(0) + "    ::   DEFAULT\n");
        for (int i = 1; i < log.size(); i++) {
            ip_log_view.append(log.get(i) + "    ::    CHANGE " + String.valueOf(i));
            ip_log_view.append("\n");
        }
    }
}
