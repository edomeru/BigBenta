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

import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.Categories_Ads.Ads_Frag;
import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds.MyAds;
import ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyFavorites_Ads.MyFavotires_Ads;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */

public class AdsTabFragment extends Fragment {
    int position = 0;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;

//    private int[] tabIcons = {
//            R.drawable.chat,
//            R.drawable.ic_menu_notification,
//            R.drawable.ic_service_notification,
//            R.drawable.ic_service_notification
//    };
    public AdsTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x =  inflater.inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.ads_tab_layout,null);
        setHasOptionsMenu(true);
        tabLayout = (TabLayout) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.tabs);
        viewPager = (ViewPager) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.viewpager);
        setHasOptionsMenu(true);
//        Bundle bundle=getArguments();

//        if(bundle !=null)
//            if(bundle.containsKey("pos")){
//
//                  position = Integer.valueOf(getArguments().getString("pos"));
//            }


        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));



        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                //setupTabIcons();
            }
        });
      tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setCurrentItem(position);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {
                // Change your textview
                if(pos==0){
                    ((MainActivity) getActivity()).setTextName("Ads Categories");
                    ((MainActivity) getActivity()).setFont("store");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                } else  if(pos==1){
                    ((MainActivity) getActivity()).setFont("Classified Ads");
                    ((MainActivity) getActivity()).setTextName("My Ads");
                    ((MainActivity) getActivity()).setTextColor(Color.WHITE);

                } else  if(pos==2){
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


        return x;

    }



//    private void setupTabIcons() {
//
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
//
//
//       Drawable drawable = getResources().getDrawable(tabIcons[0]);
//
//        Utility.setTestBadgeCount(getActivity(), drawable, BaseApplication.ServiceItemsCount);
//    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        //menu.findItem(R.id.action_service).setVisible(false);
        menu.findItem(ph.dreambig.bigbenta.marketplacev2.R.id.search).setVisible(true);
       // menu.findItem(R.id.action_cart).setVisible(false);
       // menu.findItem(R.id.action_chat).setVisible(true);
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
                case 0 : return new Ads_Frag();
                case 1 : return new MyAds();
                case 2 : return new MyFavotires_Ads();

            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }



        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "categories";
                case 1 :
                    return "ads";
                case 2 :
                    return "favorites";


            }
            return null;
        }
    }

}