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

import usmanali.mobileworld.Activity_Files.Phone_Details;
import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.mobiles;

/**
 * Created by SAJIDCOMPUTERS on 9/5/2017.
 */

public class mobile_by_brand_adapter extends RecyclerView.Adapter<mobilebybrandviewholder> {
    public mobile_by_brand_adapter(ArrayList<mobiles> mobilesArrayList, Context context) {
        this.mobilesArrayList = mobilesArrayList;
        this.context = context;
    }

    ArrayList<mobiles> mobilesArrayList;
    Context context;

    @Override
    public mobilebybrandviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mobiles_by_brand_list_layout,parent,false);
        return new mobilebybrandviewholder(v);
    }

    @Override
    public void onBindViewHolder(mobilebybrandviewholder holder, int position) {
        final mobiles product=mobilesArrayList.get(position);
        holder.productnametxt.setText(product.getMobile_name());
        holder.productpricetxt.setText("Rs "+String.valueOf(product.getMobile_price()));
        Picasso.with(context).load(product.getMobile_image()).into(holder.productimage);
        holder.productscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,Phone_Details.class);
                i.putExtra("mobile_name",product.getMobile_name());
                i.putExtra("mobile_price",product.getMobile_price());
                i.putExtra("mobile_brand",product.getMobile_brand());
                i.putExtra("mobile_ram",product.getMobile_ram());
                i.putExtra("mobile_resolution",product.getMobile_resolution());
                i.putExtra("mobile_storage",product.getMobile_storage());
                i.putExtra("mobile_simtype",product.getMobile_simtype());
                i.putExtra("mobile_image",product.getMobile_image());
                i.putExtra("mobile_frontcamera",product.getMobile_frontcamera());
                i.putExtra("mobile_backcamera",product.getMobile_backcamera());
                i.putExtra("mobile_processor",product.getMobile_processor());
                i.putExtra("mobile_os",product.getMobile_os());
                i.putExtra("mobile_id",product.getMobile_id());
                i.putExtra("mobile_battery",product.getMobile_battery());
                i.putExtra("mobile_screensize",product.getMobile_screensize());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mobilesArrayList.size();
    }
}
class mobilebybrandviewholder extends RecyclerView.ViewHolder {
    TextView productnametxt,productpricetxt;
    ImageView productimage;
    CardView productscard;
    public mobilebybrandviewholder(View itemView) {
        super(itemView);
        productnametxt=(TextView)itemView.findViewById(R.id.productnametxt);
        productpricetxt=(TextView)itemView.findViewById(R.id.productpricetxt);
        productimage=(ImageView) itemView.findViewById(R.id.productimage);
        productscard=(CardView) itemView.findViewById(R.id.productscard);
    }
}