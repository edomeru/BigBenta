
package ph.dreambig.bigbenta.marketplacev2.ServiceItemDetail;

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
 * Created by jenzoned on 3/31/16.
 */
public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.CardViewHolder>{

    Context context;
    List<RelatedData> data;

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView ProductName;
        ImageView ProductImage;

        CardViewHolder(View itemView) {
            super(itemView);
            ProductImage = (ImageView)itemView.findViewById(R.id.product_img);
            ProductName = (TextView)itemView.findViewById(R.id.product_name);
        }
    }

    public RelatedAdapter(List<RelatedData> data){
        this.data = data;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.relatedcardview, parent, false);
        context = parent.getContext();
        return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        RelatedData movie = data.get(position);
        holder.ProductName.setText(movie.getProductName());
        Glide.with(context)
                .load(movie.getUrlImage())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.ic_launcher)
                .into(holder.ProductImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}