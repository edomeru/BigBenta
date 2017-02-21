
package ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import ph.dreambig.bigbenta.marketplacev2.BaseApplication;


import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.RealmFolder.RealmRecyclerViewAdapter;
import ph.dreambig.bigbenta.marketplacev2.R;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;


public class FavoritesCartRealmAdapter extends RealmRecyclerViewAdapter<FavoritesCartData> {
    Context context;
    String title;
    Realm realm;
    int toQuantity;
    int subtotal;
    ShoppingCartData addcart;

    private class EventViewHolder extends RecyclerView.ViewHolder {
        TextView asText,soldByText,quantityText,SubTotalText,unitPriceText,ProductName,ProductWeight,ProductPrice,ProductAvailability,Quantity,UnitPrice,addtocart,variantId,storeId;
        ImageView ProductImg;
        View Devider;
        ImageButton DeleteFromCart;
        Button bQuantity;
        public EventViewHolder(View itemView) {
            super(itemView);

            asText= (TextView)itemView.findViewById(R.id.asText);
            soldByText= (TextView)itemView.findViewById(R.id.soldByText);
            quantityText= (TextView)itemView.findViewById(R.id.quantityText);
            SubTotalText= (TextView)itemView.findViewById(R.id.SubTotalText);
            unitPriceText = (TextView)itemView.findViewById(R.id.unitPriceText);
            ProductName = (TextView)itemView.findViewById(R.id.product_name);
            ProductImg = (ImageView)itemView.findViewById(R.id.product_img);
            ProductPrice = (TextView) itemView.findViewById(R.id.product_price);
            ProductAvailability = (TextView) itemView.findViewById(R.id.product_availability);
            Quantity = (TextView)itemView.findViewById(R.id.product_quantity);
           ProductWeight = (TextView) itemView.findViewById(R.id.product_weight);
            bQuantity = (Button)itemView.findViewById(R.id.quantity);
            DeleteFromCart = (ImageButton)itemView.findViewById(R.id.deletefromcart);
          UnitPrice = (TextView)itemView.findViewById(R.id.unit_price);
            addtocart = (TextView)itemView.findViewById(R.id.addtocart);
            Devider = itemView.findViewById(R.id.devider);
            storeId = (TextView)itemView.findViewById(R.id.storeId);
                    variantId =(TextView) itemView.findViewById(R.id.variantId);
            Typeface Abold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Bariol_Regular.otf");
            this.ProductName.setTypeface(Abold);
            this.ProductPrice.setTypeface(Abold);
            this.ProductAvailability.setTypeface(Abold);
            this.Quantity.setTypeface(Abold);
            this.ProductWeight.setTypeface(Abold);
            this.UnitPrice.setTypeface(Abold);
            this.unitPriceText.setTypeface(Abold);
            this.quantityText.setTypeface(Abold);
            this.SubTotalText.setTypeface(Abold);
            this.soldByText.setTypeface(Abold);
            this.asText.setTypeface(Abold);
            this.bQuantity.setTypeface(Abold);
            this.addtocart.setTypeface(Abold);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_cart_cardview, parent, false);
        context = parent.getContext();
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        final EventViewHolder evh = (EventViewHolder) viewHolder;
      final  FavoritesCartData productInfo = getItem(i);

        realm = Realm.getDefaultInstance();
        realm.setAutoRefresh(true);
        evh.bQuantity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                title = ((EventViewHolder) viewHolder).ProductName.getText().toString();
                ShowNumberPicker(title);

            }
        });
        evh.addtocart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Realm realm = Realm.getDefaultInstance();
                title = ((EventViewHolder) viewHolder).ProductName.getText().toString();
                String    img = ((EventViewHolder) viewHolder).ProductImg.getDrawable().toString();
                String  quan = ((EventViewHolder) viewHolder).Quantity.getText().toString();
                String  unitP = ((EventViewHolder) viewHolder).UnitPrice.getText().toString();
                String  storeId = ((EventViewHolder) viewHolder).storeId.getText().toString();
                String  variantId = ((EventViewHolder) viewHolder).variantId.getText().toString();
                String storname = ((EventViewHolder) viewHolder).ProductWeight.getText().toString();
                String Availablestocks = ((EventViewHolder) viewHolder).ProductAvailability.getText().toString();


                String digits = unitP.replaceAll("[^0-9.]", "");
                System.out.println(digits);

                addCart(title,Double.valueOf(digits), img,Integer.valueOf( quan),storname,Availablestocks,variantId,storeId);

//                Realm realm = Realm.getDefaultInstance();
//                title = ((EventViewHolder) viewHolder).ProductName.getText().toString();
                FavoritesCartData query = realm.where(FavoritesCartData.class).equalTo("ProductName", title).findFirst();
                realm.beginTransaction();
                // new FetchCountTask(BaseApplication.CartItemsCount - query.getQuantity()).execute();
                //query.removeFromRealm();
                query.deleteFromRealm();

                realm.commitTransaction();

                notifyDataSetChanged();

                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Added to Cart!")
                        .setContentText(Integer.valueOf( quan)+" Items of "+title+" has been Added to Cart")
                        .show();

            }
        });
        evh.DeleteFromCart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Are you sure to remove the item in you shopping cart? ")
                        .setConfirmText("Delete")
                        .setCancelText("Don't delete")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                Realm realm = Realm.getDefaultInstance();
                                title = ((FavoritesCartRealmAdapter.EventViewHolder) viewHolder).ProductName.getText().toString();
                                FavoritesCartData query = realm.where(FavoritesCartData.class).equalTo("ProductName", title).findFirst();
                                realm.beginTransaction();
                                //  new AdsFavRealmAdapter.FetchCountTask(BaseApplication.CartItemsCount - query.getQuantity()).execute();
                                //query.removeFromRealm();
                                query.deleteFromRealm();
                                realm.commitTransaction();
                                sDialog.dismissWithAnimation();
                                notifyDataSetChanged();


                                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Deleted!")
                                        .setContentText(title+"has been deleted")
                                        .show();
                            }
                        })
                        .showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });

        RealmResults<FavoritesCartData> QueryResults = realm.where(FavoritesCartData.class).findAll();
        int grandtotal = QueryResults.sum("SubTotal").intValue();
        int shipping_fee = 100;
      //  My_Favorites_Store.GrandTotal.setText("Grand total: ₱"+(grandtotal+shipping_fee));

        evh.ProductName.setText(productInfo.getProductName());
        evh.storeId.setText(productInfo.getStoreId());
        evh.variantId.setText(productInfo.getVariantId());
        evh.ProductWeight.setText(productInfo.getStoreName());
        evh.ProductAvailability.setText(productInfo.getAvailableStocks());
//        if(productInfo.getAvailability().equals("Not In Stock")){
//            evh.ProductAvailability.setTextColor(Color.parseColor("#EF5350"));
//        }

       // subtotal = (productInfo.getPrice() * productInfo.getQuantity());
        evh.UnitPrice.setText("₱"+productInfo.getPrice());
        evh.ProductPrice.setText("₱"+productInfo.getSubTotal());
       evh.Quantity.setText(""+productInfo.getQuantity());

       My_Favorites_Store.ProceedToCheckout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            final   ShoppingCartData      addcartt = new ShoppingCartData();

               Realm realm = Realm.getDefaultInstance();
                   realm.executeTransaction(new Realm.Transaction() {
                       @Override
                       public void execute(Realm realm) {
//                           for(int i = 0 ; i < getRealmAdapter().getCount(); i++){

                               //realm.insert(product);


                               addcartt.setId(getNextKey());
                               addcartt.setProductName(productInfo.getProductName());
                               addcartt.setPrice(productInfo.getPrice());
                               addcartt.setQuantity(productInfo.getQuantity());
                               addcartt.setSubTotal(productInfo.getQuantity()*productInfo.getPrice());
                               addcartt.setImageUrl(productInfo.getImageUrl());
                               addcartt.setStoreName(productInfo.getStoreName());
                               addcartt.setAvailableStocks(productInfo.getAvailableStocks());
                               addcartt.setVariantId(productInfo.getVariantId());
                               addcartt.setStoreId(productInfo.getStoreId());

                             //  realm.beginTransaction();
                              // realm.copyToRealmOrUpdate(addcartt);
                           Log.i("ADD_ALL", "dasd"+addcartt.toString());
                               realm.insert(addcartt);


                               //realm.commitTransaction();







                          // }
                       }
                   });

           }
       });

        Glide.with(context)
                .load(productInfo.getImageUrl())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.bbpaula)
                .into(evh.ProductImg);
        if(QueryResults.size()==1){
            evh.Devider.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public void ShowNumberPicker(String ToUpdate){

        RelativeLayout linearLayout = new RelativeLayout(context);
        final NumberPicker aNumberPicker = new NumberPicker(context);
        aNumberPicker.setMaxValue(20);
        aNumberPicker.setMinValue(1);
        final String prodname = ToUpdate;

        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(10,10);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        linearLayout.setLayoutParams(params);
        linearLayout.addView(aNumberPicker,numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Quantity");
        alertDialogBuilder.setView(linearLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                toQuantity =  aNumberPicker.getValue();
                                realm.beginTransaction();
                                FavoritesCartData result = realm.where(FavoritesCartData.class).equalTo("ProductName",prodname).findFirst();
                                result.setQuantity(toQuantity);
                                double totalprice = result.getPrice() * toQuantity;
                                result.setSubTotal(totalprice);
                                //int totalweight = result.getOriginalWeight() * toQuantity;
                                //result.setWeight(totalweight);
                                realm.commitTransaction();
                                notifyDataSetChanged();



                                //RealmResults<FavoritesCartData> QueryResults = realm.where(FavoritesCartData.class).findAll();
//                                BaseApplication.CartItemsCount = QueryResults.sum("Quantity").intValue();
//                                new FetchCountTask(BaseApplication.CartItemsCount).execute();


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                                Log.d("dasdsa","adasdsadasdsadsadsaddsadsadsdsad");

                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void updateNotificationsBadge(int count) {
        BaseApplication.CartItemsCount = count;
        if(count == 0 ){
            My_Favorites_Store.shippingffee.setVisibility(View.GONE);
            My_Favorites_Store.ProceedToCheckout.setVisibility(View.GONE);

            My_Favorites_Store.GrandTotal.setVisibility(View.GONE);
            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Empty Shopping Cart")
                    .setContentText("You have no items in your shopping cart.")
                    .setConfirmText("Continue shopping")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

//                            VisitUsFragment visitUsFragment = new VisitUsFragment();
//                            FragmentManager manager =  ((Activity) context).getFragmentManager();
//                            FragmentTransaction transaction = manager.beginTransaction();
//                            if(manager.findFragmentByTag("store")==null){
//                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                                transaction.replace(R.id.content_main, visitUsFragment, "store");
//
//                            }else{
//                                manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                                manager.beginTransaction().replace(R.id.content_main, visitUsFragment,"store").commit();
//
//                            }
//                            transaction.commit();
//                            sDialog.dismissWithAnimation();

//                            StoreItems_Frag fragment = new StoreItems_Frag();
//                            android.support.v4.app.FragmentManager fragmentManager =  ((Activity) context.getSupportFragmentManager());
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.containerView, fragment);
//                            fragmentTransaction.addToBackStack(null);
//                            fragmentTransaction.commit();
//                            StoreItems_Frag visitUsFragment = new StoreItems_Frag();
//                            FragmentManager manager =  ((Activity) context).getSupportFragmentManager();
//                            FragmentTransaction transaction = manager.beginTransaction();
//                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                                transaction.replace(R.id.content_main, visitUsFragment);
//                            transaction.commit();
                        }
                    })
                    .show();

        }
        ((Activity) context).invalidateOptionsMenu();
    }

    public class FetchCountTask extends AsyncTask<Void, Integer, Integer> {

        int CartCount;
        public FetchCountTask(int i) {
            super();
            CartCount = i;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            return CartCount;
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
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

       // new FetchCountTask(BaseApplication.CartItemsCount + Quantity).execute();
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

