package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import usmanali.mobileworld.adapter_classes.mobile_by_brand_adapter;
import usmanali.mobileworld.model_classes.mobiles;

/**
 * Created by SAJIDCOMPUTERS on 9/5/2017.
 */

public class fetch_mobiles_by_brand extends AsyncTask<String,Void,Void> {
    String brand;
    ArrayList<mobiles> mobilesArrayList;
    ProgressDialog pd;
    public fetch_mobiles_by_brand(RecyclerView mobilesrv, Context context) {
        this.mobilesrv = mobilesrv;
        this.context = context;
        mobilesArrayList=new ArrayList<>();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    RecyclerView mobilesrv;
    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {
            brand=strings[0];
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/get_mobiles_by_brand.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuffer data = new StringBuffer();
            data.append(URLEncoder.encode("mobile_brand", "UTF-8"));
            data.append("=");
            data.append(URLEncoder.encode(brand, "UTF-8"));
            writer.write(data.toString());
            writer.flush();
            writer.close();
            InputStream is = connection.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
            StringBuffer sb=new StringBuffer();
            String Json;
            while((Json=reader.readLine())!=null){
                sb.append(Json);
            }
            JSONArray jsonArray=new JSONArray(sb.toString());
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jb = jsonArray.getJSONObject(i);
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
                mobilesArrayList.add(mobileinfo);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(pd.isShowing())
            pd.dismiss();
        if(mobilesArrayList.size()>0) {
            mobilesrv.setAdapter(new mobile_by_brand_adapter(mobilesArrayList, context));
        }else{
            Toast.makeText(context,"Unable to fetch mobiles",Toast.LENGTH_LONG).show();
        }
    }
}
