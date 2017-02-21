package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import ph.dreambig.bigbenta.marketplacev2.BillingInformationStore;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.LogInTabFragment;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.RealmFolder.RealmDataAdapter;

import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItems_Frag;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCart extends Fragment {

    private ShoppingCartRealmAdapter adapter;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopingcart_layout, container, false);

        adapter = new ShoppingCartRealmAdapter();
        GrandTotal = (TextView)view.findViewById(R.id.grandtotal);
        shippingffee= (TextView)view.findViewById(R.id.shippingfee);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
       ShoppingCartData sc = new ShoppingCartData();
        Log.d("ShoppingCartData",sc.toString());

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



                    grandtotal = realm.where(ShoppingCartData.class).sum("Price").intValue();
//                GrandTotal.setText(String.valueOf(grandtotal));

                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Shipping Fee: ₱" + shipping_fee)
                            .setContentText("Overall Amount is ₱" + (grandtotal + shipping_fee) + ", Do you want to proceed? ")
                            .setConfirmText("YES")
                            .setCancelText("NO")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {


                                    BillingInformationStore fragment = new BillingInformationStore();
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.containerView, fragment);
                                    fragmentTransaction.commit();
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
                }
            }
        });

        ContinueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.tag = "Home";
//
                StoreItems_Frag fragment = new StoreItems_Frag();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView, fragment);
                fragmentTransaction.commit();


            }
        });




        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        RealmResults<ShoppingCartData> events = realm.where(ShoppingCartData.class).findAll();
        Log.d("RealmResults_DATA",events.toString());

        RealmDataAdapter realmAdapter = new RealmDataAdapter(getActivity().getApplicationContext(), events);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
        Log.i("ShoppingCart", "dasd"+String.valueOf(adapter.getItemCount()));
        if(adapter.getItemCount()==0){
            ContinueShopping.setVisibility(View.GONE);
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

            shippingffee.setText("Empty Shopping Cart");
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
