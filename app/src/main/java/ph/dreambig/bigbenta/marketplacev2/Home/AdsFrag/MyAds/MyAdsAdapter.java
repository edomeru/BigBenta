package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import ph.dreambig.bigbenta.marketplacev2.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class MyAdsAdapter extends RecyclerView.Adapter<MyAdsAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;
    SessionManager sessionManager;

    List<MyAdsResponse> superHeroes = new ArrayList<>();

    public MyAdsAdapter(List<MyAdsResponse> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.myadsitem_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MyAdsResponse latestitems=  superHeroes.get(position);

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(latestitems.getimage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
//
//        holder.imageView.setImageUrl(latestitems.getimage(), imageLoader);

        holder.sellerName.setText(latestitems.getData().getClassified().getClassified().get(position).getAboutSeller().get(0).getFirstName().toString()+" "+latestitems.getData().getClassified().getClassified().get(position).getAboutSeller().get(0).getLastName().toString());
        holder.pprice.setText(latestitems.getData().getClassified().getClassified().get(position).getPrice().toString());
        holder.classif_place.setText(latestitems.getData().getClassified().getClassified().get(position).getLocation().get(0).getRegionName().toString());
       if(latestitems.getData().getClassified().getClassified().get(position).getCreatedDate()==null) {
           holder.classif_date.setText("");
       }else {
           holder.classif_date.setText(latestitems.getData().getClassified().getClassified().get(position).getCreatedDate().toString());
       }
        holder.product_name.setText(latestitems.getData().getClassified().getClassified().get(position).getTitle().toString());
        Glide.with(context)
                .load(latestitems.getData().getClassified().getClassified().get(position).getImage().get(0).getImageMd().toString())
                .centerCrop()
                .dontAnimate()
                .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.bbpaula)
                .into(holder.imageView);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("ACTION");
                builder.setItems(new CharSequence[]
                                { "Sold", "Activate", "Deactivate"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        Toast.makeText(context, "SOLD",Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        Toast.makeText(context, "Activated", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        Toast.makeText(context, "Deactivated", Toast.LENGTH_SHORT).show();
                                        break;

                                }
                            }
                        });
                builder.create().show();
            }
        });

       if( latestitems.getData().getClassified().getClassified().get(position).getStatus().toString().equals("4")){
           holder.status.setText("Deactivated");
       }else if(latestitems.getData().getClassified().getClassified().get(position).getStatus().toString().equals("3")) {
           holder.status.setText("Activated");
       }

    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView status;
        public TextView product_name;
        public TextView pprice;
        public TextView store_name;
        public TextView classif_date;
        public TextView classif_place;
        public TextView  sellerName;
        public ImageView overflow;


        public ViewHolder(View itemView) {
            super(itemView);

            status= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.status);
            overflow = (ImageView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.overflow);
            imageView = (ImageView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.imageItem);
            sellerName= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.sellerName);
            product_name= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.textItemName);
            pprice= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.textPrice);
            classif_date= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.date_posted);
            classif_place= (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.place);
            Typeface Caviarr = Typeface.createFromAsset(itemView.getContext().getAssets(), "arialbold.ttf");
            Typeface atwriter = Typeface.createFromAsset(itemView.getContext().getAssets(), "atwriter.ttf");
            this.product_name.setTypeface(atwriter);
            this.pprice.setTypeface(atwriter);
            this.classif_date.setTypeface(Caviarr);
            this.classif_place.setTypeface(Caviarr);

        }
    }
}