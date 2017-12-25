package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.Smartphone_videos_task;

public class Smartphone_Videos_Activity extends AppCompatActivity {
    RecyclerView videolist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartphone__videos_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Smartphone Videos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        videolist=(RecyclerView) findViewById(R.id.videolist);
        videolist.setLayoutManager(new LinearLayoutManager(Smartphone_Videos_Activity.this));
        new Smartphone_videos_task(videolist,Smartphone_Videos_Activity.this).execute();
    }

}
