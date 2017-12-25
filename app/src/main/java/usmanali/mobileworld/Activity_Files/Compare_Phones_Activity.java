package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.get_mobile_by_name_task;

public class Compare_Phones_Activity extends AppCompatActivity {
    ImageView mobileimage;
    ImageView mobileimage2;
    TextView batterycapacity;
    TextView batterycapacity2;
    TextView priceofmobile;
    TextView priceofmobile2;
    TextView screensize;
    TextView screensize2;
    TextView ramofmobile;
    TextView ramofmobile2;
    TextView resolutionofmobile;
    TextView resolutionofmobile2;
    TextView storageofmobile;
    TextView storageofmobile2;
    TextView simtypeofmobile;
    TextView simtypeofmobile2;
    TextView processorofmobile;
    TextView processorofmobile2;
    TextView operatingsystemofmobile;
    TextView operatingsystemofmobile2;
    TextView frontcameraofmobile;
    TextView frontcameraofmobile2;
    TextView backcameraofmobile;
    TextView backcameraofmobile2;
    TextView brandofmobile;
    TextView brandofmobile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare__phones_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Compare Phones");
        ramofmobile =(TextView) findViewById(R.id.ramfirstphone);
        ramofmobile2=(TextView) findViewById(R.id.ramsecondphone);
        priceofmobile=(TextView) findViewById(R.id.pricefirstphone);
        priceofmobile2=(TextView) findViewById(R.id.pricesecondphone);
        resolutionofmobile=(TextView) findViewById(R.id.resolutionfirstphone);
        resolutionofmobile2=(TextView) findViewById(R.id.resolutionsecondphone);
        storageofmobile=(TextView) findViewById(R.id.storagefirstphone);
        storageofmobile2=(TextView) findViewById(R.id.storagesecondphone);
        simtypeofmobile=(TextView) findViewById(R.id.simtypefirstphone);
        simtypeofmobile2=(TextView) findViewById(R.id.simtypesecondphone);
        operatingsystemofmobile=(TextView) findViewById(R.id.osfirstphone);
        operatingsystemofmobile2=(TextView) findViewById(R.id.ossecondphone);
        frontcameraofmobile=(TextView) findViewById(R.id.frontcamerafirstphone);
        frontcameraofmobile2=(TextView) findViewById(R.id.frontcamerasecondphone);
        backcameraofmobile=(TextView) findViewById(R.id.rearcamerafirstphone);
        backcameraofmobile2=(TextView) findViewById(R.id.rearcamerasecondphone);
        mobileimage=(ImageView) findViewById(R.id.imageview_product_1);
        mobileimage2=(ImageView) findViewById(R.id.imageview_product_2);
        batterycapacity=(TextView) findViewById(R.id.textview_value_left);
        batterycapacity2=(TextView) findViewById(R.id.textview_value_right);
        screensize=(TextView) findViewById(R.id.screensize);
        screensize2=(TextView) findViewById(R.id.screensizesecondphone);
        processorofmobile=(TextView) findViewById(R.id.processorfirstphone);
        processorofmobile2=(TextView) findViewById(R.id.processorsecondphone);
        brandofmobile=(TextView) findViewById(R.id.brandfirstphone);
        brandofmobile2=(TextView) findViewById(R.id.brandsecondphone);
        String name= getIntent().getStringExtra("firstphone");
        String name2=getIntent().getStringExtra("secondphone");
        new get_mobile_by_name_task(Compare_Phones_Activity.this,mobileimage,priceofmobile,ramofmobile,resolutionofmobile,storageofmobile,simtypeofmobile,operatingsystemofmobile,frontcameraofmobile,backcameraofmobile,batterycapacity,screensize,processorofmobile,brandofmobile).execute(name);
        new get_mobile_by_name_task(Compare_Phones_Activity.this,mobileimage2,priceofmobile2,ramofmobile2,resolutionofmobile2,storageofmobile2,simtypeofmobile2,operatingsystemofmobile2,frontcameraofmobile2,backcameraofmobile2,batterycapacity2,screensize2,processorofmobile2,brandofmobile2).execute(name2);
    }

}
