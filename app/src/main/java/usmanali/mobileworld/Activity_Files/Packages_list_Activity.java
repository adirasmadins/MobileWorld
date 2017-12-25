package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usmanali.mobileworld.R;
import usmanali.mobileworld.adapter_classes.package_detail_adapter;

public class Packages_list_Activity extends AppCompatActivity {
ExpandableListView packagedetaillist;
    List<String> packages_catorgeries=new ArrayList<>();
    List<String> internet_packages=new ArrayList<>();
    List<String> call_packages=new ArrayList<>();
    List<String> sms_packages=new ArrayList<>();
    List<String> wingle_packages=new ArrayList<>();
    HashMap<String,List<String>> detailslist=new HashMap<>();
    int resID=-1;
    String Operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages_list_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("Operator"));
        packagedetaillist = (ExpandableListView) findViewById(R.id.packageslist);
         Operator=getIntent().getStringExtra("Operator");
        // adding Packages Category
        packages_catorgeries.add("Internet Packages");
        packages_catorgeries.add("Call Packages");
        packages_catorgeries.add("SMS Packages");
        packages_catorgeries.add("Wingle Packages");
        if (Operator.equals("telenor")){
            // adding internet Packages
            internet_packages.add("Talkshawk 4G Daily Bundle");
        internet_packages.add("Djuice 4G Daily Bundle");
        internet_packages.add("Djuice 4G Daily Bundle Lite");
        //adding call Packages
        call_packages.add("Djuice Team Offer");
        call_packages.add("Haftawar Chappar Phaar Offer");
        call_packages.add("2 Paisa Weekly Offer");
        //adding sms Packages
        sms_packages.add("Daily on-net SMS Package");
        //adding wingle Packages
        wingle_packages.add("Monthly 4G Unlimited");

        //Filling the HashMap
        detailslist.put("Internet Packages", internet_packages);
        detailslist.put("Call Packages", call_packages);
        detailslist.put("SMS Packages", sms_packages);
        detailslist.put("Wingle Packages", wingle_packages);
        //setting Adapter
        packagedetaillist.setAdapter(new package_detail_adapter(packages_catorgeries, detailslist, Packages_list_Activity.this));
    }else if(Operator.equals("ufone")){
            //Add ufone Internet Packages
            internet_packages.add("Ufone Daily Light Bucket");
            internet_packages.add("Ufone Mega Internet Bucket");
            internet_packages.add("Ufone Daily Special Bucket");
            //Add Ufone Call Packages
            call_packages.add("Daily Pakistan Offer");
            call_packages.add("Ufone Din Bhar Offer");
            //Add Ufone SMS Packages
            sms_packages.add("Ufone Daily On-net SMS Packages");
            sms_packages.add("Ufone Daily SMS Package");
            //Add Ufone Wingle Packages
            wingle_packages.add("Ufone Evo Wingle 10 GB");
            wingle_packages.add("Ufone Evo Wingle 5 GB Starter");
            //Filling the HashMap
            detailslist.put("Internet Packages", internet_packages);
            detailslist.put("Call Packages", call_packages);
            detailslist.put("SMS Packages", sms_packages);
            detailslist.put("Wingle Packages", wingle_packages);
            //setting Adapter
            packagedetaillist.setAdapter(new package_detail_adapter(packages_catorgeries, detailslist, Packages_list_Activity.this));
        }else if(Operator.equals("Zong")){
            //Add Zong Internet Packages
            internet_packages.add("Daily Mini 20 Mbps Packages");
            internet_packages.add("Daily Basic 100 Mbps");
            internet_packages.add("Monthly Basic 500 Mbps");
            //Add Zong  Call Packages
            call_packages.add("Zong Super Offer");
            call_packages.add("Zong to Zong Free Call");
            //Add Zong  SMS Packages
            sms_packages.add("Zong Perfect Package");

            //Add Zong Wingle Packages
             wingle_packages.add("Wingle 4G LTE Offer");
            wingle_packages.add("");
            //Filling the HashMap
            detailslist.put("Internet Packages", internet_packages);
            detailslist.put("Call Packages", call_packages);
            detailslist.put("SMS Packages", sms_packages);
            detailslist.put("Wingle Packages", wingle_packages);
            //setting Adapter
            packagedetaillist.setAdapter(new package_detail_adapter(packages_catorgeries, detailslist, Packages_list_Activity.this));

        }else if(Operator.equals("Mobilink")){
            //Add Mobilink Internet Packages
            internet_packages.add("Daily Social");
            internet_packages.add("Daily Browser");
            internet_packages.add("3 Day Extreme");
            //Add Mobilink  Call Packages
            call_packages.add("Day Bundle");
            call_packages.add("Daily Super Bundle");
            call_packages.add("Har Din Bundle");
            //Add Mobilink  SMS Packages
            sms_packages.add("Daily Packege");
            sms_packages.add("Daily Bundle");
            sms_packages.add("Weekly Bundle");
            //Add Mobilink Wingle Packages
            wingle_packages.add("Wingle Monthly Basic");
            wingle_packages.add("");
            //Filling the HashMap
            detailslist.put("Internet Packages", internet_packages);
            detailslist.put("Call Packages", call_packages);
            detailslist.put("SMS Packages", sms_packages);
            detailslist.put("Wingle Packages", wingle_packages);
            //setting Adapter
            packagedetaillist.setAdapter(new package_detail_adapter(packages_catorgeries, detailslist, Packages_list_Activity.this));
        }else if(Operator.equals("Warid")){
            //Add Warid Internet Packages
            internet_packages.add("Daily 4G");
            internet_packages.add("Mahana Offer");
            internet_packages.add("Poora Hafta Offer ");
            //Add Warid  Call Packages
            call_packages.add("Poora Hafta");
            call_packages.add("Mahana Offer");
            call_packages.add("7 Day Offer");
            //Add Warid  SMS Packages
            sms_packages.add("Daily Sms");
            sms_packages.add("Glow Daily Bundle");
            sms_packages.add("Glow Daily Bundle 2");
            //Add Warid Wingle Packages
            wingle_packages.add("Wingle Smart");
            wingle_packages.add("");
            //Filling the HashMap
            detailslist.put("Internet Packages", internet_packages);
            detailslist.put("Call Packages", call_packages);
            detailslist.put("SMS Packages", sms_packages);
            detailslist.put("Wingle Packages", wingle_packages);
            //setting Adapter
            packagedetaillist.setAdapter(new package_detail_adapter(packages_catorgeries, detailslist, Packages_list_Activity.this));
        }
        //Setting ClickListener to Child Items
             packagedetaillist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                 @Override
                 public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                     AlertDialog.Builder builder=new AlertDialog.Builder(Packages_list_Activity.this);
                     View v= LayoutInflater.from(Packages_list_Activity.this).inflate(R.layout.show_package_image,null);
                     ImageView pkgimage=(ImageView) v.findViewById(R.id.pkgimg);
                   if(Operator.equals("ufone")&&i==0){

                       String imgname="ufonenet"+String.valueOf(i1);
                        resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if (Operator.equals("ufone")&&i==1){
                       String imgname="ufonecall"+String.valueOf(i1);
                        resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("ufone")&&i==2){
                       String imgname="ufonesms"+String.valueOf(i1);
                        resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();

                   }else if (Operator.equals("ufone")&&i==3){
                       String imgname="ufonewingle"+String.valueOf(i1);
                        resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("telenor")&&i==0){
                       String imgname="telenornet"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();

                   }else if(Operator.equals("telenor")&&i==1){
                       String imgname="telenorcall"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if (Operator.equals("telenor")&&i==2){
                       String imgname="telenorsms"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("telenor")&&i==3){
                       String imgname="telenorwingle"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("Zong")&&i==0){
                       String imgname="zongnet"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("Zong")&&i==1){
                       String imgname="zongcall"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();

                   }else if (Operator.equals("Zong")&&i==2){
                       String imgname="zongsms"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("Zong")&&i==3){
                       String imgname="zongwingle"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();

                   }else if(Operator.equals("Warid")&&i==0){
                       String imgname="waridnet"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("Warid")&&i==1){
                       String imgname="waridcall"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if (Operator.equals("Warid")&&i==2){
                       String imgname="waridsms"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("Warid")&&i==3){
                       String imgname="waridwingle"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if(Operator.equals("Mobilink")&&i==0){
                       String imgname="mobilinknet"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();

                   }else if(Operator.equals("Mobilink")&&i==1){
                       String imgname="mobilinkcall"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }else if (Operator.equals("Mobilink")&&i==2){
                       String imgname="mobilinksms"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();

                   }else if(Operator.equals("Mobilink")&&i==3){
                       String imgname="mobilinkwingle"+String.valueOf(i1);
                       resID = getResources().getIdentifier(imgname , "drawable", getPackageName());
                       pkgimage.setImageResource(resID);
                       builder.setView(v);
                       builder.show();
                   }
                     return true;
                 }
             });

    }

}
