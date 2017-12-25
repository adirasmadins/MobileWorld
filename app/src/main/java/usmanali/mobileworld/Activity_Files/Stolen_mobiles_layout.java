package usmanali.mobileworld.Activity_Files;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.search_stolen_mobile_by_imei_task;

public class Stolen_mobiles_layout extends AppCompatActivity {
Button reportbtn,searchbtn;
    EditText imeitxt;
    CardView detailscard;
    TextView imeinumber,stolendate,reportername,mobilename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stolen_mobiles_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Mobile By IMEI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        reportbtn=(Button) findViewById(R.id.reportbtn);
        searchbtn=(Button) findViewById(R.id.searchimeibtn);
        imeitxt=(EditText)  findViewById(R.id.imeinumbertxt);
        imeinumber=(TextView) findViewById(R.id.imeinumber);
        stolendate=(TextView) findViewById(R.id.stolendate);
        reportername=(TextView) findViewById(R.id.reportername);
        mobilename=(TextView) findViewById(R.id.mobilename);
        detailscard=(CardView) findViewById(R.id.detailscard);
        detailscard.setVisibility(View.GONE);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imeitxt.getText().toString().isEmpty()){
                    Toast.makeText(Stolen_mobiles_layout.this,"Please Enter Imei",Toast.LENGTH_LONG).show();
                }else{
                    new search_stolen_mobile_by_imei_task(reportername,mobilename,imeinumber,stolendate,Stolen_mobiles_layout.this,detailscard).execute(imeitxt.getText().toString());
                }
            }
        });
        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Stolen_mobiles_layout.this,Report_Stolen_Mobiles.class));
            }
        });
    }

}
