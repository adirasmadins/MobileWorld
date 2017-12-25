package usmanali.mobileworld.Activity_Files;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.add_comments_to_posts;
import usmanali.mobileworld.Asynctasks.fetch_comments_for_posts_task;

public class comments_Activity extends AppCompatActivity {
ImageView placecomment;
    EditText commenttxt;
    RecyclerView commentslist;
    String name,Username;
    int post_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        commentslist=(RecyclerView) findViewById(R.id.commentlist);
        commentslist.setLayoutManager(new LinearLayoutManager(comments_Activity.this));
        placecomment=(ImageView) findViewById(R.id.send);
        commenttxt=(EditText) findViewById(R.id.commenttext);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        name = prefs.getString("Name", "Shaban");
        Username = prefs.getString("Username", "shaban123");
        post_id=getIntent().getIntExtra("post_id",2);
        new fetch_comments_for_posts_task(commentslist,comments_Activity.this).execute(String.valueOf(post_id));
        placecomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new add_comments_to_posts(comments_Activity.this).execute(name,Username,commenttxt.getText().toString(),String.valueOf(post_id),String.valueOf(System.currentTimeMillis()));
            }
        });
    }

}
