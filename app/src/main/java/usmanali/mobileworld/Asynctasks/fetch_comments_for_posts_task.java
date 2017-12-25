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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import usmanali.mobileworld.adapter_classes.comment_adapter;
import usmanali.mobileworld.model_classes.comment;

/**
 * Created by SAJIDCOMPUTERS on 9/2/2017.
 */

public class fetch_comments_for_posts_task extends AsyncTask<String,Void,Void> {
    String post_id;
    ArrayList<comment> commentlist;
    RecyclerView commentrv;
    Context context;
    ProgressDialog pd;
    public fetch_comments_for_posts_task(RecyclerView commentrv, Context context) {
        this.commentrv = commentrv;
        this.context = context;
        commentlist=new ArrayList<>();
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
    protected Void doInBackground(String... strings) {
        post_id=strings[0];
        try {
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/fetch_comments.php");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder informationn=new StringBuilder();
            informationn.append(URLEncoder.encode("post_unique_id","UTF-8"));
            informationn.append("=");
            informationn.append(URLEncoder.encode(post_id,"UTF-8"));
            writer.write(informationn.toString());
            writer.flush();
            writer.close();
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String json;
            StringBuilder response=new StringBuilder();
            while((json=reader.readLine())!=null){
                response.append(json);
            }
            JSONArray jsonArray=new JSONArray(response.toString());
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jo=jsonArray.getJSONObject(i);
                comment cm=new comment();
                cm.setNameofcommenter(jo.getString("name"));
                cm.setTimeofcommennt(jo.getString("time"));
                cm.setUsernameofcommenter(jo.getString("Username"));
                cm.setComment_body(jo.getString("comment_body"));
                cm.setPost_unique_id(jo.getString("post_unique_id"));
                commentlist.add(cm);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (pd.isShowing())
            pd.dismiss();
        if(commentlist.size()>0) {
            commentrv.setAdapter(new comment_adapter(commentlist));
        }else{
            Toast.makeText(context,"No Comments found",Toast.LENGTH_LONG).show();
        }
    }
}
