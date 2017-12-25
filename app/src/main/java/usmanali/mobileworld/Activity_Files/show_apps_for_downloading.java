package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.fetch_apps_task;

public class show_apps_for_downloading extends AppCompatActivity {
RecyclerView applist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_apps_for_downloading);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        applist=(RecyclerView) findViewById(R.id.appslist);
        applist.setLayoutManager(new GridLayoutManager(show_apps_for_downloading.this,2));
        new fetch_apps_task(show_apps_for_downloading.this,applist).execute();
    }
}
