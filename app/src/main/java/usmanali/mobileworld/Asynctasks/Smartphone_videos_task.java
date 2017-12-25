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

import usmanali.mobileworld.adapter_classes.videos_list_adapter;
import usmanali.mobileworld.model_classes.video;

/**
 * Created by SAJIDCOMPUTERS on 8/30/2017.
 */

public class Smartphone_videos_task extends AsyncTask {
    ArrayList<video> videourllist;
    RecyclerView videoslist;
    StringBuffer sb;
    Context context;
    ProgressDialog pd;
    public Smartphone_videos_task(RecyclerView videoslist, Context context) {
        this.videoslist = videoslist;
        videourllist=new ArrayList<>();
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
    protected Object doInBackground(Object[] objects) {
        try {
            URL url = new URL("https://ameboid-snaps.000webhostapp.com/get_videos.php");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            BufferedReader reader =new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
             sb=new StringBuffer();
            String Json;
            while((Json=reader.readLine())!=null){
                sb.append(Json);
            }
            JSONArray jsonArray=new JSONArray(sb.toString());
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jb= jsonArray.getJSONObject(i);
                 video videourls=new video(jb.getString("video_url"));
                videourllist.add(videourls);
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if(pd.isShowing())
            pd.dismiss();
        if(videourllist.size()>0) {
            videoslist.setAdapter(new videos_list_adapter(videourllist));
        }else{
            Toast.makeText(context,"Video not Found",Toast.LENGTH_LONG).show();
        }
    }
}
