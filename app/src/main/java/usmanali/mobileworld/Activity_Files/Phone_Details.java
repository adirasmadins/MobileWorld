package usmanali.mobileworld.Activity_Files;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.dbhelper;

public class Phone_Details extends AppCompatActivity {
    ImageView mobileimage;
    TextView nameofmobile;
    TextView priceofmobile;
    TextView brandofmobile;
    TextView ramofmobile;
    TextView resolutionofmobile;
    TextView storageofmobile;
    TextView simtypeofmobile;
    TextView processorofmobile;
    TextView operatingsystemofmobile;
    TextView frontcameraofmobile;
    TextView backcameraofmobile;
    TextView  battery;
    TextView  screensize;
    Boolean status;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("mobile_name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Intitializing the Views
        mobileimage=(ImageView) findViewById(R.id.productimage);
        nameofmobile=(TextView) findViewById(R.id.nameofphone);
        priceofmobile=(TextView) findViewById(R.id.price);
        resolutionofmobile=(TextView) findViewById(R.id.screenresolution);
        storageofmobile=(TextView) findViewById(R.id.storage);
        simtypeofmobile=(TextView) findViewById(R.id.simtype);
        processorofmobile=(TextView) findViewById(R.id.processor);
        operatingsystemofmobile=(TextView) findViewById(R.id.operatingsystem);
        ramofmobile=(TextView) findViewById(R.id.ramofphone);
        frontcameraofmobile=(TextView) findViewById(R.id.frontcamera);
        backcameraofmobile=(TextView) findViewById(R.id.backcamera);
        brandofmobile=(TextView) findViewById(R.id.brand);
        battery=(TextView) findViewById(R.id.battery);
        screensize=(TextView) findViewById(R.id.screensize);
        //setting values in views
        Picasso.with(Phone_Details.this).load(getIntent().getStringExtra("mobile_image")).into(mobileimage);
        nameofmobile.setText(getIntent().getStringExtra("mobile_name"));
        priceofmobile.setText(String.valueOf("RS "+getIntent().getIntExtra("mobile_price",0)));
        resolutionofmobile.setText(getIntent().getStringExtra("mobile_resolution"));
        storageofmobile.setText(getIntent().getStringExtra("mobile_storage"));
        simtypeofmobile.setText(getIntent().getStringExtra("mobile_simtype"));
        processorofmobile.setText(getIntent().getStringExtra("mobile_processor"));
        operatingsystemofmobile.setText(getIntent().getStringExtra("mobile_os"));
        ramofmobile.setText(getIntent().getStringExtra("mobile_ram"));
        frontcameraofmobile.setText(getIntent().getStringExtra("mobile_frontcamera"));
        backcameraofmobile.setText(getIntent().getStringExtra("mobile_backcamera"));
        brandofmobile.setText(getIntent().getStringExtra("mobile_brand"));
        battery.setText(getIntent().getStringExtra("mobile_battery"));
        screensize.setText(getIntent().getStringExtra("mobile_screensize"));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        status=prefs.getBoolean("Status",false);
        username=prefs.getString("Username","Guest");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getIntExtra("mobile_price", 0) > 30000&&status){
            getMenuInflater().inflate(R.menu.bidding_menu_item, menu);
    }else if(getIntent().getIntExtra("mobile_price",0)<30000&&status){
            getMenuInflater().inflate(R.menu.cart_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.place_bid){
            Intent i=new Intent(Phone_Details.this,Bidding_Activity.class);
            i.putExtra("mobilename",getIntent().getStringExtra("mobile_name"));
            i.putExtra("mobileid",getIntent().getIntExtra("mobile_id", 2));
            i.putExtra("mobileprice",getIntent().getIntExtra("mobile_price", 0));
            startActivity(i);
        }else if(item.getItemId()==R.id.view_bids){
            Intent i=new Intent(Phone_Details.this,show_bids_Activity.class);
            i.putExtra("mobileid",getIntent().getIntExtra("mobile_id", 2));
            startActivity(i);
        }else if(item.getItemId()==R.id.addtocart){
            final AlertDialog.Builder quantityselectiondialog = new AlertDialog.Builder(Phone_Details.this);
            quantityselectiondialog.setTitle("Select Quantity");
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            final View v = inflater.inflate(R.layout.alertdialoglayout, null);
            quantityselectiondialog.setView(v);
            final NumberPicker quantitypicker = (NumberPicker) v.findViewById(R.id.selectquantity);
            quantitypicker.setMaxValue(10);
            quantitypicker.setMinValue(1);
            quantityselectiondialog.setPositiveButton("Set", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int Price =getIntent().getIntExtra("mobile_price",0) * quantitypicker.getValue();
                    Boolean isinserted = new dbhelper(Phone_Details.this).insert_product_toshoppingcart(nameofmobile.getText().toString(), Price,getIntent().getStringExtra("mobile_image"),quantitypicker.getValue(),username);
                    if (isinserted) {
                        Toast.makeText(Phone_Details.this, "Product Added to cart", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Phone_Details.this, "Product not Added to cart", Toast.LENGTH_LONG).show();
                    }
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            quantityselectiondialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
