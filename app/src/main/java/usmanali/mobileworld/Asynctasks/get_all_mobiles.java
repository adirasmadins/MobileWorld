package usmanali.mobileworld.Asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import usmanali.mobileworld.adapter_classes.adapter;
import usmanali.mobileworld.model_classes.mobiles;

/**
 * Created by SAJIDCOMPUTERS on 8/30/2017.
 */

public class get_all_mobiles extends AsyncTask {
ArrayList<mobiles> mobileslist;
    Context context;
    public get_all_mobiles(RecyclerView mobilesrv,Context context) {
        this.mobilesrv = mobilesrv;
        this.context=context;
        mobileslist=new ArrayList<>();
    }

    RecyclerView mobilesrv;
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("https://ameboid-snaps.000webhostapp.com/get_all_mobiles.php");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
           StringBuffer sb=new StringBuffer();
            String Json;
            while((Json=reader.readLine())!=null){
                sb.append(Json);
            }
            JSONArray jsonArray=new JSONArray(sb.toString());
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jb= jsonArray.getJSONObject(i);
                mobiles mobileinfo=new mobiles();
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
                mobileslist.add(mobileinfo);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(mobileslist.size()>0)
        mobilesrv.setAdapter(new adapter(mobileslist,context));
    }
}
