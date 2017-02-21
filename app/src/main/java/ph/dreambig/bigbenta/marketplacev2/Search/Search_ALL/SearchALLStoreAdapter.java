package ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL;


import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.FavoritesCartData;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.Search.SearchActivitySample;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.StoreItemDetail.StoreItemDetail;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by edmeralarte on 23/08/2016.
 */

public class SearchALLStoreAdapter extends RecyclerView.Adapter<SearchALLStoreAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;
    ShoppingCartData addcart;
    CallbackManager callbackManager;
    SessionManager sessionManager;
    ShareDialog shareDialog;
    Realm realm;
    FavoritesCartData addtofav;
    List<SearchALLResponse> superHeroes = new ArrayList<>();
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        ImageView shineButton;
        public TextView product_title;
        public TextView pprice;
        public TextView store_name;
        public TextView classif_date;
        public TextView classif_place,labelOrigP;
        public ImageView scart,share;
        public TextView sale;
        public TextView orig_price;

        public ViewHolder(View itemView) {
            super(itemView);
            labelOrigP= (TextView) itemView.findViewById(R.id.labelOrigP);
            share = (ImageView) itemView.findViewById(R.id.share);
            orig_price= (TextView) itemView.findViewById(R.id.orig_price);
            sale = (TextView) itemView.findViewById(R.id.sale);
            scart = (ImageView) itemView.findViewById(R.id.scart);
            shineButton = (ImageView) itemView.findViewById(R.id.shine_button);
            imageView = (ImageView) itemView.findViewById(R.id.product_img);
            product_title= (TextView) itemView.findViewById(R.id.product_title);
            pprice= (TextView) itemView.findViewById(R.id.price);
            store_name= (TextView) itemView.findViewById(R.id.store_name);
            Typeface Abold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.store_name.setTypeface(Abold);
            this.pprice.setTypeface(Abold);
            this.product_title.setTypeface(Abold);
        }
    }
    public SearchALLStoreAdapter(List<SearchALLResponse> superHeroes, Context context){
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
                .inflate(R.layout.searchstoreallcardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

      final  SearchALLResponse latestitems=  superHeroes.get(position);

//        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
//        imageLoader.get(latestitems.getimage(), ImageLoader.getImageListener(holder.imageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //holder.imageView.setImageUrl(latestitems.getimage(), imageLoader);
        if(latestitems.getData().getStores().getStores().get(position).getTitle()==null){
            holder.product_title.setText("");
        }else{
            holder.product_title.setText(latestitems.getData().getStores().getStores().get(position).getTitle().toString());
        }
        String textp = latestitems.getData().getStores().getStores().get(position).getPrice().toString();
        String digitsp = textp.replaceAll("[^0-9.]", "");
        System.out.println(digitsp);

        double xp = Double.valueOf(digitsp) ;
        int yp = (int) xp;
        String priceIntp = String.valueOf(yp);
        holder.pprice.setText("₱"+priceIntp);
        holder.store_name.setText(latestitems.getData().getStores().getStores().get(position).getStoreName().toString());
//        if (holder.shineButton != null)
//            holder.shineButton.init((Activity)context);

        holder.shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "click");
            }
        });
       // holder.store_name.setText(latestitems.getclassif_date());
       // holder.product_name.setText(latestitems.getclassif_name());
        if(latestitems.getData().getStores().getStores().get(position).getImage().size()==0){
            Glide.with(context)
                    .load(R.drawable.bbpaula)
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.drawable.bbpaula)
                    .into(holder.imageView);
        }else {
            Glide.with(context)
                    .load(latestitems.getData().getStores().getStores().get(position).getImage().get(0).getImageMd().toString())
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.drawable.bbpaula)
                    .into(holder.imageView);

        }
        if(latestitems.getData().getStores().getStores().get(position).getIsSale()==1) {
            holder.sale.setVisibility(View.VISIBLE);

            String text = latestitems.getData().getStores().getStores().get(position).getOriginalPrice().toString();
            String digits = text.replaceAll("[^0-9.]", "");
            System.out.println(digits);

            double x = Double.valueOf(digits) ;
            int y = (int) x;
            String priceInt = String.valueOf(y);

            holder.orig_price.setText("₱"+priceInt);
            holder.orig_price.setVisibility(View.VISIBLE);
            holder.labelOrigP.setVisibility(View.INVISIBLE);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr=new StoreItemDetail();
                FragmentManager fm= ((SearchActivitySample) context).getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("item_id", latestitems.getData().getStores().getStores().get(position).getId().toString());
                args.putString("from", "SearchAllActivity");
                fr.setArguments(args);
                ft.replace(R.id.seachpage, fr);
                ft.addToBackStack(null);
                ft.commit();
            }
        });



        holder.scart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fr=new StoreItemDetail();
//                FragmentManager fm= ((SearchActivitySample) context).getSupportFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                Bundle args = new Bundle();
//
//                args.putString("item_id", latestitems.getData().getStores().getStores().get(position).getId().toString());
//                fr.setArguments(args);
//                ft.replace(R.id.seachpage, fr);
//                ft.addToBackStack(null);
//                ft.commit();
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


        holder.shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text2 = latestitems.getData().getStores().getStores().get(position).getPrice().toString();
                String digits2 = text2.replaceAll("[^0-9.]", "");
                double x2 = Double.valueOf(digits2);
                int y2 = (int) x2;
                String priceInt2 = String.valueOf(y2);
                addToFav(latestitems.getData().getStores().getStores().get(position).getTitle().toString(), x2, latestitems.getData().getStores().getStores().get(position).getImage().get(0).getImageMd().toString(),Integer.valueOf(latestitems.getData().getStores().getStores().get(position).getMinimumOrder().toString()) , latestitems.getData().getStores().getStores().get(position).getStoreName().toString(), latestitems.getData().getStores().getStores().get(position).getQuantity().toString(), latestitems.getData().getStores().getStores().get(position).getVariantId().toString(), latestitems.getData().getStores().getStores().get(position).getStoreId().toString());
                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Added to Favorites!")
                        .setContentText(latestitems.getData().getStores().getStores().get(position).getQuantity().toString() + " Items of " + latestitems.getData().getStores().getStores().get(position).getTitle().toString() + " has been Added to Favorites")
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
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
    public int getNextKey() {

        try {
            return realm.where(ShoppingCartData.class).max("id").intValue() + 1;
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }
}