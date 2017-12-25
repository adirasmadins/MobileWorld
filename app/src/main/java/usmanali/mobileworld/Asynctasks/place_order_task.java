package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by shaban on 9/5/2017.
 */

public class place_order_task extends AsyncTask<String,Void,String> {
    ProgressDialog pd;
    public place_order_task(Context context) {
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
    protected String doInBackground(String... params) {
        try {
            String Username=params[0];
            String Name_of_Items=params[1];
            String Name=params[2];
            String postal_address=params[3];
            String Email=params[4];
            String Phone=params[5];
            String price=params[6];
            String orderdatetime=params[7];
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/place_orders.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String information =
                    URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8") + "&" +
                    URLEncoder.encode("Name_of_Items", "UTF-8") + "=" + URLEncoder.encode(Name_of_Items,"UTF-8") + "&"  +
                            URLEncoder.encode("Name","UTF-8") + "=" +  URLEncoder.encode(Name, "UTF-8")+ "&" +
                            URLEncoder.encode("Address","UTF-8") + "=" + URLEncoder.encode(postal_address ,"UTF-8") + "&" +
                            URLEncoder.encode("Email","UTF-8")+ "=" + URLEncoder.encode(Email,"UTF-8")+ "&" +
                            URLEncoder.encode("Phone", "UTF-8")+ "=" + URLEncoder.encode(Phone,"UTF-8")+"&"+
                            URLEncoder.encode("price","UTF-8")+ "=" + URLEncoder.encode(price,"UTF-8")+ "&"+
                            URLEncoder.encode("orderdatetime", "UTF-8")+ "=" + URLEncoder.encode(orderdatetime,"UTF-8");
            writer.write(information);
            writer.flush();
            writer.close();
            os.close();
            InputStream is = connection.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
            StringBuffer sb=new StringBuffer();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
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
