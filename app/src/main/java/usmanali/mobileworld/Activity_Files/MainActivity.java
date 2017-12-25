package usmanali.mobileworld.Activity_Files;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import usmanali.mobileworld.Asynctasks.get_all_mobiles;
import usmanali.mobileworld.R;
import usmanali.mobileworld.adapter_classes.brand_list_adapter;
import usmanali.mobileworld.adapter_classes.carouseladapter;
import usmanali.mobileworld.Asynctasks.fetch_acessories_task;
import usmanali.mobileworld.Asynctasks.mobile_prices_greater_then;
import usmanali.mobileworld.Asynctasks.mobile_prices_less_then;
import usmanali.mobileworld.model_classes.brands;

public class MainActivity extends AppCompatActivity {
ArrayList<Integer> carouselimages;
    ArrayList<brands> brandsArrayList;
    ViewPager carouselpager;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView4;
    NavigationView nv;
    DrawerLayout drawerlayout;
    Boolean status;
    RecyclerView RecyclerView3;
    ActionBarDrawerToggle drawerToggle;
    Button more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isNetworkConnected();
        nv=(NavigationView) findViewById(R.id.nav_bar);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        status=prefs.getBoolean("Status",false);
         if(status){
             nv.inflateMenu(R.menu.navigation_bar_menu);
         }else if(!status){
             nv.inflateMenu(R.menu.unauthenticated_user_navigation_bar_menu);
         }
        drawerlayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.discussion_forum){
                    startActivity(new Intent(MainActivity.this,Discussion_forum.class));
                }else if(item.getItemId()==R.id.home){
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                    finish();
                }else if(item.getItemId()==R.id.signup){
                    startActivity(new Intent(MainActivity.this,Signup_Activity.class));
                }else if(item.getItemId()==R.id.login){
                    startActivity(new Intent(MainActivity.this,Login_Activity.class));
                }else if(item.getItemId()==R.id.compare){
                    startActivity(new Intent(MainActivity.this,Choose_Phones_to_Compare_Activity.class));
                }else if(item.getItemId()==R.id.usedmobileads){
                    startActivity(new Intent(MainActivity.this,postusedmobileads.class));
                }else if(item.getItemId()==R.id.packages){
                    startActivity(new Intent(MainActivity.this,show_cellular_packages.class));
                }else if(item.getItemId()==R.id.stolenmobiles){
                    startActivity(new Intent(MainActivity.this,Stolen_mobiles_layout.class));
                }else if(item.getItemId()==R.id.videos){
                    startActivity(new Intent(MainActivity.this,Smartphone_Videos_Activity.class));
                }else if(item.getItemId()==R.id.viewusedmobile) {
                    startActivity(new Intent(MainActivity.this, display_used_mobiles_Activity.class));
                }else if(item.getItemId()==R.id.download_apps){
                    startActivity(new Intent(MainActivity.this,show_apps_for_downloading.class));
                }else if(item.getItemId()==R.id.cart){
                    startActivity(new Intent(MainActivity.this,Shopping_Cart.class));
                }
                return false;
            }
        });
        add_images_to_carousel();
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view_list);
        RecyclerView3=(RecyclerView) findViewById(R.id.recycler_view_list3);
        recyclerView4=(RecyclerView) findViewById(R.id.recycler_view_list4);
        recyclerView2=(RecyclerView) findViewById(R.id.recycler_view_list2);
         recyclerView4.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        RecyclerView3.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayout.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayout.HORIZONTAL,false));
        new get_all_mobiles(recyclerView,MainActivity.this).execute();
        new mobile_prices_greater_then(MainActivity.this,recyclerView2).execute("30000");
        new fetch_acessories_task(MainActivity.this,recyclerView4).execute();
        add_brands();
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerlayout.setDrawerListener(drawerToggle);
    }

    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(status) {
            getMenuInflater().inflate(R.menu.shopping_cart_menu_item, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            if (item.getItemId() == R.id.shoppingcart) {
                 SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                prefs.edit().remove("Status").apply();
                prefs.edit().remove("Name").apply();
                prefs.edit().remove("Username").apply();
                prefs.edit().remove("Address").apply();
                prefs.edit().remove("Email").apply();
                prefs.edit().remove("Phone").apply();
                prefs.edit().remove("Status").apply();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                finish();
            }

        return super.onOptionsItemSelected(item);
    }

    public void add_images_to_carousel() {
        carouselpager=(ViewPager)findViewById(R.id.carouselpager);
        carouselimages = new ArrayList<>();
        carouselimages.add(R.drawable.huaweip10plus);
        carouselimages.add(R.drawable.g);
        carouselpager.setAdapter(new carouseladapter(MainActivity.this, carouselimages));
    }
    private void add_brands(){
        brandsArrayList=new ArrayList<>();
        brandsArrayList.add(new brands("SAMSUNG",R.drawable.samsung));
       brandsArrayList.add(new brands("LENOVO",R.drawable.lenovo));
       brandsArrayList.add(new brands("NOKIA",R.drawable.nokia));
        brandsArrayList.add(new brands("QMOBILE",R.drawable.qmobile));
        brandsArrayList.add(new brands("SONY",R.drawable.sony));
        brandsArrayList.add(new brands("HTC",R.drawable.htc));
        brandsArrayList.add(new brands("APPLE",R.drawable.apple_logo));
        brandsArrayList.add(new brands("HUAWEI",R.drawable.huawei_logo));
        brandsArrayList.add(new brands("OPPO",R.drawable.oppo_logo));
        brandsArrayList.add(new brands("LG",R.drawable.lg_logo));
        RecyclerView3.setAdapter(new brand_list_adapter(brandsArrayList,MainActivity.this));
    }
    private void isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo()!=null && cm.getActiveNetworkInfo().isConnected()){
        }else {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("92Mobiles");
            builder.setCancelable(false);
            builder.setMessage("You are not connected to the Network");
            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).show();
        }
    }
}
