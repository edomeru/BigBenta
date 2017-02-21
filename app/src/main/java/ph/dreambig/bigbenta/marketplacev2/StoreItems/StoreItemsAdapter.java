package ph.dreambig.bigbenta.marketplacev2.StoreItems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import ph.dreambig.bigbenta.marketplacev2.R;

import java.util.List;

/**
 * Created by edmeralarte on 01/09/2016.
 */

public class StoreItemsAdapter extends RecyclerView.Adapter<StoreItemsAdapter.VisitUsViewHolder> {

    Context context;
    List<StoreItemsData> data;



    public static class VisitUsViewHolder  extends RecyclerView.ViewHolder {
        ImageView HeaderImage;
        TextView ProductName;
        TextView Price;

        VisitUsViewHolder(View itemView) {
            super(itemView);

            HeaderImage = (ImageView)itemView.findViewById(R.id.product_img);
            ProductName = (TextView)itemView.findViewById(R.id.product_name);
            Price =(TextView) itemView.findViewById(R.id.price);

        }
    }

    public StoreItemsAdapter(List<StoreItemsData> data){
        this.data = data;
    }

    @Override
    public StoreItemsAdapter.VisitUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.storeitems_cardview, parent, false);
        context = parent.getContext();
        return new StoreItemsAdapter.VisitUsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoreItemsAdapter.VisitUsViewHolder holder, int position) {
        StoreItemsData visitUsData = data.get(position);
        holder.ProductName.setText(visitUsData.getName());
        Glide.with(context)
                .load(visitUsData.getImgUrl())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ic_launcher)
                .into(holder.HeaderImage);
        holder.Price.setText(visitUsData.getPrice());

    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

}
