package usmanali.mobileworld.Asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by SAJIDCOMPUTERS on 8/31/2017.
 */

public class fetch_mobile_for_comparing extends AsyncTask {
    Spinner choosefirstmobile,choosesecondmobile;
    Context context;
    ArrayList<String> mobilenames;
    public fetch_mobile_for_comparing(Spinner choosefirstmobile, Spinner choosesecondmobile, Context context) {
        this.choosefirstmobile = choosefirstmobile;
        this.choosesecondmobile = choosesecondmobile;
        this.context = context;
        mobilenames=new ArrayList<>();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("https://ameboid-snaps.000webhostapp.com/fetch_mobile_for_comparing.php");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
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
            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jb = jsonArray.getJSONObject(i);
                mobilenames.add(jb.getString("mobile_name"));
            }
        } catch (Exception ex) {}
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,mobilenames);
        choosefirstmobile.setAdapter(adapter);
        choosesecondmobile.setAdapter(adapter);
    }
}
