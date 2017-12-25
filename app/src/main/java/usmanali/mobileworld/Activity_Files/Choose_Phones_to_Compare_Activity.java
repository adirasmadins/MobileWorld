package usmanali.mobileworld.Activity_Files;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.fetch_mobile_for_comparing;

public class Choose_Phones_to_Compare_Activity extends AppCompatActivity {
Button comparebtn;
    Spinner choosefirstphone,choosesecondphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__phones_to__compare_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Choose Phones to Compare");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        comparebtn = (Button) findViewById(R.id.comparebtn);
        choosefirstphone=(Spinner) findViewById(R.id.firstphonedropdown);
        choosesecondphone=(Spinner) findViewById(R.id.secondphonedropdown);
        new fetch_mobile_for_comparing(choosefirstphone,choosesecondphone,Choose_Phones_to_Compare_Activity.this).execute();
        comparebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Choose_Phones_to_Compare_Activity.this,Compare_Phones_Activity.class);
                i.putExtra("firstphone",choosefirstphone.getSelectedItem().toString());
                i.putExtra("secondphone",choosesecondphone.getSelectedItem().toString());
                startActivity(i);
            }
        });
    }
}
