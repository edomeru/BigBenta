
package ph.dreambig.bigbenta.marketplacev2.AdsSubCat;

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
 * Created by jenzoned on 4/4/16.
 */
public class AdsSubAdapter extends RecyclerView.Adapter<AdsSubAdapter.VisitUsViewHolder> {

    Context context;
    List<AdsSubData> data;

    public static class VisitUsViewHolder  extends RecyclerView.ViewHolder {
        ImageView HeaderImage;
        TextView ProductName;

        VisitUsViewHolder(View itemView) {
            super(itemView);

            HeaderImage = (ImageView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_img);
            ProductName = (TextView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_name);

        }
    }

    public AdsSubAdapter(List<AdsSubData> data){
        this.data = data;
    }

    @Override
    public VisitUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.ads_subcat_cardview, parent, false);
        context = parent.getContext();
        return new VisitUsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VisitUsViewHolder holder, int position) {
        AdsSubData storeData = data.get(position);
        holder.ProductName.setText(storeData.getName());
        Glide.with(context)
                .load(storeData.getImgUrl())
                .centerCrop()
                .dontAnimate()
                .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.ic_launcher)
                .into(holder.HeaderImage);
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

}
