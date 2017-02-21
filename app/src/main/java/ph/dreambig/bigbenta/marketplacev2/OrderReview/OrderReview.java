package ph.dreambig.bigbenta.marketplacev2.OrderReview;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.BaseApplication;

import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.RealmFolder.RealmDataAdapter;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;
import ph.dreambig.bigbenta.marketplacev2.R;

import io.realm.Realm;
import io.realm.RealmResults;


public class OrderReview extends Fragment {


    private OrderReviewAdapter adapter;
    private Realm realm;
    TextView grandTotal,Shipping_F;
    Button placetoorder;
    int Shipping_fee = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_review_fragment,container,false);

        adapter = new OrderReviewAdapter();
        placetoorder = (Button)view.findViewById(R.id.placetoorder);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        grandTotal = (TextView)view.findViewById(R.id.grandtotal);
        Shipping_F = (TextView)view.findViewById(R.id.shippingfeee);

        RealmResults<ShoppingCartData> QueryResults = realm.where(ShoppingCartData.class).findAll();
        int grandtotal = QueryResults.sum("SubTotal").intValue();
        grandTotal.setText("₱"+(grandtotal+Shipping_fee));
        Shipping_F.setText("₱"+String.valueOf(Shipping_fee));
        placetoorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Realm realm = Realm.getDefaultInstance();
                RealmResults<ShoppingCartData> r1 = realm.where(ShoppingCartData.class).findAll();
                realm.beginTransaction();
                r1.deleteAllFromRealm();
                realm.commitTransaction();

                new FetchCountTask(0).execute();


            }
        });
        return view;
    }

    //if Registered >2500 = delivery charge free if! Registered >3500 = delivery charge free

    @Override
    public void onResume() {
        super.onResume();
        RealmResults<ShoppingCartData> events = realm.where(ShoppingCartData.class).findAll();
        RealmDataAdapter realmAdapter = new RealmDataAdapter(getActivity().getApplicationContext(), events);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
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

    private void updateNotificationsBadge(int count) {
        BaseApplication.CartItemsCount = count;
        if(count == 0 ){
//                ShoppingCart.ProceedToCheckout.setVisibility(View.GONE);
//                ShoppingCart.ContinueShopping.setVisibility(View.GONE);
//                ShoppingCart.GrandTotal.setVisibility(View.GONE);
//            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                    .setTitleText("Empty Shopping Cart")
//                    .setContentText("You have no items in your shopping cart.")
//                    .setConfirmText("Continue shopping")
//                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
//
//
//
//
//                        }
//                    })
//                    .show();

        }
        ((Activity) getActivity()).invalidateOptionsMenu();
    }
}
