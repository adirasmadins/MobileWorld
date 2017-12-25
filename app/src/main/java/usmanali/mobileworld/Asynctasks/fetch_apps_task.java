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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import usmanali.mobileworld.adapter_classes.app_list_adapter;
import usmanali.mobileworld.model_classes.apps;

/**
 * Created by SAJIDCOMPUTERS on 9/4/2017.
 */

public class fetch_apps_task extends AsyncTask {
    ArrayList<apps> appslist;
    ProgressDialog pd;
    public fetch_apps_task(Context context, RecyclerView appsrv) {
        this.context = context;
        this.appsrv = appsrv;
        appslist=new ArrayList<>();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    Context context;
    RecyclerView appsrv;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/get_app_from_url.php");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
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
                apps app=new apps();
                app.setApp_name(jb.getString("app_name"));
                app.setApp_version(jb.getString("app_version"));
                app.setApp_url(jb.getString("app_url"));
                app.setApp_pic_url(jb.getString("app_pic_url"));
                appslist.add(app);
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
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (pd.isShowing())
            pd.dismiss();
        if(appslist.size()>0) {
            appsrv.setAdapter(new app_list_adapter(appslist, context));
        }else {
            Toast.makeText(context,"No Data Found",Toast.LENGTH_LONG).show();
        }
    }
}
