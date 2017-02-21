package ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.Categories_Store.Category_Store;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.MyCart2;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.fragcartSAMPLE;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyOrderHistory.MyOrderHistory;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.My_Favorites_Store;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.PromoDeals.PromoDeals;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SampleWebView;

/**
 * A simple {@link Fragment} subclass.
 */

public class StoreTabFragment extends Fragment {
    int position = 0;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 5 ;
    public StoreTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View x =  inflater.inflate(R.layout.store_tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        Bundle bundle=getArguments();

        if(bundle !=null)
            if(bundle.containsKey("position")){

                  position = Integer.valueOf(getArguments().getString("position"));
            }


        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));



        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
      tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.setCurrentItem(position);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {
               //  Change your textview
                if(pos==0){
                    ((MainActivity) getActivity()).setTextName("Store Categories");
                    ((MainActivity) getActivity()).setFont("store");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                } else  if(pos==1){
                    ((MainActivity) getActivity()).setFont("store");
                    ((MainActivity) getActivity()).setTextName("My Cart");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);


//                    SampleWebView fr=new SampleWebView();
//                    FragmentManager fm= getActivity().getSupportFragmentManager();
//                    FragmentTransaction ft=fm.beginTransaction();
//                    ft.addToBackStack(null);
//                    ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
//                    ft.commit();

                    Intent i = new Intent(getActivity(), MyCart2.class);
                    startActivity(i);

                } else  if(pos==2){
                    ((MainActivity) getActivity()).setTextName("My Order History");
                    ((MainActivity) getActivity()).setFont("store");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                }else  if(pos==3){
                    ((MainActivity) getActivity()).setTextName("Promo/Deals");
                    ((MainActivity) getActivity()).setFont("store");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                }
                else  if(pos==4){
                    ((MainActivity) getActivity()).setTextName("My Favorites");
                    ((MainActivity) getActivity()).setFont("store");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                }
            }


            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        Log.d("STORETABFRAGMENT", "HELLLOOO");
        ((MainActivity) getActivity()).setTextName("Store Categories");
        return x;

    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
       // menu.findItem(R.id.action_service).setVisible(false);
        menu.findItem(R.id.search).setVisible(true);
       // menu.findItem(R.id.action_cart).setVisible(false);
        //menu.findItem(R.id.action_chat).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new Category_Store();
//                case 1 : return new MyCart();
                case 1 : return new SampleWebView();
                case 2 : return new MyOrderHistory();
                case 3 : return new PromoDeals();
                case 4 : return new My_Favorites_Store();
            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "category";
                case 1 :
                    return "cart";
                case 2 :
                    return "orders";
                case 3 :
                    return "deals";
                case 4 :
                    return "favorites";
            }
            return null;
        }
    }

}