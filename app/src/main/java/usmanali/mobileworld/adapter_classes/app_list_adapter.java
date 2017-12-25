package usmanali.mobileworld.adapter_classes;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.apps;

/**
 * Created by SAJIDCOMPUTERS on 9/4/2017.
 */

public class app_list_adapter extends RecyclerView.Adapter<appsviewholder> {
    public app_list_adapter(ArrayList<apps> appsArrayList, Context context) {
        this.appsArrayList = appsArrayList;
        this.context = context;
    }

    ArrayList<apps> appsArrayList;
    Context context;
    @Override
    public appsviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.app_list_layout,parent,false);
        return new appsviewholder(v);
    }

    @Override
    public void onBindViewHolder(appsviewholder holder, int position) {
        final apps app=appsArrayList.get(position);
        holder.appname.setText(app.getApp_name());
        holder.appversion.setText(app.getApp_version());
        Picasso.with(context).load(app.getApp_pic_url()).into(holder.app_pic);
        holder.download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(app.getApp_url());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setTitle(app.getApp_name());
                request.setDescription("Downloading");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadmanager.enqueue(request);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appsArrayList.size();
    }
}
class appsviewholder extends RecyclerView.ViewHolder{
   TextView appname,appversion;
    ImageView app_pic;
    Button download_btn;
    public appsviewholder(View itemView) {
        super(itemView);
        appname=(TextView) itemView.findViewById(R.id.appnametxt);
        appversion=(TextView) itemView.findViewById(R.id.appversiontxt);
        app_pic=(ImageView) itemView.findViewById(R.id.appimg);
        download_btn=(Button) itemView.findViewById(R.id.download_btn);
    }
}