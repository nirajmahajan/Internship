package android.example.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Welcome();
    }

    private void Welcome() {
        TextView tv = findViewById(R.id.tv_welcome);
        Intent in = getIntent();
        String Name = in.getStringExtra("NAME");
        tv.setText(getResources().getString(R.string.welcome, Name));
    }
}
