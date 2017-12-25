package usmanali.mobileworld.Activity_Files;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.add_posts_task;

public class add_posts_Activity extends AppCompatActivity {
EditText posttext;
    Button addpostbtn;
    String name,Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_posts_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Compose Post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        posttext = (EditText) findViewById(R.id.posttxt);
        addpostbtn = (Button) findViewById(R.id.post);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        name = prefs.getString("Name", "Shaban");
        Username = prefs.getString("Username", "shaban123");
        addpostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posttext.getText().toString().isEmpty()) {
                    Toast.makeText(add_posts_Activity.this, "Please Enter what You want to Post", Toast.LENGTH_LONG).show();
                } else {
                    new add_posts_task(add_posts_Activity.this).execute(name, Username, posttext.getText().toString(), String.valueOf(System.currentTimeMillis()));
                }
            }
        });
    }
}
