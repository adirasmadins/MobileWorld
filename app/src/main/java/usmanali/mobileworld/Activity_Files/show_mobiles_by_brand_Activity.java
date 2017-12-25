package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.fetch_mobiles_by_brand;

public class show_mobiles_by_brand_Activity extends AppCompatActivity {
RecyclerView mobilesbybrandlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mobiles_by_brand);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("brand")+" Phones");
        mobilesbybrandlist=(RecyclerView) findViewById(R.id.mobilesbybrandlist);
        mobilesbybrandlist.setLayoutManager(new GridLayoutManager(show_mobiles_by_brand_Activity.this,2));
        new fetch_mobiles_by_brand(mobilesbybrandlist,show_mobiles_by_brand_Activity.this).execute(getIntent().getStringExtra("brand"));
    }

}
