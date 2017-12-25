package usmanali.mobileworld.adapter_classes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

import usmanali.mobileworld.Activity_Files.comments_Activity;
import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.posts;

/**
 * Created by SAJIDCOMPUTERS on 6/10/2017.
 */

public class discussion_forum_adapter extends RecyclerView.Adapter<Holder> {
    ArrayList<posts> listofposts;
     android.support.v4.app.FragmentManager manager;Context context;
    public  discussion_forum_adapter(ArrayList<posts> postsArrayList,Context context){
        this.listofposts=postsArrayList;
        this.context=context;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
      View v=  LayoutInflater.from(parent.getContext()).inflate(R.layout.disscussion_forum_layout,parent,false);
        return new Holder(v);
    }
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final posts p= listofposts.get(position);
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(p.getTimeinmillis()),
                System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color1 = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRect(p.getName().substring(0, 1), color1);
        holder.profilepic.setImageDrawable(drawable);
     holder.timestamp.setText(timeAgo);
        holder.name.setText(p.getName());
        holder.post.setText(p.getPost());
        holder.comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,comments_Activity.class);
                intent.putExtra("post_id",p.getPost_id());
               context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listofposts.size();
    }
}
class Holder extends RecyclerView.ViewHolder {
    TextView name,timestamp,post;
    LinearLayout comments;
    ImageView profilepic;
    public Holder(View itemView) {
        super(itemView);
        name=(TextView) itemView.findViewById(R.id.name);
        timestamp=(TextView) itemView.findViewById(R.id.timeofpost);
        post=(TextView) itemView.findViewById(R.id.post);
        comments=(LinearLayout)itemView.findViewById(R.id.layout_comment);
        profilepic=(ImageView)itemView.findViewById(R.id.profilePic);
    }
}
