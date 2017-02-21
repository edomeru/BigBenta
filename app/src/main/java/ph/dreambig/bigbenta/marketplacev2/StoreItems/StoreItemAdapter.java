package ph.dreambig.bigbenta.marketplacev2.StoreItems;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
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

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.MyCart2;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.FavoritesCartData;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.StoreItemDetail.StoreItemDetail;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.ViewHolder> {
    CallbackManager callbackManager;
    FavoritesCartData addtofav;
    private ImageLoader imageLoader;
    private Context context;
    ShareDialog shareDialog;
    Realm realm;
    ShoppingCartData addcart;
    SessionManager sessionManager;
    private final static int FADE_DURATION = 1000;

    List<StoreItemRetrofitResponse> superHeroes = new ArrayList<>();



    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        ImageView shineButton,scart;
       TextView sale;
        public TextView product_title,freeshipping,labelOrigP;
        public TextView pprice;
        public TextView store_name;
        public TextView classif_date;
        public TextView classif_place;
        public TextView orig_price;
        public ImageView share;

        public ViewHolder(View itemView) {
            super(itemView);
            labelOrigP = (TextView) itemView.findViewById(R.id.labelOrigP);
            freeshipping = (TextView) itemView.findViewById(R.id.freeshipping);
            scart= (ImageView) itemView.findViewById(R.id.scart);
            orig_price = (TextView) itemView.findViewById(R.id.orig_price);
            sale = (TextView) itemView.findViewById(R.id.sale);
            shineButton = (ImageView) itemView.findViewById(R.id.shine_button);
            share  = (ImageView) itemView.findViewById(R.id.share);
            imageView = (ImageView) itemView.findViewById(R.id.product_img);
            product_title= (TextView) itemView.findViewById(R.id.product_title);
            pprice= (TextView) itemView.findViewById(R.id.price);
            store_name= (TextView) itemView.findViewById(R.id.store_name);
            //classif_place= (TextView) itemView.findViewById(R.id.place);
            Typeface Abold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.store_name.setTypeface(Abold);
            //this.classif_place.setTypeface(Caviarr);
            Typeface Caviarr = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.pprice.setTypeface(Caviarr);
            this.product_title.setTypeface(Caviarr);
            this.freeshipping.setTypeface(Caviarr);

        }
    }
    public StoreItemAdapter(List<StoreItemRetrofitResponse> superHeroes, Context context){
        super();
        //Getting all the superheroes
        FacebookSdk.sdkInitialize(context.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog((MainActivity) context);
        this.superHeroes = superHeroes;
        this.context = context;
        sessionManager = new SessionManager(context);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.storeitems_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final StoreItemRetrofitResponse latestitems = superHeroes.get(position);

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(latestitems.getimage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //holder.imageView.setImageUrl(latestitems.getimage(), imageLoader);
        holder.product_title.setText(latestitems.getData().getStores().getStores().get(position).getTitle().toString());
        if(Integer.valueOf(latestitems.getData().getStores().getStores().get(position).getQuantity().toString())!=0) {


            String text2 = latestitems.getData().getStores().getStores().get(position).getPrice().toString();
            String digits2 = text2.replaceAll("[^0-9.]", "");
            System.out.println(digits2);
            System.out.println(latestitems.getData().getStores().getStores().get(position).getQuantity().toString());
            System.out.println(latestitems.getData().getStores().getStores().get(position).getMinimumOrder().toString());
            double x2 = Double.valueOf(digits2);
            int y2 = (int) x2;
            String priceInt2 = String.valueOf(y2);
            holder.pprice.setTextColor(Color.WHITE);
            holder.pprice.setText("₱" + priceInt2);
            holder.scart.setEnabled(true);
        }else{
            holder.pprice.setText("Out Of Stock");
            holder.pprice.setTextColor(Color.RED);
            holder.scart.setEnabled(false);
        }
        holder.store_name.setText(latestitems.getData().getStores().getStores().get(position).getStoreName());
//        if (holder.shineButton != null)
//            holder.shineButton.init((Activity)context);
        if (latestitems.getData().getStores().getStores().get(position).getIsSale() == 1) {
            holder.sale.setVisibility(View.VISIBLE);
            setScaleAnimation(holder.sale);
            String text = latestitems.getData().getStores().getStores().get(position).getOriginalPrice().toString();
            String digits = text.replaceAll("[^0-9.]", "");
            System.out.println(digits);

            double x = Double.valueOf(digits);
            int y = (int) x;
            String priceInt = String.valueOf(y);
            holder.labelOrigP.setVisibility(View.INVISIBLE);
            holder.orig_price.setText("₱" + priceInt);
            holder.orig_price.setVisibility(View.VISIBLE);
        }


       if(latestitems.getData().getStores().getStores().get(position).getIsFreeShipping()!=null) {
           if (Integer.valueOf(latestitems.getData().getStores().getStores().get(position).getIsFreeShipping().toString()) == 1) {
               holder.freeshipping.setVisibility(View.VISIBLE);
           }
       }


        holder.shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = latestitems.getData().getStores().getStores().get(position).getPrice().toString();
                String digits = text.replaceAll("[^0-9.]", "");
                System.out.println(digits);

                double x = Double.valueOf(digits);
                addToFav(latestitems.getData().getStores().getStores().get(position).getTitle().toString(), x , latestitems.getData().getStores().getStores().get(position).getImage().get(0).getImageMd().toString(), Integer.valueOf(latestitems.getData().getStores().getStores().get(position).getMinimumOrder().toString()), latestitems.getData().getStores().getStores().get(position).getStoreName().toString(), latestitems.getData().getStores().getStores().get(position).getQuantity().toString(), latestitems.getData().getStores().getStores().get(position).getVariantId().toString(), latestitems.getData().getStores().getStores().get(position).getStoreId().toString());
                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Added to Favorites!")
                        .setContentText(latestitems.getData().getStores().getStores().get(position).getQuantity().toString() + " Items of " + latestitems.getData().getStores().getStores().get(position).getTitle().toString() + " has been Added to Favorites")
                        .show();
            }
        });
        // holder.store_name.setText(latestitems.getclassif_date());
        // holder.product_name.setText(latestitems.getclassif_name());
        if (latestitems.getData().getStores().getStores().get(position).getImage().size()!=0){
            Glide.with(context)
                    .load(latestitems.getData().getStores().getStores().get(position).getImage().get(0).getImageSm().toString())
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.drawable.bbpaula)
                    .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr = new StoreItemDetail();
                FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("item_id", latestitems.getData().getStores().getStores().get(position).getId().toString());
                args.putString("from", "MainActivity");
                fr.setArguments(args);
                ft.replace(R.id.containerView, fr);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
    }else{
            Glide.with(context)
                    .load(R.drawable.bbpaula)
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.drawable.bbpaula)
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fr = new StoreItemDetail();
                    FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    Bundle args = new Bundle();
                    args.putString("item_id", latestitems.getData().getStores().getStores().get(position).getId().toString());
                    fr.setArguments(args);
                    ft.replace(R.id.containerView, fr);
                    ft.addToBackStack(null);
                    ft.commit();

                }
            });
        }



        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(latestitems.getData().getStores().getStores().get(position).getTitle().toString())
                        .setContentDescription(latestitems.getData().getStores().getStores().get(position).getLongDescription().toString())
                        .setContentUrl(Uri.parse(Constants.BIGBENTA_ITEM_LINK +
                                latestitems.getData().getStores().getStores().get(position).getId().toString() + "/" + latestitems.getData().getStores().getStores().get(position).getTitle()))
                        .setImageUrl(Uri.parse(latestitems.getData().getStores().getStores().get(position).getImage().get(0).getImageSm().toString()))
                        .build();

                shareDialog.show(linkContent);
            }
        });

        holder.scart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = latestitems.getData().getStores().getStores().get(position).getPrice().toString();
                String digits = text.replaceAll("[^0-9.]", "");
                System.out.println(digits);

                double x = Double.valueOf(digits);
                addCart(latestitems.getData().getStores().getStores().get(position).getTitle().toString(),x, latestitems.getData().getStores().getStores().get(position).getImage().get(0).getImageMd().toString(),Integer.valueOf(latestitems.getData().getStores().getStores().get(position).getMinimumOrder().toString()) ,latestitems.getData().getStores().getStores().get(position).getStoreName().toString(),latestitems.getData().getStores().getStores().get(position).getQuantity().toString(),latestitems.getData().getStores().getStores().get(position).getVariantId().toString(),latestitems.getData().getStores().getStores().get(position).getStoreId().toString());

                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Added!")
                        .setContentText(latestitems.getData().getStores().getStores().get(position).getMinimumOrder().toString()+" Items of "+latestitems.getData().getStores().getStores().get(position).getTitle().toString()+" has been Added to Cart")
                        .show();
            }
        });

        setScaleAnimation(holder.shineButton);
        setScaleAnimation(holder.share);
        setScaleAnimation(holder.scart);
        setScaleAnimation(holder.pprice);

        setFadeAnimation(holder.imageView);

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

    private void addToFav(final String ProdName, final double ProdPrice,final String ImgUrl,final int Quantity,String store_name,String avail,String variantId,String storeId){
        addtofav = new FavoritesCartData();
        addtofav.setId(getFavNextKey());
        addtofav.setProductName(ProdName);
        addtofav.setPrice(ProdPrice);
        addtofav.setQuantity(Quantity);
        addtofav.setSubTotal(Quantity*ProdPrice);
        addtofav.setImageUrl(ImgUrl);
        addtofav.setStoreName(store_name);
        addtofav.setAvailableStocks(avail);
        addtofav.setVariantId(variantId);
        addtofav.setStoreId(storeId);


        realm.beginTransaction();
        realm.copyToRealmOrUpdate(addtofav);
        realm.commitTransaction();
        //new FetchCountTask(BaseApplication.CartItemsCount + Quantity).execute();
    }
    public int getFavNextKey() {

        try {
            return realm.where(FavoritesCartData.class).max("id").intValue() + 1;
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }

    private void addCart(final String ProdName, final double ProdPrice,final String ImgUrl,final int Quantity,String store_name,String avail,String variantId,String storeId){
        addcart = new ShoppingCartData();
        addcart.setId(getNextKey());
        addcart.setProductName(ProdName);
        addcart.setPrice(ProdPrice);
        addcart.setQuantity(Quantity);
        addcart.setSubTotal(Quantity*ProdPrice);
        addcart.setImageUrl(ImgUrl);
        addcart.setStoreName(store_name);
        addcart.setAvailableStocks(avail);
        addcart.setVariantId(variantId);
        addcart.setStoreId(storeId);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(addcart);
        realm.commitTransaction();
        //new StoreItemDetail.FetchCountTask(AppController.CartItemsCount + Quantity).execute();
    }
    @Override
    public int getItemCount() {
        if (superHeroes.size() == 0) {
            StoreItems_Frag.labelNotification.setVisibility(View.VISIBLE);

        } else {
            StoreItems_Frag.labelNotification.setVisibility(View.GONE);
        }
        return superHeroes.size();
    }
    public int getNextKey() {

        try {
            return realm.where(ShoppingCartData.class).max("id").intValue() + 1;
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }

}