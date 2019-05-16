package android.example.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Validate();
    }



    private void Validate(){
        Button b_login = (Button) findViewById(R.id.b_login);
        final EditText Et_usern = (EditText) findViewById(R.id.et_username);
        final EditText Et_passwd = (EditText) findViewById(R.id.et_passwd);

        b_login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Username = Et_usern.getText().toString();
                        String Passwd = Et_passwd.getText().toString();

                        if(/*enter validation here*/true) {
                            Intent Allow = new Intent(MainActivity.this, Welcome.class);
                            Allow.putExtra("NAME", Username);
                            startActivity(Allow);
                        }
                        else{
                            Toast toast = Toast.makeText(getApplicationContext(), "Incorrect Password or username",Toast.LENGTH_LONG);
                            toast.show();
                            Et_passwd.setText("");
                            Et_usern.setText("");
                        }
                    }
                }
        );
    }
}
