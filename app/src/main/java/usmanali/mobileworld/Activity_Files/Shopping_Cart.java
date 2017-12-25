package usmanali.mobileworld.Activity_Files;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import usmanali.mobileworld.R;
import usmanali.mobileworld.adapter_classes.cartlistadapter;
import usmanali.mobileworld.model_classes.dbhelper;
import usmanali.mobileworld.model_classes.mobiles;
import usmanali.mobileworld.Asynctasks.place_order_task;

public class Shopping_Cart extends AppCompatActivity {
RecyclerView cartlist;
    String Username;
    Button btnCheckout;
    dbhelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping__cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       cartlist=(RecyclerView) findViewById(R.id.cartlist);
        btnCheckout=(Button) findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place_orders();
            }
        });
        ArrayList<mobiles> latestmobilelist=new ArrayList<>();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Username=prefs.getString("Username","Guest");
        cartlist.setLayoutManager(new LinearLayoutManager(Shopping_Cart.this));
         mydb=new dbhelper(Shopping_Cart.this);
    cartlist.setAdapter(new cartlistadapter(show_products_in_cart(mydb,Username),Shopping_Cart.this));
    }
    public ArrayList<mobiles> show_products_in_cart(dbhelper mydb,String Username){
        ArrayList<mobiles> productsincart=new ArrayList<>();
        Cursor res = mydb.get_products_in_cart(Username);
        if (res.getCount() == 0) {
            Toast.makeText(Shopping_Cart.this,"No Products in Cart",Toast.LENGTH_LONG) .show();
        }
        while (res.moveToNext()) {
            mobiles p=new mobiles();
            p.setId(res.getInt(0));
            p.setMobile_name(res.getString(1));
            p.setMobile_price(res.getInt(2));
            p.setQuantity(res.getInt(4));
            p.setUsername(res.getString(5));
            p.setMobile_image(res.getString(3));
            productsincart.add(p);
        }
        return productsincart;
    }
    private void place_orders(){
        java.util.Calendar calender= java.util.Calendar.getInstance();
      String  DateTime= String.valueOf(calender.getTime());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Shopping_Cart.this);
     String name=  prefs.getString("Name",null);
       String username= prefs.getString("Username",null);
      String Address=  prefs.getString("Address",null);
      String email=  prefs.getString("Email",null);
      String phone=  prefs.getString("Phone",null);
        Cursor res = mydb.get_products_in_cart(Username);
        if (res.getCount() == 0) {
            Toast.makeText(Shopping_Cart.this, "You dont have any items in cart", Toast.LENGTH_LONG).show();
        }else {
            StringBuffer sb = new StringBuffer();
            while (res.moveToNext()) {
                sb.append(res.getString(1) + " ");
                sb.append("x" + res.getInt(4) + " " + "\n");
            }
            new place_order_task(Shopping_Cart.this).execute(username,sb.toString(),name,Address,email,phone,String.valueOf(mydb.getTotalOfAmount(Username)),DateTime);
            mydb.delete_all(username);
            startActivity(new Intent(Shopping_Cart.this, MainActivity.class));
            finish();
        }
    }
}
