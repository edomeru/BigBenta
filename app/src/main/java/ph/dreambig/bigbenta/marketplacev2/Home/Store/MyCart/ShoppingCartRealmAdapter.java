
package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import ph.dreambig.bigbenta.marketplacev2.BaseApplication;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.RealmFolder.RealmRecyclerViewAdapter;


import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;


public class ShoppingCartRealmAdapter extends RealmRecyclerViewAdapter<ShoppingCartData> {
    Context context;
    String title;
    Realm realm;
    int toQuantity;
    int subtotal;

    private class EventViewHolder extends RecyclerView.ViewHolder {
        TextView ProductName,ProductWeight,ProductPrice,StoreName,Quantity,UnitPrice,ProductAvailability;
        ImageView ProductImg;
        View Devider;
        Button bQuantity,DeleteFromCart;
        public EventViewHolder(View itemView) {
            super(itemView);

            ProductName = (TextView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_name);
            ProductImg = (ImageView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_img);
            ProductPrice = (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_price);
            ProductAvailability = (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_availability);
            Quantity = (TextView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_quantity);
           ProductWeight = (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_weight);
            bQuantity = (Button)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.quantity);
            DeleteFromCart = (Button)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.deletefromcart);
          UnitPrice = (TextView)itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.unit_price);
            Devider = itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.devider);
            StoreName = (TextView) itemView.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.storename);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.shopping_cart_cardview, parent, false);
        context = parent.getContext();
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        final EventViewHolder evh = (EventViewHolder) viewHolder;
        ShoppingCartData productInfo = getItem(i);

        realm = Realm.getDefaultInstance();
        realm.setAutoRefresh(true);
        evh.StoreName.setText(productInfo.getStoreName());

        evh.bQuantity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                title = ((EventViewHolder) viewHolder).ProductName.getText().toString();
                ShowNumberPicker(title);

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
                                title = ((EventViewHolder) viewHolder).ProductName.getText().toString();
                                ShoppingCartData query = realm.where(ShoppingCartData.class).equalTo("ProductName", title).findFirst();
                                realm.beginTransaction();
                                new FetchCountTask(BaseApplication.CartItemsCount - query.getQuantity()).execute();
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

        RealmResults<ShoppingCartData> QueryResults = realm.where(ShoppingCartData.class).findAll();
        int grandtotal = QueryResults.sum("SubTotal").intValue();
        int shipping_fee = 100;
        MyCart.GrandTotal.setText("Grand total: ₱"+(grandtotal+shipping_fee));
        evh.ProductName.setText(productInfo.getProductName());
       // evh.ProductWeight.setText(""+productInfo.getOriginalWeight() * productInfo.getQuantity()+".00 kg");
        evh.ProductAvailability.setText(productInfo.getAvailableStocks());
        if(productInfo.getAvailableStocks().equals("0")){
            evh.ProductAvailability.setTextColor(Color.parseColor("#EF5350"));
        }

       // subtotal = (productInfo.getPrice() * productInfo.getQuantity());
        evh.UnitPrice.setText("₱"+productInfo.getPrice());
        evh.ProductPrice.setText("₱"+productInfo.getSubTotal());
       evh.Quantity.setText(""+productInfo.getQuantity());
        Glide.with(context)
                .load(productInfo.getImageUrl())
                .centerCrop()
                .dontAnimate()
                .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.bbpaula)
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
                                ShoppingCartData result = realm.where(ShoppingCartData.class).equalTo("ProductName",prodname).findFirst();
                                result.setQuantity(toQuantity);
                                double totalprice = result.getPrice() * toQuantity;
                                result.setSubTotal(totalprice);
                                //int totalweight = result.getOriginalWeight() * toQuantity;
                                //result.setWeight(totalweight);
                                realm.commitTransaction();
                                notifyDataSetChanged();



                                RealmResults<ShoppingCartData> QueryResults = realm.where(ShoppingCartData.class).findAll();
                                BaseApplication.CartItemsCount = QueryResults.sum("Quantity").intValue();
                                new FetchCountTask(BaseApplication.CartItemsCount).execute();

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
            MyCart.shippingffee.setVisibility(View.GONE);
            MyCart.ProceedToCheckout.setVisibility(View.GONE);
            MyCart.ContinueShopping.setVisibility(View.GONE);
            MyCart.GrandTotal.setVisibility(View.GONE);
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
}

