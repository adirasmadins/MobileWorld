package usmanali.mobileworld.adapter_classes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.bidding;

/**
 * Created by shaban on 8/10/2017.
 */

public class bidding_list_adapter extends RecyclerView.Adapter<biddingviewholder> {
    ArrayList<bidding> biddinglist;
    Context c;

    public bidding_list_adapter(ArrayList<bidding> biddinglist, Context c) {
        this.biddinglist = biddinglist;
        this.c = c;
    }

    @Override
    public biddingviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.bidding_layout,parent,false);
        return new biddingviewholder(v);
    }

    @Override
    public void onBindViewHolder(biddingviewholder holder, int position) {
bidding bid=biddinglist.get(position);
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(bid.getDate()),
                System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color1 = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRect(bid.getName().substring(0, 1), color1);
        holder.profilepic.setImageDrawable(drawable);
        holder.biddername.setText(bid.getName());
        holder.biddate.setText(timeAgo);
        holder.bidprice.setText("RS "+String.valueOf(bid.getBiddingprice()));
    }

    @Override
    public int getItemCount() {
        return biddinglist.size();
    }
}
class biddingviewholder extends RecyclerView.ViewHolder{
TextView bidprice,biddername,biddate;
    ImageView profilepic;
    public biddingviewholder(View itemView) {
        super(itemView);
        bidprice=(TextView) itemView.findViewById(R.id.bidprice);
        biddername=(TextView) itemView.findViewById(R.id.nameofbidder);
        biddate=(TextView) itemView.findViewById(R.id.biddate);
        profilepic=(ImageView) itemView.findViewById(R.id.profilePic);
    }
}