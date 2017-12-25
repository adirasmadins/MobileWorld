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
 * Created by SAJIDCOMPUTERS on 8/31/2017.
 */

public class add_posts_task extends AsyncTask<String,Void,String> {
    public add_posts_task(Context context) {
        this.context = context;
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }
    ProgressDialog pd;
    Context context;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
        pd.show();

    }

    @Override
    protected String doInBackground(String... strings) {
        String Name=strings[0];
        String Username=strings[1];
        String post=strings[2];
        String post_datetime=strings[3];
        try{
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/insert_post.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String information =
                            URLEncoder.encode("Username", "UTF-8") + "=" + URLEncoder.encode(Username, "UTF-8") + "&" +
                            URLEncoder.encode("Name","UTF-8") + "=" +  URLEncoder.encode(Name, "UTF-8")+ "&" +
                            URLEncoder.encode("post","UTF-8") + "=" + URLEncoder.encode(post ,"UTF-8") + "&" +
                            URLEncoder.encode("post_datetime","UTF-8")+ "=" + URLEncoder.encode(post_datetime,"UTF-8");
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
        }catch(Exception ex){}
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
