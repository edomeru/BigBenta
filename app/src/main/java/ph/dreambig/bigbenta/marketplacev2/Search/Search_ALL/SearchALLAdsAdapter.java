package ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import ph.dreambig.bigbenta.marketplacev2.AdsItemDetail.AdsItemDetail;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyFavorites_Ads.AdsFavData;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.Search.SearchActivitySample;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class SearchALLAdsAdapter extends RecyclerView.Adapter<SearchALLAdsAdapter.ViewHolder> {
    AdsFavData addfav;
    Realm realm;
    private ImageLoader imageLoader;
    private Context context;
    SessionManager sessionManager;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    List<SearchALLResponse> superHeroes = new ArrayList<>();
    String cityName="";

    public SearchALLAdsAdapter(List<SearchALLResponse> superHeroes, Context context){
        super();
        FacebookSdk.sdkInitialize(context.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog((SearchActivitySample) context);
        this.superHeroes = superHeroes;
        this.context = context;
        sessionManager = new SessionManager(context);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_search_adsitem_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

     final   SearchALLResponse latestitems=  superHeroes.get(position);

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(latestitems.getimage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
//
//        holder.imageView.setImageUrl(latestitems.getimage(), imageLoader);

        holder.pprice.setText("₱"+latestitems.getData().getClassified().getClassified().get(position).getPrice().toString());

        if(latestitems.getData().getClassified().getClassified().get(position).getLocation()==null) {
            holder.classif_place.setText("");
            cityName="";
        }else {
            holder.classif_place.setText(latestitems.getData().getClassified().getClassified().get(position).getLocation().get(0).getRegionName().toString());
            cityName = latestitems.getData().getClassified().getClassified().get(position).getLocation().get(0).getCityName().toString();
        }

       if(latestitems.getData().getClassified().getClassified().get(position).getCreatedDate()==null) {
           holder.classif_date.setText("");
       }else {
           holder.classif_date.setText("Posted "+latestitems.getData().getClassified().getClassified().get(position).getCreatedDate().toString());
       }
        holder.product_name.setText(latestitems.getData().getClassified().getClassified().get(position).getTitle().toString());
        Glide.with(context)
                .load(latestitems.getData().getClassified().getClassified().get(position).getImage().get(0).getImageMd().toString())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.bbpaula)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr=new AdsItemDetail();
                FragmentManager fm= ((SearchActivitySample)context).getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("pic", latestitems.getData().getClassified().getClassified().get(position).getImage().get(0).getImageMd().toString());
                args.putString("name", latestitems.getData().getClassified().getClassified().get(position).getTitle().toString());
                args.putString("price", latestitems.getData().getClassified().getClassified().get(position).getPrice().toString());
                args.putString("item_id", latestitems.getData().getClassified().getClassified().get(position).getId().toString());
                args.putString("from", "SearchActivity");
                fr.setArguments(args);
                ft.replace(R.id.seachpage, fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        holder. mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr=new AdsItemDetail();
                FragmentManager fm= ((SearchActivitySample)context).getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("pic", latestitems.getData().getClassified().getClassified().get(position).getImage().get(0).getImageMd().toString());
                args.putString("name", latestitems.getData().getClassified().getClassified().get(position).getTitle().toString());
                args.putString("price", latestitems.getData().getClassified().getClassified().get(position).getPrice().toString());
                args.putString("item_id", latestitems.getData().getClassified().getClassified().get(position).getId().toString());
                args.putString("from", "SearchActivity");
                fr.setArguments(args);
                ft.replace(R.id.seachpage, fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(latestitems.getData().getClassified().getClassified().get(position).getTitle().toString())
                        .setContentDescription(latestitems.getData().getClassified().getClassified().get(position).getDescription().toString())
                        .setContentUrl(Uri.parse(Constants.BIGBENTA_ITEM_LINK +
                                latestitems.getData().getClassified().getClassified().get(position).getId().toString() + "/" + latestitems.getData().getClassified().getClassified().get(position).getTitle().toString()))
                        .setImageUrl(Uri.parse( latestitems.getData().getClassified().getClassified().get(position).getImage().get(0).getImageMd().toString()))
                        .build();

                shareDialog.show(linkContent);
            }
        });

        holder.shine_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addToFav(latestitems.getData().getClassified().getClassified().get(position).getTitle().toString(), latestitems.getData().getClassified().getClassified().get(position).getPrice().toString(),latestitems.getData().getClassified().getClassified().get(position).getImage().get(0).getImageMd().toString(), latestitems.getData().getClassified().getClassified().get(position).getId().toString() , holder.classif_date.getText().toString(),cityName,holder.classif_place.getText().toString() );
                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Added!")
                        .setContentText(latestitems.getData().getClassified().getClassified().get(position).getTitle().toString()+" "+" has been Added to Favorites")
                        .show();

            }
        });
    }
    private void addToFav(final String ProdName, final String ProdPrice,final String ImgUrl,String itemId, String  created_date, String city_name, String region_name){
        addfav = new AdsFavData();
        addfav.setId(getNextKey());
        addfav.setProductName(ProdName);
        addfav.setPrice(ProdPrice);
        addfav.setItemId(itemId);
        addfav.setImageUrl(ImgUrl);
        addfav.setCreated_date(created_date);
        addfav.setCity_name(city_name);
        addfav.setRegion_name(region_name);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(addfav);
        realm.commitTransaction();

    }

    public int getNextKey() {

        try {
            return realm.where(AdsFavData.class).max("id").intValue() + 1;
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return 0;
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
        public ImageView mail,share,shine_button;

        public ViewHolder(View itemView) {
            super(itemView);
            shine_button = (ImageView) itemView.findViewById(R.id.shine_button);
            share = (ImageView) itemView.findViewById(R.id.share);
            mail = (ImageView) itemView.findViewById(R.id.mail);
            imageView = (ImageView) itemView.findViewById(R.id.imageItem);
            product_name= (TextView) itemView.findViewById(R.id.textItemName);
            pprice= (TextView) itemView.findViewById(R.id.textPrice);
            classif_date= (TextView) itemView.findViewById(R.id.date_posted);
            classif_place= (TextView) itemView.findViewById(R.id.place);

            Typeface Abold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");

            this.product_name.setTypeface(Abold);
            this.pprice.setTypeface(Abold);
            this.classif_date.setTypeface(Abold);
            this.classif_place.setTypeface(Abold);

        }
    }
}