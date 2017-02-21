package ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.Categories_Ads.Ads_Frag;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.Category_Service;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.Categories_Store.Category_Store;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyOrderHistory.MyOrderHistory;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.My_Favorites_Store;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.PromoDeals.PromoDeals;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SampleWebView;

/**
 * A simple {@link Fragment} subclass.
 */

public class Home2TabFragment extends Fragment {
    int position = 0;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;
    public Home2TabFragment() {
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
                    ((MainActivity) getActivity()).setTextName("Service Categories");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);


                } else  if(pos==2){
                    ((MainActivity) getActivity()).setTextName("Classified Ads Categories");
                    ((MainActivity) getActivity()).setFont("com/example/edmeralarte/bigbenta2/ServiceServer");
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
                case 1 : return new Category_Service();
                case 2 : return new Ads_Frag();

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
                    return "Store";
                case 1 :
                    return "Services";
                case 2 :
                    return "Classified Ads";

            }
            return null;
        }
    }

}