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

import usmanali.mobileworld.adapter_classes.show_acessories_adapter;
import usmanali.mobileworld.model_classes.acessories;

/**
 * Created by SAJIDCOMPUTERS on 9/9/2017.
 */

public class fetch_acessories_task extends AsyncTask {
    Context context;
    ArrayList<acessories> acessoriesArrayList;
    RecyclerView acessorieslist;
    ProgressDialog pd;
    public fetch_acessories_task(Context context, RecyclerView acessorieslist) {
        this.context = context;
        this.acessorieslist = acessorieslist;
        acessoriesArrayList=new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url =new URL("https://ameboid-snaps.000webhostapp.com/get_all_acessories.php");
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
                acessories acessorie=new acessories();
                acessorie.setAcessories_name(jb.getString("acessories_name"));
                acessorie.setAcessories_brand(jb.getString("acessories_brand"));
                acessorie.setAcessories_price(jb.getInt("acessories_price"));
                acessorie.setAcessories_image(jb.getString("acessories_image"));
                acessoriesArrayList.add(acessorie);
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
        if(acessoriesArrayList.size()>0){
            acessorieslist.setAdapter(new show_acessories_adapter(acessoriesArrayList,context));
        }else {
            Toast.makeText(context,"no acessories found",Toast.LENGTH_LONG).show();
        }
    }

}
