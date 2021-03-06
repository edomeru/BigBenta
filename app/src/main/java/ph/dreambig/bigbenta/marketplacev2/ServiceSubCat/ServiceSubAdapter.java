package ph.dreambig.bigbenta.marketplacev2.ServiceSubCat;

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

public class ServiceSubAdapter extends RecyclerView.Adapter<ServiceSubAdapter.VisitUsViewHolder> {

    Context context;
    List<ServiceSubData> data;

    public static class VisitUsViewHolder  extends RecyclerView.ViewHolder {
        ImageView HeaderImage;
        TextView ProductName;

        VisitUsViewHolder(View itemView) {
            super(itemView);

            HeaderImage = (ImageView)itemView.findViewById(R.id.product_img);
            ProductName = (TextView)itemView.findViewById(R.id.product_name);

        }
    }

    public ServiceSubAdapter(List<ServiceSubData> data){
        this.data = data;
    }

    @Override
    public ServiceSubAdapter.VisitUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_cardview, parent, false);
        context = parent.getContext();
        return new ServiceSubAdapter.VisitUsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServiceSubAdapter.VisitUsViewHolder holder, int position) {
        ServiceSubData visitUsData = data.get(position);
        holder.ProductName.setText(visitUsData.getName());
        Glide.with(context)
                .load(visitUsData.getImgUrl())
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
