package usmanali.mobileworld.Asynctasks;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import usmanali.mobileworld.adapter_classes.show_used_mobile_adapter;
import usmanali.mobileworld.model_classes.usedmobiles;

/**
 * Created by SAJIDCOMPUTERS on 9/1/2017.
 */

public class fetch_used_mobiles_task extends AsyncTask {
    RecyclerView usedmobilelist;
    Context context;
     ProgressDialog pd;
    public fetch_used_mobiles_task(RecyclerView usedmobilelist, Context context) {
        this.usedmobilelist = usedmobilelist;
        this.context = context;
        usedmobilesArrayList=new ArrayList<>();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    ArrayList<usedmobiles> usedmobilesArrayList;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("https://ameboid-snaps.000webhostapp.com/get_all_used_mobiles.php");
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
            Log.d("usedmobiles",sb.toString());
            JSONArray jsonArray=new JSONArray(sb.toString());
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jb = jsonArray.getJSONObject(i);
                usedmobiles um=new usedmobiles();
                um.setName_of_publisher(jb.getString("Name"));
                um.setUsername_of_publisher("Username");
                um.setMobile_name(jb.getString("mobile_name"));
                um.setMobile_brand(jb.getString("mobile_brand"));
                um.setMobile_description(jb.getString("mobile_description"));
                um.setMobile_usage_period(jb.getString("mobile_usage_period"));
                um.setMobile_warranty(jb.getString("mobile_warranty"));
                um.setMobile_image(jb.getString("mobile_image"));
                um.setMobile_price(jb.getInt("mobile_price"));
                usedmobilesArrayList.add(um);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(pd.isShowing())
            pd.dismiss();
        if(usedmobilesArrayList.size()>0) {
            usedmobilelist.setAdapter(new show_used_mobile_adapter(context, usedmobilesArrayList));
        }else{
            Toast.makeText(context,"No Used Mobile Available for Sale",Toast.LENGTH_LONG).show();
        }
    }
}
