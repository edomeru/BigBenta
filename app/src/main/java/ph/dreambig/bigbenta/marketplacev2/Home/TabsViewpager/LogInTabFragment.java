package ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ph.dreambig.bigbenta.marketplacev2.LogIn.FirstPage;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Frag1;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Frag2;
import ph.dreambig.bigbenta.marketplacev2.R;

/**
 * A simple {@link Fragment} subclass.
 */

public class LogInTabFragment extends Fragment {
    int position = 0;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2 ;
    public LogInTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x =  inflater.inflate(R.layout.login_tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        setHasOptionsMenu(true);
        Bundle bundle=getArguments();

        if(bundle !=null)
            if(bundle.containsKey("pos")){

                  position = Integer.valueOf(getArguments().getString("pos"));
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
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {
                // Change your textview
                if(pos==0){

                    ((FirstPage) getActivity()).setTextTitle("LogIn");


                } else  if(pos==1){


                    ((FirstPage) getActivity()).setTextTitle("Register");

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
                case 0 : return new Frag1();
                case 1 : return new Frag2();

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
                    return "Log In";
                case 1 :
                    return "Register";

            }
            return null;
        }
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.action_service).setVisible(false);
//        menu.findItem(R.id.search).setVisible(true);
//        menu.findItem(R.id.action_cart).setVisible(false);
//        menu.findItem(R.id.action_chat).setVisible(true);
//        super.onPrepareOptionsMenu(menu);
//    }

}