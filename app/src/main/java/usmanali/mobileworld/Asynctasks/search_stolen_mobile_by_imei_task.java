package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

import usmanali.mobileworld.model_classes.stolen_mobiles;

/**
 * Created by SAJIDCOMPUTERS on 8/30/2017.
 */

public class search_stolen_mobile_by_imei_task extends AsyncTask<String,Void,Void> {
    String imeinumber;
    ArrayList<stolen_mobiles> stolen_mobilesdata;
    ProgressDialog pd;
    public search_stolen_mobile_by_imei_task(TextView nameofreporter, TextView mobilename, TextView mobileimei, TextView stolendate, Context context,CardView detailscard) {
        this.nameofreporter = nameofreporter;
        this.mobilename = mobilename;
        this.mobileimei = mobileimei;
        this.stolendate = stolendate;
        this.context = context;
        this.detailscard=detailscard;
        stolen_mobilesdata=new ArrayList<>();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    TextView nameofreporter,mobilename,mobileimei,stolendate;
    CardView detailscard;
    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        imeinumber=strings[0];
        try {
            URL url = new URL("https://ameboid-snaps.000webhostapp.com/search_stolen_mobile_by_imei.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuffer data = new StringBuffer();
            data.append(URLEncoder.encode("imei_number", "UTF-8"));
            data.append("=");
            data.append(URLEncoder.encode(imeinumber, "UTF-8"));
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
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jb= jsonArray.getJSONObject(i);
                stolen_mobiles sm=new stolen_mobiles();
                sm.setPersonname(jb.getString("Name"));
                sm.setBuyer_reciept(jb.getString("reciept"));
                sm.setImei(jb.getString("imei_number"));
                sm.setStolen_date(jb.getString("stolen_date"));
                sm.setMobile_name(jb.getString("mobile_name"));
                stolen_mobilesdata.add(sm);
        }
        }catch(Exception e){}
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(pd.isShowing())
            pd.dismiss();
        if(stolen_mobilesdata.size()!=0){
            Toast.makeText(context,"Mobile with this Imei is Found",Toast.LENGTH_LONG).show();
            mobileimei.setText(stolen_mobilesdata.get(0).getImei());
            mobilename.setText(stolen_mobilesdata.get(0).getMobile_name());
            stolendate.setText(stolen_mobilesdata.get(0).getStolen_date());
            nameofreporter.setText(stolen_mobilesdata.get(0).getPersonname());
            detailscard.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(context,"Mobile with this Imei is not Found",Toast.LENGTH_LONG).show();
        }
    }
}
