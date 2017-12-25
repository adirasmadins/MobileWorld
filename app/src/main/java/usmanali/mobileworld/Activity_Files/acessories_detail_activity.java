package usmanali.mobileworld.Activity_Files;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class acessories_detail_activity extends AppCompatActivity {
TextView nameofacessorie,brand,priceofacessorie;
    ImageView acessorie_image;
    String username;
    Boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acessories_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("acessories_name"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nameofacessorie=(TextView) findViewById(R.id.nameofacessorie);
        brand=(TextView) findViewById(R.id.brand);
        priceofacessorie=(TextView)findViewById(R.id.priceofacessorie);
        acessorie_image=(ImageView) findViewById(R.id.productimage);
        nameofacessorie.setText(getIntent().getStringExtra("acessories_name"));
        priceofacessorie.setText("Rs "+String.valueOf(getIntent().getIntExtra("acessories_price",0)));
        brand.setText(getIntent().getStringExtra("acessories_brand"));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        status=prefs.getBoolean("Status",false);
        username=prefs.getString("Username","Guest");
        Picasso.with(acessories_detail_activity.this).load(getIntent().getStringExtra("acessories_image")).into(acessorie_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addtocart){
            final AlertDialog.Builder quantityselectiondialog = new AlertDialog.Builder(acessories_detail_activity.this);
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
                    Boolean isinserted = new dbhelper(acessories_detail_activity.this).insert_product_toshoppingcart(nameofacessorie.getText().toString(), Price,getIntent().getStringExtra("mobile_image"),quantitypicker.getValue(),username);
                    if (isinserted) {
                        Toast.makeText(acessories_detail_activity.this, "Product Added to cart", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(acessories_detail_activity.this, "Product not Added to cart", Toast.LENGTH_LONG).show();
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
