package ph.dreambig.bigbenta.marketplacev2.AdsSubCat;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class LatestClassifiedAdapter extends RecyclerView.Adapter<LatestClassifiedAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;
    SessionManager sessionManager;

    List<AdsSubCatRetrofitResponse> superHeroes = new ArrayList<>();

    public LatestClassifiedAdapter(List<AdsSubCatRetrofitResponse> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ads_subcat_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        AdsSubCatRetrofitResponse latestitems=  superHeroes.get(position);

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(latestitems.getimage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

//        holder.imageView.setImageUrl(latestitems.getimage(), imageLoader);
//
//        holder.pprice.setText("Php"+latestitems.getprice()+".00");
//        holder.classif_place.setText(latestitems.getclassif_place());
//        holder.classif_date.setText(latestitems.getclassif_date());
        holder.product_name.setText(latestitems.getData().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public NetworkImageView imageView;

        public TextView product_name;
        public TextView pprice;
        public TextView store_name;
        public TextView classif_date;
        public TextView classif_place;

        public ViewHolder(View itemView) {
            super(itemView);

//            imageView = (NetworkImageView) itemView.findViewById(R.id.classif_image);
            product_name= (TextView) itemView.findViewById(R.id.product_name);
//            pprice= (TextView) itemView.findViewById(R.id.classif_price);
//            classif_date= (TextView) itemView.findViewById(R.id.classif_date);
//            classif_place= (TextView) itemView.findViewById(R.id.classif_place);
            Typeface Caviarr = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.product_name.setTypeface(Caviarr);
//            this.pprice.setTypeface(Caviarr);
//            this.classif_date.setTypeface(Caviarr);
//            this.classif_place.setTypeface(Caviarr);

        }
    }
}