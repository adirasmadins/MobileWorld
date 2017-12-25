package usmanali.mobileworld.Activity_Files;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.report_stolen_mobile_task;

public class Report_Stolen_Mobiles extends AppCompatActivity implements View.OnClickListener {
    EditText name, imei, stolendate;
    Button report;
    String reportingpersonname;
    ImageView reciept;
    Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 100;
    private Uri filepath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__stolen__mobiles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Report Stolen Mobile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Report_Stolen_Mobiles.this);
        reportingpersonname= prefs.getString("Name","Shaban");
        name = (EditText) findViewById(R.id.personname);
        imei = (EditText) findViewById(R.id.imeinumbertxt);
        stolendate = (EditText) findViewById(R.id.stolendate);
        report = (Button) findViewById(R.id.reportbtn);
        reciept=(ImageView) findViewById(R.id.reciptpic);
        report.setOnClickListener(this);
        reciept.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                reciept.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void chooseimg(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }
    public void add_data(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String method="insert";
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        if (name.getText().toString().isEmpty()) {
            Toast.makeText(Report_Stolen_Mobiles.this,"Please Provide Name",Toast.LENGTH_LONG).show();
        } else if (imei.getText().toString().isEmpty()) {
            Toast.makeText(Report_Stolen_Mobiles.this,"Please Provide IMEI Number",Toast.LENGTH_LONG).show();
        } else if (stolendate.getText().toString().isEmpty()) {
            Toast.makeText(Report_Stolen_Mobiles.this,"Please Provide Stolen Date",Toast.LENGTH_LONG).show();
        } else {
            new report_stolen_mobile_task(Report_Stolen_Mobiles.this).execute(reportingpersonname,imei.getText().toString(), stolendate.getText().toString(),encodedImage,name.getText().toString());
        }

    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.reportbtn){
            add_data();
        }else if(view.getId()==R.id.reciptpic){
            chooseimg();
        }
    }
}
