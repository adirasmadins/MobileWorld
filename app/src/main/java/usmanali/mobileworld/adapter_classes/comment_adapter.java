package usmanali.mobileworld.adapter_classes;

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
import usmanali.mobileworld.model_classes.comment;

/**
 * Created by SAJIDCOMPUTERS on 9/2/2017.
 */

public class comment_adapter extends RecyclerView.Adapter<commentsviewholder> {
    public comment_adapter(ArrayList<comment> commentArrayList) {
        this.commentArrayList = commentArrayList;
    }

    ArrayList<comment> commentArrayList;

    @Override
    public commentsviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_layout,parent,false);
        return new commentsviewholder(v);
    }

    @Override
    public void onBindViewHolder(commentsviewholder holder, int position) {
            comment cm=commentArrayList.get(position);
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(cm.getTimeofcommennt()),
                System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color1 = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRect(cm.getNameofcommenter().substring(0, 1), color1);
        holder.profilepic.setImageDrawable(drawable);
        holder.comment.setText(cm.getComment_body());
        holder.name.setText(cm.getNameofcommenter());
        holder.timeofpost.setText(String.valueOf(timeAgo));
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }
}
class commentsviewholder extends RecyclerView.ViewHolder{
 ImageView profilepic;
    TextView name,comment,timeofpost;
    public commentsviewholder(View itemView) {
        super(itemView);
        name=(TextView)itemView.findViewById(R.id.name);
        comment=(TextView) itemView.findViewById(R.id.post);
        timeofpost=(TextView) itemView.findViewById(R.id.timeofpost);
        profilepic=(ImageView) itemView.findViewById(R.id.profilePic);
    }
}
