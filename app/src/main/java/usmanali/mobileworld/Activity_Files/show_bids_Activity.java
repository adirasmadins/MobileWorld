package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.fetch_bids_by_id_task;

public class show_bids_Activity extends AppCompatActivity {
RecyclerView showbids;
    String mobile_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bids);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bids");
        showbids=(RecyclerView) findViewById(R.id.bidslist);

        showbids.setLayoutManager(new LinearLayoutManager(show_bids_Activity.this));
        mobile_id=String.valueOf(getIntent().getIntExtra("mobileid",2));
        new fetch_bids_by_id_task(show_bids_Activity.this,showbids).execute(mobile_id);
    }

}
