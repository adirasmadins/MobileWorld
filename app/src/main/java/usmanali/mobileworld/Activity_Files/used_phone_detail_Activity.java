package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import usmanali.mobileworld.R;

public class used_phone_detail_Activity extends AppCompatActivity {
 ImageView productimage;
    TextView adpostername,mobilename,price,howmuchused,mobiledescription,mobilebrand,warrantyleft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_phone_detail_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adpostername=(TextView) findViewById(R.id.adpostername);
        mobilename=(TextView) findViewById(R.id.mobilename);
        price=(TextView) findViewById(R.id.price);
        howmuchused=(TextView) findViewById(R.id.howmuchused);
        mobiledescription=(TextView) findViewById(R.id.mobiledescription);
        productimage=(ImageView) findViewById(R.id.productimage);
        mobilebrand=(TextView) findViewById(R.id.mobilebrand);
        warrantyleft=(TextView) findViewById(R.id.warrantyleft);
        //setting the values
        adpostername.setText(getIntent().getStringExtra("adpostername"));
        mobilename.setText(getIntent().getStringExtra("mobilename"));
        price.setText(String.valueOf(getIntent().getIntExtra("price",0)));
        howmuchused.setText(getIntent().getStringExtra("mobileusage"));
        mobiledescription.setText(getIntent().getStringExtra("mobiledescription"));
        Picasso.with(used_phone_detail_Activity.this).load(getIntent().getStringExtra("mobileimage")).into(productimage);
        mobilebrand.setText(getIntent().getStringExtra("mobilebrand"));
        warrantyleft.setText(getIntent().getStringExtra("mobilewarrantyleft"));
        Log.d("adpostername",getIntent().getStringExtra("adpostername"));
    }

}
