package usmanali.mobileworld.Activity_Files;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.fetch_maximium_bid_task;
import usmanali.mobileworld.Asynctasks.place_bid_task;

public class Bidding_Activity extends AppCompatActivity {
    Button placebid;
    EditText bidingamount;
    RecyclerView bidderslist;
    String name,username,mobilename,mobileid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        placebid=(Button) findViewById(R.id.placebid);
        bidingamount=(EditText) findViewById(R.id.bidingamount);
        bidderslist=(RecyclerView) findViewById(R.id.bidderslist);
        bidderslist.setLayoutManager(new LinearLayoutManager(Bidding_Activity.this));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
       name= prefs.getString("Name","Shaban");
       username= prefs.getString("Username","shaban123");
       mobilename=getIntent().getStringExtra("mobilename");
        mobileid=String.valueOf(getIntent().getIntExtra("mobileid",1));
        new fetch_maximium_bid_task(bidderslist,Bidding_Activity.this).execute(mobileid);
        placebid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bidingamount.getText().toString().isEmpty()) {
                    Toast.makeText(Bidding_Activity.this,"Please Enter Bidding Amount",Toast.LENGTH_LONG).show();
                }else if (Integer.parseInt(bidingamount.getText().toString())<getIntent().getIntExtra("mobileprice",0)){
                    Toast.makeText(Bidding_Activity.this,"Bidding Amount Should be greater then phone Price",Toast.LENGTH_LONG).show();
                }else{
                    new place_bid_task(Bidding_Activity.this).execute(name, username, mobilename, mobileid, bidingamount.getText().toString(), String.valueOf(System.currentTimeMillis()));
                }
            }
        });
    }
}
