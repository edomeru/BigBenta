package ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings;


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

import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings.RealmFolder.RealmDataAdapter;
import ph.dreambig.bigbenta.marketplacev2.R;

import ph.dreambig.bigbenta.marketplacev2.SessionManager;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookings extends Fragment {

    private MyBookingListRealmAdapter adapter;
    TextView bookZenZenNai;
    private Realm realm;
    public static Button Checkout;
    public static TextView shippingffee,GrandTotal;
    private SessionManager sessionManager;
    public MyBookings() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("My Booking List");
        realm = Realm.getDefaultInstance();
        sessionManager = new SessionManager(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View x = inflater.inflate(R.layout.fragment_my_bookinglist, container, false);
        bookZenZenNai = (TextView) x.findViewById(R.id.bookZenZenNai);
        bookZenZenNai.setVisibility(View.GONE);
        adapter = new MyBookingListRealmAdapter();
        Checkout = (Button)x.findViewById(R.id.checkout);
        GrandTotal = (TextView)x.findViewById(R.id.grandtotal);
        shippingffee= (TextView)x.findViewById(R.id.shippingfee);
        RecyclerView rv = (RecyclerView) x.findViewById(R.id.my_recycler_view_bookinglist);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);

        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        bookZenZenNai.setTypeface(bariol);

        return x;
    }
    @Override
    public void onResume() {
        super.onResume();

        RealmResults<BookingListData> events = realm.where(BookingListData.class).findAll();
        RealmDataAdapter realmAdapter = new RealmDataAdapter(getActivity().getApplicationContext(), events);
        adapter.setRealmAdapter(realmAdapter);
        adapter.notifyDataSetChanged();
        Log.i("ShoppingCart", "dasd"+String.valueOf(adapter.getItemCount()));
        if(adapter.getItemCount()==0){


//            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                    .setTitleText("Empty Shopping Cart")
//                    .setContentText("You have no items in your shopping cart.")
//                    .setConfirmText("Continue shopping")
//                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
////                            MainActivity.popFragment("Shopping Cart");
////                            MainActivity.setFragment("Visit Us");
//
//                            ServiceItems1_Frag fragment = new ServiceItems1_Frag();
//                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            fragmentTransaction.replace(R.id.containerView, fragment);
//                            fragmentTransaction.addToBackStack(null);
//                            fragmentTransaction.commit();
//                            sDialog.dismissWithAnimation();
//                        }
//                    })
//                    .show();
            bookZenZenNai.setVisibility(View.VISIBLE);
            bookZenZenNai.setTextSize(18);
            bookZenZenNai.setText("No Bookings Yet");
            bookZenZenNai.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onDestroy() {
        realm.close();
        super.onDestroy();

    }
}
