package usmanali.mobileworld.adapter_classes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import usmanali.mobileworld.Activity_Files.used_phone_detail_Activity;
import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.usedmobiles;

/**
 * Created by SAJIDCOMPUTERS on 9/1/2017.
 */

public class show_used_mobile_adapter extends RecyclerView.Adapter<usedmobileviewholder> {
    Context context;
    ArrayList<usedmobiles> usedmobilesArrayList;

    public show_used_mobile_adapter(Context context, ArrayList<usedmobiles> usedmobilesArrayList) {
        this.context = context;
        this.usedmobilesArrayList = usedmobilesArrayList;
    }

    @Override
    public usedmobileviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.used_mobile_list_layout,parent,false);
        return new usedmobileviewholder(v);
    }

    @Override
    public void onBindViewHolder(usedmobileviewholder holder, int position) {
        final usedmobiles usedmobile =usedmobilesArrayList.get(position);
       holder.productnametxt.setText(usedmobile.getMobile_name());
        holder.productpricetxt.setText("Rs "+String.valueOf(usedmobile.getMobile_price()));
        Picasso.with(context).load(usedmobile.getMobile_image()).into(holder.productimage);
        holder.productcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,used_phone_detail_Activity.class);
                intent.putExtra("mobilename",usedmobile.getMobile_name());
                intent.putExtra("mobilebrand",usedmobile.getMobile_brand());
                intent.putExtra("mobileimage",usedmobile.getMobile_image());
                intent.putExtra("price",usedmobile.getMobile_price());
                intent.putExtra("mobileusage",usedmobile.getMobile_usage_period());
                intent.putExtra("mobiledescription",usedmobile.getMobile_description());
                intent.putExtra("mobilewarrantyleft",usedmobile.getMobile_warranty());
                intent.putExtra("adpostername",usedmobile.getName_of_publisher());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usedmobilesArrayList.size();
    }
}
class usedmobileviewholder extends RecyclerView.ViewHolder{
    ImageView productimage;
    TextView  productnametxt,productpricetxt;
    CardView productcard;
    public usedmobileviewholder(View itemView) {
        super(itemView);
        productimage=(ImageView)itemView.findViewById(R.id.productimage);
        productnametxt=(TextView)itemView.findViewById(R.id.productnametxt);
        productpricetxt=(TextView) itemView.findViewById(R.id.productpricetxt);
        productcard=(CardView) itemView.findViewById(R.id.productscard);
    }
}