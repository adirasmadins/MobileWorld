package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.fetch_used_mobiles_task;

public class display_used_mobiles_Activity extends AppCompatActivity {
RecyclerView usedmobilelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_used_mobiles_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        usedmobilelist=(RecyclerView) findViewById(R.id.usedmobilelist);
        usedmobilelist.setLayoutManager(new GridLayoutManager(display_used_mobiles_Activity.this,2));
        new fetch_used_mobiles_task(usedmobilelist,display_used_mobiles_Activity.this).execute();
    }

}
