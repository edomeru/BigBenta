package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.Categories_Ads;

import android.content.Context;
import android.graphics.Typeface;
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

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.VisitUsViewHolder> {

    Context context;
    List<AdsData> data;

    public static class VisitUsViewHolder  extends RecyclerView.ViewHolder {
        ImageView HeaderImage;
        TextView ProductName;

        VisitUsViewHolder(View itemView) {
            super(itemView);

            HeaderImage = (ImageView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_img);
            ProductName = (TextView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_name);
            Typeface tf = Typeface.createFromAsset(itemView.getContext().getAssets(),
                    "Bariol_Regular.otf");
            ProductName.setTypeface(tf);
        }
    }

    public  AdsAdapter (List<AdsData> data){
        this.data = data;
    }

    @Override
    public AdsAdapter.VisitUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.ads_cardview, parent, false);
        context = parent.getContext();
        return new AdsAdapter.VisitUsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdsAdapter.VisitUsViewHolder holder, int position) {
        AdsData visitUsData = data.get(position);
        holder.ProductName.setText(visitUsData.getName());
        Glide.with(context)
                .load(visitUsData.getImgUrl())
                .centerCrop()
                .dontAnimate()
                .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.bbpaula)
                .into(holder.HeaderImage);
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

}
