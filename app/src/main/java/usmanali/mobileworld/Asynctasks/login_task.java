package usmanali.mobileworld.Asynctasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import usmanali.mobileworld.Activity_Files.MainActivity;
import usmanali.mobileworld.model_classes.Users;

/**
 * Created by SAJIDCOMPUTERS on 8/14/2017.
 */

public class login_task extends AsyncTask<String,Void,Void> {
ProgressDialog pd;
    public login_task(Context context) {
        this.context = context;
        customerArrayList=new ArrayList<>();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
    }

    Context context;
    ArrayList<Users> customerArrayList;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!pd.isShowing())
            pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        String Username=strings[0];
        String Password=strings[1];
        try {
          URL url = new URL("https://ameboid-snaps.000webhostapp.com/login.php");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            StringBuffer data=new StringBuffer();
            data.append(URLEncoder.encode("Username","UTF-8"));
            data.append("=");
            data.append(URLEncoder.encode(Username,"UTF-8"));
            data.append("&");
            data.append(URLEncoder.encode("Password","UTF-8"));
            data.append("=");
            data.append(URLEncoder.encode(Password,"UTF-8"));
            writer.write(data.toString());
            writer.flush();
            writer.close();
            // os.close();
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
                Users customer=new Users();
                customer.setName(jb.getString("Name"));
                customer.setUsername(jb.getString("Username"));
                customer.setEmail(jb.getString("Email"));
                customer.setAddress(jb.getString("Address"));
                customer.setPhone(jb.getString("Phone"));
                customer.setLogin_status(jb.getString("status"));
                customerArrayList.add(customer);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
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
        if(customerArrayList.size()!=0) {
            Toast.makeText(context, customerArrayList.get(0).getLogin_status(), Toast.LENGTH_LONG).show();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit().putString("Name",customerArrayList.get(0).getName()).commit();
            prefs.edit().putString("Username",customerArrayList.get(0).getUsername()).commit();
            prefs.edit().putString("Address",customerArrayList.get(0).getAddress()).commit();
            prefs.edit().putString("Email",customerArrayList.get(0).getEmail()).commit();
            prefs.edit().putString("Phone",customerArrayList.get(0).getPhone()).commit();
            prefs.edit().putBoolean("Status",true).commit();
            Intent intent=new Intent(context,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

        }else{
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            Toast.makeText(context,"Login Failed", Toast.LENGTH_LONG).show();
            prefs.edit().putBoolean("Status",false).commit();
        }
    }
}
