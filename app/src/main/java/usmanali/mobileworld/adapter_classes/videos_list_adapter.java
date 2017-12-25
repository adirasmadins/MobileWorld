package usmanali.mobileworld.adapter_classes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.ArrayList;

import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.video;

/**
 * Created by SAJIDCOMPUTERS on 8/12/2017.
 */

public class videos_list_adapter extends RecyclerView.Adapter<videolistviewholder> {
    public videos_list_adapter(ArrayList<video> videourllist) {
        this.videourllist = videourllist;
    }

    ArrayList<video> videourllist;
    @Override
    public videolistviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_adapter,parent,false);
        return new videolistviewholder(v);
    }

    @Override
    public void onBindViewHolder(videolistviewholder holder, int position) {
        video videourls=videourllist.get(position);
      holder.videoplayer.loadData(videourls.getVideourl(),"text/html","utf-8");
    }

    @Override
    public int getItemCount() {
        return videourllist.size();
    }
}

class videolistviewholder extends RecyclerView.ViewHolder{
   WebView videoplayer;
    public videolistviewholder(View itemView) {
        super(itemView);
        videoplayer=(WebView) itemView.findViewById(R.id.videoplayer);
        videoplayer.getSettings().setJavaScriptEnabled(true);
        videoplayer.setWebChromeClient(new WebChromeClient(){
        });
    }
}
