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

import usmanali.mobileworld.Activity_Files.acessories_detail_activity;
import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.acessories;

/**
 * Created by SAJIDCOMPUTERS on 9/9/2017.
 */

public class show_acessories_adapter extends RecyclerView.Adapter<acessoriesviewholder> {
    public show_acessories_adapter(ArrayList<acessories> acessoriesArrayList, Context context) {
        this.acessoriesArrayList = acessoriesArrayList;
        this.context = context;
    }

    ArrayList<acessories> acessoriesArrayList;
    Context context;
    @Override
    public acessoriesviewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_layout,viewGroup,false);
        return new acessoriesviewholder(v);
    }

    @Override
    public void onBindViewHolder(acessoriesviewholder acessoriesviewholder, int i) {
        final acessories acessorie=acessoriesArrayList.get(i);
        acessoriesviewholder.name_of_acessorie.setText(acessorie.getAcessories_name());
        acessoriesviewholder.price_of_acessorie.setText("Rs "+String.valueOf(acessorie.getAcessories_price()));
        Picasso.with(context).load(acessorie.getAcessories_image()).into(acessoriesviewholder.image_of_acessorie);
        acessoriesviewholder.productcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,acessories_detail_activity.class);
                i.putExtra("acessories_name",acessorie.getAcessories_name());
                i.putExtra("acessories_brand",acessorie.getAcessories_brand());
                i.putExtra("acessories_price",acessorie.getAcessories_price());
                i.putExtra("acessories_image",acessorie.getAcessories_image());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return acessoriesArrayList.size();
    }
}
class acessoriesviewholder extends RecyclerView.ViewHolder{
TextView name_of_acessorie,price_of_acessorie;
    ImageView image_of_acessorie;
    CardView productcard;
    public acessoriesviewholder(View itemView) {
        super(itemView);
        name_of_acessorie=(TextView)itemView.findViewById(R.id.productnametxt);
        price_of_acessorie=(TextView) itemView.findViewById(R.id.productpricetxt);
        image_of_acessorie=(ImageView) itemView.findViewById(R.id.productimage);
        productcard=(CardView) itemView.findViewById(R.id.productscard);
    }
}