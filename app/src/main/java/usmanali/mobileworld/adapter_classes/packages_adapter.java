package usmanali.mobileworld.adapter_classes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import usmanali.mobileworld.Activity_Files.Packages_list_Activity;
import usmanali.mobileworld.R;

/**
 * Created by SAJIDCOMPUTERS on 7/22/2017.
 */

public class packages_adapter extends RecyclerView.Adapter <packageviewholder> {
ArrayList<Integer> logos;
Context context;
    public packages_adapter(ArrayList<Integer> logos,Context context) {
        this.logos = logos;
        this.context=context;
    }

    @Override
    public packageviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.operator_list_layout,parent,false);
        return new packageviewholder(v);
    }

    @Override
    public void onBindViewHolder(packageviewholder holder, final int position) {

      holder.opteratorslogo.setImageResource(logos.get(position));
        holder.operatorcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(position==0){
                   Intent i=new Intent(context,Packages_list_Activity.class);
                   i.putExtra("Operator","telenor");
                   i.putExtra("position",position);
                   context.startActivity(i);
               }else if(position==1){
                   Intent i=new Intent(context,Packages_list_Activity.class);
                   i.putExtra("Operator","ufone");
                   context.startActivity(i);
               }else if(position==2){
                   Intent i=new Intent(context,Packages_list_Activity.class);
                   i.putExtra("Operator","Zong");
                   context.startActivity(i);
               }else if(position==3){
                   Intent i=new Intent(context,Packages_list_Activity.class);
                   i.putExtra("Operator","Mobilink");
                   context.startActivity(i);
               }else if (position==4){
                   Intent i=new Intent(context,Packages_list_Activity.class);
                   i.putExtra("Operator","Warid");
                   context.startActivity(i);
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return logos.size();
    }
}
class packageviewholder extends RecyclerView.ViewHolder {
    ImageView opteratorslogo;
    CardView operatorcard;
    public packageviewholder(View itemView) {
        super(itemView);
        opteratorslogo=(ImageView) itemView.findViewById(R.id.operatorimage);
        operatorcard=(CardView) itemView.findViewById(R.id.operatorcard);
    }
}
