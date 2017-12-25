package usmanali.mobileworld.Asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import usmanali.mobileworld.model_classes.mobiles;

/**
 * Created by SAJIDCOMPUTERS on 8/31/2017.
 */

public class get_mobile_by_name_task extends AsyncTask<String,Void,Void> {
ArrayList<mobiles> mobilesArrayList;
    Context context;
    ImageView mobileimage;
    TextView priceofmobile;
    TextView ramofmobile;
    TextView resolutionofmobile;
    TextView storageofmobile;
    TextView simtypeofmobile;
    TextView batteryofmobile;
    TextView screensizeofmobile;
    TextView operatingsystemofmobile;
    TextView frontcameraofmobile;
    TextView backcameraofmobile;
    TextView processorofmobile;
    TextView brandofmobile;
    public get_mobile_by_name_task(Context context, ImageView mobileimage, TextView priceofmobile, TextView ramofmobile, TextView resolutionofmobile, TextView storageofmobile, TextView simtypeofmobile, TextView operatingsystemofmobile, TextView frontcameraofmobile, TextView backcameraofmobile,TextView batteryofmobile,TextView screensizeofmobile,TextView processorofmobile,TextView brandofmobile) {
        this.context = context;
        this.mobileimage = mobileimage;
        this.priceofmobile = priceofmobile;
        this.ramofmobile = ramofmobile;
        this.resolutionofmobile = resolutionofmobile;
        this.storageofmobile = storageofmobile;
        this.simtypeofmobile = simtypeofmobile;
        this.batteryofmobile=batteryofmobile;
        this.screensizeofmobile=screensizeofmobile;
        this.operatingsystemofmobile = operatingsystemofmobile;
        this.frontcameraofmobile = frontcameraofmobile;
        this.backcameraofmobile = backcameraofmobile;
        this.processorofmobile=processorofmobile;
        this.brandofmobile=brandofmobile;
        mobilesArrayList=new ArrayList<>();
    }

    @Override
    protected Void doInBackground(String... strings) {
        String name =strings[0];
        try {
            URL url = new URL("https://ameboid-snaps.000webhostapp.com/get_mobile_info_by_name.php?mobile_name="+name);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream is = connection.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
            StringBuffer sb=new StringBuffer();
            String Json;
            while((Json=reader.readLine())!=null){
                sb.append(Json);
            }
            Log.d("mobile_by_name",sb.toString());
            JSONArray jsonArray=new JSONArray(sb.toString());
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jb = jsonArray.getJSONObject(i);
                mobiles mobileinfo = new mobiles();
                mobileinfo.setMobile_name(jb.getString("mobile_name"));
                mobileinfo.setMobile_backcamera(jb.getString("mobile_backcamera"));
                mobileinfo.setMobile_image(jb.getString("mobile_image"));
                mobileinfo.setMobile_price(jb.getInt("mobile_price"));
                mobileinfo.setMobile_frontcamera(jb.getString("mobile_frontcamera"));
                mobileinfo.setMobile_brand(jb.getString("mobile_brand"));
                mobileinfo.setMobile_os(jb.getString("mobile_os"));
                mobileinfo.setMobile_ram(jb.getString("mobile_ram"));
                mobileinfo.setMobile_battery(jb.getString("Battery_capacity"));
                mobileinfo.setMobile_screensize(jb.getString("screen_size"));
                mobileinfo.setMobile_processor(jb.getString("mobile_processor"));
                mobileinfo.setMobile_resolution(jb.getString("screen_resolution"));
                mobileinfo.setMobile_simtype(jb.getString("mobile_simtype"));
                mobileinfo.setMobile_id(jb.getInt("Id"));
                mobileinfo.setMobile_storage(jb.getString("mobile_storage"));
                mobilesArrayList.add(mobileinfo);
            }
        }catch(Exception ex){
           ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(mobilesArrayList.size()>0) {
            priceofmobile.setText(String.valueOf(mobilesArrayList.get(0).getMobile_price()));
            ramofmobile.setText(mobilesArrayList.get(0).getMobile_ram());
            resolutionofmobile.setText(mobilesArrayList.get(0).getMobile_resolution());
            storageofmobile.setText(mobilesArrayList.get(0).getMobile_storage());
            simtypeofmobile.setText(mobilesArrayList.get(0).getMobile_simtype());
            processorofmobile.setText(mobilesArrayList.get(0).getMobile_processor());
            operatingsystemofmobile.setText(mobilesArrayList.get(0).getMobile_os());
            frontcameraofmobile.setText(mobilesArrayList.get(0).getMobile_frontcamera());
            backcameraofmobile.setText(mobilesArrayList.get(0).getMobile_backcamera());
            batteryofmobile.setText(mobilesArrayList.get(0).getMobile_battery());
            screensizeofmobile.setText(mobilesArrayList.get(0).getMobile_screensize());
            brandofmobile.setText(mobilesArrayList.get(0).getMobile_brand());
            Picasso.with(context).load(mobilesArrayList.get(0).getMobile_image()).into(mobileimage);
        }else{
            Toast.makeText(context,"This Feature is only Available for devices with Android 7.0 and above",Toast.LENGTH_LONG).show();
        }
    }
}
