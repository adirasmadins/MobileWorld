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
import java.util.List;

import usmanali.mobileworld.Activity_Files.Phone_Details;
import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.mobiles;


/**
 * Created by SAJIDCOMPUTERS on 3/7/2017.
 */

public class adapter extends RecyclerView.Adapter<viewholder> {
    List<mobiles> productslist;Context c;
    public adapter(ArrayList<mobiles> listofproducts,Context ctx){
        this.productslist=listofproducts;
        this.c=ctx;
    }
    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(final viewholder holder, int position) {
final mobiles product=productslist.get(position);
        holder.name.setText(product.getMobile_name());
        if(product.getMobile_image()!=null) {
            Picasso.with(c).load(product.getMobile_image()).into(holder.productimage);
        }
        holder.productscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(c,Phone_Details.class);
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
                c.startActivity(i);
            }
        });
        holder.price.setText("Rs "+String.valueOf(product.getMobile_price()));
    }
    @Override
    public int getItemCount() {
        return productslist.size();
    }
}

class viewholder extends RecyclerView.ViewHolder{
TextView name,price;
    ImageView productimage;
    CardView productscard;
    public viewholder(View itemView) {

        super(itemView);
        name=(TextView)itemView.findViewById(R.id.productnametxt);
        price=(TextView)itemView.findViewById(R.id.productpricetxt);
        productimage=(ImageView)itemView.findViewById(R.id.productimage);
        productscard=(CardView)itemView.findViewById(R.id.productscard);
    }
}