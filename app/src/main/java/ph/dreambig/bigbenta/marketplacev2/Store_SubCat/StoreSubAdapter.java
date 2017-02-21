
package ph.dreambig.bigbenta.marketplacev2.Store_SubCat;

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
 * Created by jenzoned on 4/4/16.
 */
public class StoreSubAdapter extends RecyclerView.Adapter<StoreSubAdapter.VisitUsViewHolder> {

    Context context;
    List<StoreSubData> data;

    public static class VisitUsViewHolder  extends RecyclerView.ViewHolder {
        ImageView HeaderImage;
        TextView ProductName;

        VisitUsViewHolder(View itemView) {
            super(itemView);

            HeaderImage = (ImageView)itemView.findViewById(R.id.product_img);
            ProductName = (TextView)itemView.findViewById(R.id.product_name);

        }
    }

    public StoreSubAdapter(List<StoreSubData> data){
        this.data = data;
    }

    @Override
    public VisitUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_cardview, parent, false);
        context = parent.getContext();
        return new VisitUsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VisitUsViewHolder holder, int position) {
        StoreSubData storeData = data.get(position);
        holder.ProductName.setText(storeData.getName());
        Glide.with(context)
                .load(storeData.getImgUrl())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ic_launcher)
                .into(holder.HeaderImage);
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

}
