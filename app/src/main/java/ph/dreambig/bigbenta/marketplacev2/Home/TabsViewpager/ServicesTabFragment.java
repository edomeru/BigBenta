package ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;

import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.Category_Service;
import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookingHistory.MyBooking_History;
import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings.MyBookings;
import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.My_Favorites_Service.My_Favorites_Service;

/**
 * A simple {@link Fragment} subclass.
 */

public class ServicesTabFragment extends Fragment {
    int position = 0;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4 ;
    public ServicesTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x =  inflater.inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.services_tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.tabs);
        viewPager = (ViewPager) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.viewpager);
        setHasOptionsMenu(true);
        Bundle bundle=getArguments();

        if(bundle !=null)
            if(bundle.containsKey("position")){

                  position = Integer.valueOf(getArguments().getString("position"));
            }

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        viewPager.setCurrentItem(position);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {
                // Change your textview
                if(pos==0){
                    ((MainActivity) getActivity()).setTextName("Services Categories");
                    ((MainActivity) getActivity()).setFont("My Store");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                } else  if(pos==1){
                    ((MainActivity) getActivity()).setTextName("My Bookings");
                    ((MainActivity) getActivity()).setFont("My Classified Ads");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                } else  if(pos==2){
                    ((MainActivity) getActivity()).setTextName("My Booking History");
                    ((MainActivity) getActivity()).setFont("com/example/edmeralarte/bigbenta2/ServiceServer");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                }else  if(pos==3){
                    ((MainActivity) getActivity()).setTextName("My Favorites");
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

        ((MainActivity) getActivity()).setTextName("Service Categories");
        return x;

    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
       // menu.findItem(R.id.action_service).setVisible(false);
        menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.search).setVisible(true);
        //menu.findItem(R.id.action_cart).setVisible(false);
       // menu.findItem(R.id.action_chat).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new Category_Service();
                case 1 : return new MyBookings();
                case 2 : return new MyBooking_History();
                case 3 : return new My_Favorites_Service();
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
                    return "bookings";
                case 2 :
                    return "booking history";
                case 3 :
                    return "favorites";
            }
            return null;
        }
    }

}