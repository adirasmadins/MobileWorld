package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
 * Created by SAJIDCOMPUTERS on 9/1/2017.
 */

public class add_comments_to_posts extends AsyncTask<String,Void,String> {
    String nameofcommenter,usernameofcommenter,comment_body,post_unique_id,comment_time;
    Context context;
    ProgressDialog pd;
    public add_comments_to_posts(Context context) {
        this.context = context;
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait....");
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
        nameofcommenter=strings[0];
        usernameofcommenter=strings[1];
        comment_body=strings[2];
        post_unique_id=strings[3];
        comment_time=strings[4];
        try {
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/insert_comments.php");
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder information=new StringBuilder();
            information.append(URLEncoder.encode("name","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(nameofcommenter,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("Username","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(usernameofcommenter,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("comment_body","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(comment_body,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("post_unique_id","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(post_unique_id,"UTF-8"));
            information.append("&");
            information.append(URLEncoder.encode("time","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(comment_time,"UTF-8"));
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
