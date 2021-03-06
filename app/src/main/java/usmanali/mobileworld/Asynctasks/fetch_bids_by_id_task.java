package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import usmanali.mobileworld.adapter_classes.bidding_list_adapter;
import usmanali.mobileworld.model_classes.bidding;

/**
 * Created by SAJIDCOMPUTERS on 9/4/2017.
 */

public class fetch_bids_by_id_task extends AsyncTask<String,Void,Void> {
    ArrayList<bidding> biddingArrayList;
    RecyclerView bidslist;
    Context context;
    ProgressDialog pd;
    public fetch_bids_by_id_task(Context context,RecyclerView bidslist) {
        this.context = context;
        this.bidslist=bidslist;
        biddingArrayList=new ArrayList<>();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);

    }

    String mobile_id;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        mobile_id=strings[0];
        try {
            URL url=new URL("https://ameboid-snaps.000webhostapp.com/fetch_bids_by_id.php");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuilder information=new StringBuilder();
            information.append(URLEncoder.encode("mobile_id","UTF-8"));
            information.append("=");
            information.append(URLEncoder.encode(mobile_id,"UTF-8"));
            writer.write(information.toString());
            writer.flush();
            writer.close();
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String json;
            StringBuilder response=new StringBuilder();
            while((json=reader.readLine())!=null){
                response.append(json);
            }
            Log.d("response",response.toString());
            JSONArray jsonArray=new JSONArray(response.toString());
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jo=jsonArray.getJSONObject(i);
                 bidding bids =new bidding();
                bids.setName(jo.getString("Name"));
                bids.setBiddingprice(jo.getInt("bid_amount"));
                bids.setMobile_name(jo.getString("mobile_name"));
                bids.setMobile_id(jo.getInt("mobile_id"));
                bids.setDate(jo.getString("bid_date"));
                biddingArrayList.add(bids);
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
       if(pd.isShowing())
           pd.dismiss();
       if(biddingArrayList.size()<=0){
           Toast.makeText(context,"no bids are placed for this mobile",Toast.LENGTH_LONG).show();
       }else{
           bidslist.setAdapter(new bidding_list_adapter(biddingArrayList,context));
       }
    }
}
