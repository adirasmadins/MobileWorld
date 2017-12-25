package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by SAJIDCOMPUTERS on 9/3/2017.
 */

public class place_bid_task extends AsyncTask<String,Void,String> {
    String name,username,mobilename,mobileid,bid_amount,bid_date;
    ProgressDialog pd;
    public place_bid_task(Context context) {
        this.context = context;
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        name=strings[0];
        username=strings[1];
        mobilename=strings[2];
        mobileid=strings[3];
        bid_amount=strings[4];
        bid_date=strings[5];
        try {
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/place_bid.php");
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder information=new StringBuilder();
            information.append(URLEncoder.encode("Name","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(name,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("Username","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(username,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("mobile_name","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(mobilename,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("mobile_id","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(mobileid,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("bid_amount","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(bid_amount,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("bid_date","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(bid_date,"UTF-8"));
            writer.write(information.toString());
            writer.flush();
            writer.close();
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response=new StringBuilder();
            String json;
            while((json=reader.readLine())!=null){
                response.append(json);
            }
            return response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
