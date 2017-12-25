package usmanali.mobileworld.adapter_classes;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import usmanali.mobileworld.R;
import usmanali.mobileworld.model_classes.dbhelper;
import usmanali.mobileworld.model_classes.mobiles;

/**
 * Created by SAJIDCOMPUTERS on 7/6/2017.
 */

public class cartlistadapter extends RecyclerView.Adapter<cartviewholder> {
    cartviewholder viewholder;
    public cartlistadapter(ArrayList<mobiles> productsArrayList, Context context) {
        this.productsArrayList = productsArrayList;
        this.context = context;
    }

    ArrayList<mobiles> productsArrayList;
    Context context;


    @Override
    public cartviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcartlistlayout,parent,false);
        return new cartviewholder(v);
    }

    @Override
    public void onBindViewHolder(final cartviewholder holder, final int position) {
        this.viewholder=holder;
      final mobiles pro=productsArrayList.get(position);
        final mobiles p=productsArrayList.get(position);
        holder.price.setText("Rs "+p.getMobile_price());
        Picasso.with(context).load(p.getMobile_image()).into(holder.productimg);
        holder.name.setText(p.getMobile_name());
        holder.quantity.setText("Quantity x"+p.getQuantity());
        holder.excludefromcartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbhelper mydb = new dbhelper(context);
                Integer rows = mydb.delete(String.valueOf(p.getId()));
                if (rows > 0) {
                    Toast.makeText(context,"Item Removed From Cart",Toast.LENGTH_LONG).show();
                    productsArrayList.remove(holder.getAdapterPosition());
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(holder.getAdapterPosition(),getItemCount());
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context,"Item not Removed From Cart",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }
}
class cartviewholder extends RecyclerView.ViewHolder {
    TextView price,quantity,name;
    Button excludefromcartbtn;
    ImageView productimg;
    public cartviewholder(View itemView) {
        super(itemView);
        price=(TextView)itemView.findViewById(R.id.productPrice);
        name=(TextView)itemView.findViewById(R.id.productName);
        excludefromcartbtn=(Button)itemView.findViewById(R.id.excludefromcart);
        productimg=(ImageView) itemView.findViewById(R.id.productImage);
        quantity=(TextView) itemView.findViewById(R.id.productquantity);
    }
}