package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyOrderHistory;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import ph.dreambig.bigbenta.marketplacev2.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class OrderHistAdapter extends RecyclerView.Adapter<OrderHistAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    SessionManager sessionManager;

    List<OrderHistResponse> superHeroes = new ArrayList<>();
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        ImageView shineButton;
       TextView sale;
        public TextView product_title;
        public TextView pprice;
        public TextView store_name;
        public TextView classif_date;
        public TextView classif_place;
        public TextView orig_price;
        public TextView statuss;
        public TextView  statustext;

        public ViewHolder(View itemView) {
            super(itemView);
            statustext= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.statustext);
            statuss= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.statuss);
            orig_price = (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_price);
            sale = (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.sale);
            shineButton = (ImageView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.shine_button);
            imageView = (ImageView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_img);
            product_title= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_name);
            pprice= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.unit_price);
            store_name= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_quantity);
            //classif_place= (TextView) itemView.findViewById(R.id.place);
            Typeface Abold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.store_name.setTypeface(Abold);
            //this.classif_place.setTypeface(Caviarr);
            Typeface Caviarr = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.pprice.setTypeface(Caviarr);
            this.product_title.setTypeface(Caviarr);
            this.statustext.setTypeface(Caviarr);
            this.statuss.setTypeface(Caviarr);


        }
    }
    public OrderHistAdapter(List<OrderHistResponse> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.ord_hist_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
 String stat="";
       final OrderHistResponse latestitems=  superHeroes.get(position);

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(latestitems.getimage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //holder.imageView.setImageUrl(latestitems.getimage(), imageLoader);
       if(latestitems.getData().get(position).getBuyerPaymentStatus()!=null) {
           if (latestitems.getData().get(position).getBuyerPaymentStatus().toString().equals("6")) {
               stat = "PENDING";
           } else if (latestitems.getData().get(position).getBuyerPaymentStatus().toString().equals("1")) {
               stat = "PAID";
           } else {
               stat = "ABANDONED";
           }
       }

        holder. statuss.setText(stat);

       if( latestitems.getData().get(position).getItems().size()!=0){
           holder. orig_price.setText("₱"+latestitems.getData().get(position).getItems().get(0).getTotalPrice().toString());
           holder.product_title.setText(latestitems.getData().get(position).getItems().get(0).getName().toString());
           holder.pprice.setText("₱"+latestitems.getData().get(position).getItems().get(0).getPrice().toString());
           holder.store_name.setText(latestitems.getData().get(position).getItems().get(0).getQuantity().toString());
//        if (holder.shineButton != null)
//            holder.shineButton.init((Activity)context);
//       if(latestitems.getData().getStores().getStores().get(position).getIsSale()==1) {
//           holder.sale.setVisibility(View.VISIBLE);
//           holder.orig_price.setText(latestitems.getData().getStores().getStores().get(position).getOriginalPrice().toString());
//           holder.orig_price.setVisibility(View.VISIBLE);
//       }


//           holder.shineButton.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View view) {
//                   Log.e(TAG, "click");
//               }
//           });
           Glide.with(context)
                   .load(latestitems.getData().get(position).getItems().get(0).getImages().get(0).getImageSm().toString())
                   .centerCrop()
                   .dontAnimate()
                   .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.bbpaula)
                   .into(holder.imageView);

           holder.imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
//                Fragment fr=new StoreItemDetail();
//                FragmentManager fm=((MainActivity) context).getSupportFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                Bundle args = new Bundle();
//                args.putString("item_id", latestitems.getData().getStores().getStores().get(position).getId().toString());
//                fr.setArguments(args);
//                ft.replace(R.id.containerView, fr);
//                ft.addToBackStack(null);
//                ft.commit();

               }
           });
       }

    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }


}