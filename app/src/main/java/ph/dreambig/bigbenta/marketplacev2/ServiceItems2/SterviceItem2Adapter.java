package ph.dreambig.bigbenta.marketplacev2.ServiceItems2;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.ServiceItemDetail.ServiceItemDetail;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edmer on 26/10/2016.
 */

public class SterviceItem2Adapter extends RecyclerView.Adapter<SterviceItem2Adapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;
    ShareDialog shareDialog;
    CallbackManager callbackManager;
    private final static int FADE_DURATION = 1000;
    List<ServiceResponse> superHeroes = new ArrayList<>();

    public SterviceItem2Adapter(List<ServiceResponse> superHeroes, Context context){
        super();
        //Getting all the superheroes
        this.superHeroes = superHeroes;
        this.context = context;
        FacebookSdk.sdkInitialize(context.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog((MainActivity) context);
    }

    @Override
    public SterviceItem2Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.serviceitem_cardview, parent, false);
        SterviceItem2Adapter.ViewHolder viewHolder = new SterviceItem2Adapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SterviceItem2Adapter.ViewHolder holder,final int position) {

     final   ServiceResponse latestitems=  superHeroes.get(position);

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(latestitems.getData().get(position).getSmDomain()+latestitems.getData().get(position).getSmGroup()+latestitems.getData().get(position).getSmName()+"."+latestitems.getData().get(position).getSmExtension(), ImageLoader.getImageListener(holder.imageView, R.drawable.bbpaula, android.R.drawable.ic_dialog_alert));

       // holder.imageView.setImageUrl(latestitems.getData().get(position).getSmDomain()+latestitems.getData().get(position).getSmGroup()+latestitems.getData().get(position).getSmName()+"."+latestitems.getData().get(position).getSmExtension(), imageLoader);
        holder.product_name.setText(latestitems.getData().get(position).getBusinessName());
        holder.pprice.setText("₱"+String.valueOf(latestitems.getData().get(position).getRate()));
        holder.store_name.setText(latestitems.getData().get(position).getCatItemName());


        Glide.with(context)
                .load(latestitems.getData().get(position).getSmDomain()+latestitems.getData().get(position).getSmGroup()+latestitems.getData().get(position).getSmName()+"."+latestitems.getData().get(position).getSmExtension())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.bbpaula)
                .into(holder.imageView);

        // holder.store_name.setText(latestitems.getclassif_date());
        // holder.product_name.setText(latestitems.getclassif_name());

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(latestitems.getData().get(position).getBusinessName().toString())
                        .setContentDescription(latestitems.getData().get(position).getExpertise().toString())
                        .setContentUrl(Uri.parse(Constants.BIGBENTA_ITEM_LINK ))
                               // latestitems.getData().getClassified().getClassified().get(position).getId().toString() + "/" + latestitems.getData().getClassified().getClassified().get(position).getTitle().toString()))
                        .setImageUrl(Uri.parse(latestitems.getData().get(position).getSmDomain()+latestitems.getData().get(position).getSmGroup()+latestitems.getData().get(position).getSmName()+"."+latestitems.getData().get(position).getSmExtension()))
                        .build();

                shareDialog.show(linkContent);
            }
        });

        holder.scart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fr=new ServiceItemDetail();
                FragmentManager fm=((MainActivity) context).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("srvce_id", latestitems.getData().get(position).getSrvcId().toString());
                fr.setArguments(args);
                ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fr=new ServiceItemDetail();
                FragmentManager fm=((MainActivity) context).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("srvce_id", latestitems.getData().get(position).getSrvcId().toString());
                args.putString("cat_name", latestitems.getData().get(position).getCatItemName().toString());

                fr.setArguments(args);
                ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        setScaleAnimation(holder.mail);
        setScaleAnimation(holder.share);
        setScaleAnimation(holder.scart);
        setScaleAnimation(holder.pprice);

        setFadeAnimation(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;

        public TextView product_name;
        public TextView pprice;
        public TextView store_name;
        public TextView classif_date;
        public TextView classif_place;

        public ImageView share,scart,mail;

        public ViewHolder(View itemView) {
            super(itemView);
            mail= (ImageView) itemView.findViewById(R.id.mail);
            scart= (ImageView) itemView.findViewById(R.id.scart);
            share= (ImageView) itemView.findViewById(R.id.share);
            imageView = (ImageView) itemView.findViewById(R.id.product_img);
            product_name= (TextView) itemView.findViewById(R.id.product_name);
            pprice= (TextView) itemView.findViewById(R.id.price);
            store_name= (TextView) itemView.findViewById(R.id.store_name);
            //classif_place= (TextView) itemView.findViewById(R.id.place);
            Typeface Caviarr = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.product_name.setTypeface(Caviarr);
            this.pprice.setTypeface(Caviarr);
            this.store_name.setTypeface(Caviarr);



        }
    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
}