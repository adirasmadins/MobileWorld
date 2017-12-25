package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
 * Created by SAJIDCOMPUTERS on 8/14/2017.
 */

public class Register_task extends AsyncTask<String,Void,String> {
    Context context;
    ProgressDialog pd;
    public Register_task(Context context) {
        this.context=context;
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
        String Username=strings[0];
        String Password=strings[1];
        String Name=strings[2];
        String Email=strings[3];
        String postal_address=strings[4];
        String Phone=strings[5];
        try{
        URL url = new URL("https://ameboid-snaps.000webhostapp.com/Signup.php");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        String information =
                URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8") + "&" +
                        URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password,"UTF-8") + "&"  +
                        URLEncoder.encode("Name","UTF-8") + "=" +  URLEncoder.encode(Name, "UTF-8")+ "&" +
                        URLEncoder.encode("Address","UTF-8") + "=" + URLEncoder.encode(postal_address ,"UTF-8") + "&" +
                        URLEncoder.encode("Email","UTF-8")+ "=" + URLEncoder.encode(Email,"UTF-8")+ "&" +
                        URLEncoder.encode("Phone", "UTF-8")+ "=" + URLEncoder.encode(Phone,"UTF-8");

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
    } catch (Exception e) {
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
