package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import usmanali.mobileworld.adapter_classes.discussion_forum_adapter;
import usmanali.mobileworld.model_classes.posts;

/**
 * Created by SAJIDCOMPUTERS on 8/31/2017.
 */

public class fetch_discussion_forum_posts_task extends AsyncTask {
    ArrayList<posts> postslist;
    ProgressDialog pd;
    public fetch_discussion_forum_posts_task(Context context,RecyclerView postsrv) {
        this.context = context;
        this.postsrv=postsrv;
        postslist=new ArrayList<>();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    Context context;
    RecyclerView postsrv;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("https://ameboid-snaps.000webhostapp.com/fetch_posts.php");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
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
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jb= jsonArray.getJSONObject(i);
              posts post=new posts();
                post.setName(jb.getString("Name"));
                post.setUsername(jb.getString("Username"));
                post.setPost(jb.getString("post"));
                post.setPost_id(jb.getInt("post_id"));
                post.setTimeinmillis(jb.getString("post_datetime"));
                postslist.add(post);
            }
        }catch(Exception e){}
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(pd.isShowing())
            pd.dismiss();
        if(postslist.size()>0) {
            postsrv.setAdapter(new discussion_forum_adapter(postslist, context));
        }else{
            Toast.makeText(context,"No Posts Found",Toast.LENGTH_LONG).show();
        }
    }
}
