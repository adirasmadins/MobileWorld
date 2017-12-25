package usmanali.mobileworld.Activity_Files;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import usmanali.mobileworld.R;
import usmanali.mobileworld.Asynctasks.post_used_mobiles_ads_task;

public class postusedmobileads extends AppCompatActivity implements View.OnClickListener {
ImageView imageView;
    Button post_ad;
    Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 100;
    private Uri filepath;
    String name,username;
    EditText mobilename,mobilesaleprice,howmuchused,warrantyleft,otherdescription,mobilebrand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postusedmobileads);
        imageView=(ImageView)findViewById(R.id.mobileimage);
        mobilename=(EditText)findViewById(R.id.mobilename);
        post_ad=(Button) findViewById(R.id.placead);
        mobilesaleprice=(EditText) findViewById(R.id.mobilesaleprice);
        howmuchused=(EditText) findViewById(R.id.howmuchused);
        warrantyleft=(EditText) findViewById(R.id.warrantyleft);
        otherdescription=(EditText) findViewById(R.id.otherdescription);
        mobilebrand=(EditText) findViewById(R.id.mobilebrand);
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(postusedmobileads.this);
        name=prefs.getString("Name","Shaban");
        username=prefs.getString("Username","shaban123");
        imageView.setOnClickListener(this);
        post_ad.setOnClickListener(this);
    }
    public void choose_img() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }
    public void add_data(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        if(mobilename.getText().toString().isEmpty()){
            Toast.makeText(postusedmobileads.this,"Enter Name of the Mobile",Toast.LENGTH_LONG).show();
        }else if(mobilesaleprice.getText().toString().isEmpty()){
            Toast.makeText(postusedmobileads.this,"Enter Price of the Mobile",Toast.LENGTH_LONG).show();
        }else if(howmuchused.getText().toString().isEmpty()){
            Toast.makeText(postusedmobileads.this,"Enter Duration of Useage  of the Mobile",Toast.LENGTH_LONG).show();
        }else if(warrantyleft.getText().toString().isEmpty()){
            Toast.makeText(postusedmobileads.this,"Enter Warranty left  of the Mobile",Toast.LENGTH_LONG).show();
        }else if(otherdescription.getText().toString().isEmpty()){
            Toast.makeText(postusedmobileads.this,"Enter Description of the Mobile",Toast.LENGTH_LONG).show();
        }else{
            new post_used_mobiles_ads_task(postusedmobileads.this).execute(name,username,mobilename.getText().toString(),mobilebrand.getText().toString(),warrantyleft.getText().toString(),howmuchused.getText().toString(),otherdescription.getText().toString(),mobilesaleprice.getText().toString(),encodedImage);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null) {
            filepath=data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.mobileimage){
            choose_img();
        }else if(view.getId()==R.id.placead){
            add_data();
        }
    }
}
