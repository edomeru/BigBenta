package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by edmeralarte on 01/09/2016.
 */

public class AdsItemsAdapter extends RecyclerView.Adapter<AdsItemsAdapter.VisitUsViewHolder> {

    Context context;
    List<AdsItemsData> data;



    public static class VisitUsViewHolder  extends RecyclerView.ViewHolder {
        ImageView HeaderImage;
        TextView ProductName;
        TextView Price;

        VisitUsViewHolder(View itemView) {
            super(itemView);

            HeaderImage = (ImageView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.imageItem);
            ProductName = (TextView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.textItemName);
            Price =(TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.textPrice);

        }
    }

    public AdsItemsAdapter(List<AdsItemsData> data){
        this.data = data;
    }

    @Override
    public AdsItemsAdapter.VisitUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.adsitem_cardview, parent, false);
        context = parent.getContext();
        return new AdsItemsAdapter.VisitUsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdsItemsAdapter.VisitUsViewHolder holder, int position) {
        AdsItemsData visitUsData = data.get(position);
        holder.ProductName.setText(visitUsData.getName());
        Glide.with(context)
                .load(visitUsData.getImgUrl())
                .centerCrop()
                .dontAnimate()
                .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.ic_launcher)
                .into(holder.HeaderImage);
        holder.Price.setText(visitUsData.getPrice());

    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

}
