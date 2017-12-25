package usmanali.mobileworld.Activity_Files;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.fetch_discussion_forum_posts_task;

public class Discussion_forum extends AppCompatActivity {
RecyclerView postslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Discussion Forum");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        postslist=(RecyclerView) findViewById(R.id.postslist);
        postslist.setLayoutManager(new LinearLayoutManager(Discussion_forum.this));
        new fetch_discussion_forum_posts_task(Discussion_forum.this,postslist).execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_post) {
        startActivity(new Intent(Discussion_forum.this,add_posts_Activity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
