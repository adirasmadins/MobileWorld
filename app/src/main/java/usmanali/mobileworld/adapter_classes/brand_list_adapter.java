package usmanali.mobileworld.adapter_classes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import usmanali.mobileworld.Activity_Files.show_mobiles_by_brand_Activity;
import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.brands;

/**
 * Created by SAJIDCOMPUTERS on 9/5/2017.
 */

public class brand_list_adapter extends RecyclerView.Adapter<brandsviewholder> {
    ArrayList<brands> brandsArrayList;
    Context context;

    public brand_list_adapter(ArrayList<brands> brandsArrayList, Context context) {
        this.brandsArrayList = brandsArrayList;
        this.context = context;
    }

    @Override
    public brandsviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_list_layout,parent,false);
        return new brandsviewholder(v);
    }

    @Override
    public void onBindViewHolder(brandsviewholder holder, int position) {
       final brands brand=brandsArrayList.get(position);
       holder.brandname.setText(brand.getBrandname());
        holder.brandimg.setImageResource(brand.getBrandimg());
        holder.brandimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,show_mobiles_by_brand_Activity.class);
                intent.putExtra("brand",brand.getBrandname());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandsArrayList.size();
    }
}
class brandsviewholder extends RecyclerView.ViewHolder {
    ImageView brandimg;
    TextView  brandname;
    public brandsviewholder(View itemView) {
        super(itemView);
        brandimg=(ImageView) itemView.findViewById(R.id.brandimg);
        brandname=(TextView) itemView.findViewById(R.id.brandname);
    }
}