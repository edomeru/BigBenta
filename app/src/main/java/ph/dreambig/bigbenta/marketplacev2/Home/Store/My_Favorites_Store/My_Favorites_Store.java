package ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.RealmFolder.RealmDataAdapter;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;


import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class My_Favorites_Store extends Fragment {
    private FavoritesCartRealmAdapter adapter;
    private Realm realm;

    public static Button ProceedToCheckout;
    public static TextView shippingffee,GrandTotal;
    int grandtotal;
    String shipping_fee;
    private SessionManager sessionManager;

    public My_Favorites_Store() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Shopping Cart");
        realm = Realm.getDefaultInstance();
        sessionManager = new SessionManager(getActivity());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View    view = inflater.inflate(R.layout.my_favorites_store, container, false);
        adapter = new FavoritesCartRealmAdapter();
       // GrandTotal = (TextView)view.findViewById(R.id.grandtotal);
        shippingffee= (TextView)view.findViewById(R.id.shippingfee);
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        shippingffee.setTypeface(bariol);


        RecyclerView rv = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        rv.hasFixedSize();
        shipping_fee = "No Favorites Yet";
        ProceedToCheckout = (Button)view.findViewById(R.id.proceedtocheckout);

//        ProceedToCheckout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // if(sessionManager.isLoggedIn()) {
////                   MainActivity.setFragment("Billing Information");
//                //}else if(!sessionManager.isLoggedIn()){
//                //  MainActivity.setFragment("Checkout");
//                //}
//
//             //   grandtotal = realm.where(FavoritesCartData.class).sum("Price").intValue();
////                GrandTotal.setText(String.valueOf(grandtotal));
//
//                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("Add All Items to Cart?")
//                        .setContentText("Do you want to proceed? ")
//                        .setConfirmText("YES")
//                        .setCancelText("NO")
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//
//                         RealmResults<FavoritesCartData>  query = realm.where(FavoritesCartData.class).findAll();
//                                realm.beginTransaction();
//
//                                query.deleteAllFromRealm();
//
//
//                                realm.commitTransaction();
//
//                                adapter. notifyDataSetChanged();
//                                /////START
//
//
//                                //////END
//                                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
//                                        .setTitleText("Deleted!")
//                                        .setContentText("All Ads Favorites has been Deleted")
//                                        .show();
//                            }
//                        })
//                        .showCancelButton(true)
//                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.dismissWithAnimation();
//                            }
//                        })
//                        .show();
//            }
//        });







        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        RealmResults<FavoritesCartData> events = realm.where(FavoritesCartData.class).findAll();
        RealmDataAdapter realmAdapter = new RealmDataAdapter(getActivity().getApplicationContext(), events);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
        Log.i("ShoppingCart", "dasd"+String.valueOf(adapter.getItemCount()));
        if(adapter.getItemCount()==0){

            ProceedToCheckout.setVisibility(View.GONE);

//            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                    .setTitleText("Empty Shopping Cart")
//                    .setContentText("You have no items in your shopping cart.")
//                    .setConfirmText("Continue shopping")
//                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
////                            MainActivity.popFragment("Shopping Cart");
////                            MainActivity.setFragment("Visit Us");
//                            sDialog.dismissWithAnimation();
//                        }
//                    })
//                    .show();

            shippingffee.setText("No Favotires Yet");
            shippingffee.setTextSize(18);
            shippingffee.setTextColor(Color.GRAY);
        }



    }

    @Override
    public void onDestroy() {
        realm.close();
        super.onDestroy();

    }

}
