package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyFavorites_Ads;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyFavorites_Ads.RealmFolder.RealmDataAdapter;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.LogInTabFragment;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFavotires_Ads extends Fragment {

View view;
    public MyFavotires_Ads() {
        // Required empty public constructor
    }

    private AdsFavRealmAdapter adapter;
    private Realm realm;
    public static Button ProceedToCheckout, ContinueShopping;
    public static TextView shippingffee,GrandTotal;
    int grandtotal;

    int shipping_fee;
    private SessionManager sessionManager;

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
        view = inflater.inflate(R.layout.my_favotires__ads, container, false);
        adapter = new AdsFavRealmAdapter();
        GrandTotal = (TextView)view.findViewById(R.id.grandtotal);
        shippingffee= (TextView)view.findViewById(R.id.shippingfee);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        AdsFavData sc = new AdsFavData();
        Log.d("AdsFavData",sc.toString());

        shipping_fee = 100;
        ProceedToCheckout = (Button)view.findViewById(R.id.proceedtocheckout);
        ContinueShopping = (Button)view.findViewById(R.id.continueshopping);
        ProceedToCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sessionManager.isLoggedIn()) {

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("You Are NOT Logged In yet" )
                            .setContentText("Please Log in To Proceed")
                            .setConfirmText("YES")
                            .setCancelText("NO")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {

                                    LogInTabFragment fr=new LogInTabFragment();
                                    FragmentManager fm= getActivity().getSupportFragmentManager();
                                    FragmentTransaction ft=fm.beginTransaction();
                                    ft.replace(R.id.containerView, fr);
                                    ft.commit();
                                    sDialog.dismissWithAnimation();
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

                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Are you sure?")
                            .setContentText("Are you sure to remove the item in you shopping cart? ")
                            .setConfirmText("Delete")
                            .setCancelText("Don't delete")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    RealmResults<AdsFavData>  query = realm.where(AdsFavData.class).findAll();
                                    realm.beginTransaction();
                                    // new FetchCountTask(BaseApplication.CartItemsCount - query.getQuantity()).execute();
                                    //query.removeFromRealm();
                                    query.deleteAllFromRealm();


                                    realm.commitTransaction();

                                    adapter. notifyDataSetChanged();
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                            .setTitleText("Deleted!")
                                            .setContentText("All Ads Favorites has been Deleted")
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
            }
        });
//
//        ContinueShopping.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                MainActivity.tag = "Home";
////
//                StoreItems_Frag fragment = new StoreItems_Frag();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.containerView, fragment);
//                fragmentTransaction.commit();
//
//
//            }
//        });




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        RealmResults<AdsFavData> events = realm.where(AdsFavData.class).findAll();
        Log.d("RealmResults_DATA",events.toString());

        RealmDataAdapter realmAdapter = new RealmDataAdapter(getActivity().getApplicationContext(), events);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
        Log.i("ShoppingCart", "dasd"+String.valueOf(adapter.getItemCount()));
        if(adapter.getItemCount()==0){
//            ContinueShopping.setVisibility(View.GONE);
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

            shippingffee.setText("No Ads Favorites Yet");
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


