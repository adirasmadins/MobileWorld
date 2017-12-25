package usmanali.mobileworld.Activity_Files;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import usmanali.mobileworld.R;
import usmanali.mobileworld.adapter_classes.packages_adapter;

public class show_cellular_packages extends AppCompatActivity {
RecyclerView operatorslist;
    ArrayList<Integer> logos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cellular_packages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Operator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        operatorslist=(RecyclerView) findViewById(R.id.operatorslist);
        logos.add(R.drawable.telenor_logo);
        logos.add(R.drawable.ufone_packages);
        logos.add(R.drawable.zong_logo);
        logos.add(R.drawable.mobilink_logo);
        logos.add(R.drawable.warid_logo);
        operatorslist.setLayoutManager(new LinearLayoutManager(show_cellular_packages.this));
        operatorslist.setAdapter(new packages_adapter(logos,show_cellular_packages.this));

    }

}
