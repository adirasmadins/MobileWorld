package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by SAJIDCOMPUTERS on 8/31/2017.
 */

public class post_used_mobiles_ads_task extends AsyncTask<String,Void,String> {
    Context context;
    ProgressDialog pd;
    public post_used_mobiles_ads_task(Context context) {
        this.context = context;
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String name=strings[0];
        String username=strings[1];
        String mobile_name=strings[2];
        String mobile_brand=strings[3];
        String mobile_warranty=strings[4];
        String mobile_usage_period=strings[5];
        String mobile_description=strings[6];
        String mobile_price=strings[7];
        String mobile_image=strings[8];
        try {
            URL url = new URL("https://ameboid-snaps.000webhostapp.com/post_used_mobile_ads.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            String information =
                            URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(username,"UTF-8") + "&"  +
                            URLEncoder.encode("mobile_name","UTF-8") + "=" +  URLEncoder.encode(mobile_name, "UTF-8")+ "&" +
                            URLEncoder.encode("mobile_brand","UTF-8") + "=" + URLEncoder.encode(mobile_brand ,"UTF-8") + "&" +
                            URLEncoder.encode("mobile_warranty","UTF-8")+ "=" + URLEncoder.encode(mobile_warranty,"UTF-8")+ "&" +
                            URLEncoder.encode("mobile_usage_period", "UTF-8")+ "=" + URLEncoder.encode(mobile_usage_period,"UTF-8")+ "&" +
                            URLEncoder.encode("mobile_description", "UTF-8")+ "=" + URLEncoder.encode(mobile_description,"UTF-8")+ "&" +
                            URLEncoder.encode("mobile_price", "UTF-8")+ "=" + URLEncoder.encode(mobile_price,"UTF-8")+ "&"+ URLEncoder.encode("mobile_image", "UTF-8")+ "=" + URLEncoder.encode(strings[8],"UTF-8");
            writer.write(information);
            writer.flush();
            writer.close();
            os.close();
            InputStream is = connection.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(is));
            StringBuffer sb=new StringBuffer();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            Log.d("mobileimage",mobile_image);
            return sb.toString();
        }catch(Exception ex
                ){}
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(pd.isShowing())
            pd.dismiss();
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }
}
