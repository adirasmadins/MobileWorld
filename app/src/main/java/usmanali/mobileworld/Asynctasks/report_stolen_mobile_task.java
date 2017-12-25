package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by SAJIDCOMPUTERS on 8/30/2017.
 */

public class report_stolen_mobile_task extends AsyncTask<String,Void,String> {
    ProgressDialog pd;
    public report_stolen_mobile_task(Context con) {
        this.con = con;
        pd=new ProgressDialog(con);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    Context con;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String name=strings[0];
        String imei=strings[1];
        String stolen_date=strings[2];
        String reciept=strings[3];
        String mobile_name=strings[4];
        try{
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/report_stolen_mobiles.php");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            String information =
                    URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" + URLEncoder.encode("imei_number", "UTF-8") + "=" + URLEncoder.encode(imei,"UTF-8") + "&"  + URLEncoder.encode("stolen_date","UTF-8") + "=" +  URLEncoder.encode(stolen_date, "UTF-8")+ "&" + URLEncoder.encode("reciept","UTF-8") + "=" + URLEncoder.encode(reciept ,"UTF-8") + "&" + URLEncoder.encode("mobile_name","UTF-8") + "=" + URLEncoder.encode(mobile_name,"UTF-8");
            writer.write(information);
            writer.flush();
            writer.close();
            BufferedReader reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            Log.e("response",sb.toString());
            return sb.toString();
        }catch(Exception ex){}
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if(!pd.isShowing())
            pd.dismiss();
        Toast.makeText(con,response,Toast.LENGTH_LONG).show();
    }
}
