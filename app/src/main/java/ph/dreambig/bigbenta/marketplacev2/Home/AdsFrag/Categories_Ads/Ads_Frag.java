package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.Categories_Ads;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import ph.dreambig.bigbenta.marketplacev2.AdsSubCat.Ads_SubCat_Frag;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.ServiceAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ads_Frag extends Fragment {

    private List<AdsData> adsData = new ArrayList<>();
    private AdsAdapter mAdapter;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    int CatId[] = {
            155,
            173,

            143,
            56,
            31,
            134,
            50,
            2,
            93,
            410,

            297,
           196
    };



    String mProductName[] = {
            "Real Estate",
            "Cars/Vehicles",

            "Mobile & Gadgets",
            "Computers",
            "Clothes",
            "Pets & Accessories",
            "Hobbies",
            "Appliances",
            "Furniture",
            "Bags & Luggages",

            "Bussiness for Sale",
             "Services"
    };
    String mImageUrl[] = {
            "http://edmer.x10host.com/bb/RealEstate.jpg",
            "http://edmer.x10host.com/bb/carsVehicles.jpg",

            "http://edmer.x10host.com/bb/MobileGadgets.jpg",
            "http://edmer.x10host.com/bb/computer.png",
            "http://edmer.x10host.com/bb/Clothes.jpg",
            "http://edmer.x10host.com/bb/PetsAccessories.jpeg",
            "http://edmer.x10host.com/bb/Hobbies.jpg",
            "http://edmer.x10host.com/bb/Appliances.jpg",
            "http://edmer.x10host.com/bb/Furniture.jpg",
            "http://edmer.x10host.com/bb/BagsLuggages.jpg",



            "http://edmer.x10host.com/bb/bfs.jpg",
            "http://edmer.x10host.com/bb/Services.jpg"
    };

    Ads_Frag newInstance(String mProductName, String mProductImage) {
        Ads_Frag ViewProductFragment = new Ads_Frag();

        Bundle args = new Bundle();
        args.putString("Name", mProductName);
        args.putString("Url", mProductImage);
        ViewProductFragment.setArguments(args);

        return ViewProductFragment;
    }

    public Ads_Frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AdsAdapter(adsData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.fragment_ads, container, false);

        recyclerView = (RecyclerView) view.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.ads_recycler_view);
        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        mAdapter =  new AdsAdapter(adsData);

        if(mAdapter.getItemCount()==0) {
            prepareVisitData();
            mAdapter =  new AdsAdapter(adsData);
            recyclerView.setAdapter(mAdapter);

        }else {

            recyclerView.setAdapter(mAdapter);

        }



        recyclerView.addOnItemTouchListener(new Ads_Frag.RecyclerTouchListener(getActivity(), recyclerView, new Ads_Frag.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Ads_SubCat_Frag fr=new Ads_SubCat_Frag();
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("catname", adsData.get(position).getName());
                args.putString("catid", String.valueOf(adsData.get(position).getCatId()));
                args.putString("catPic", adsData.get(position).getImgUrl());
                fr.setArguments(args);
                ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
                ft.addToBackStack(null);
                ft.commit();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        ((MainActivity) getActivity()).setTextName("Classified Ads");
        return view;

    }
    private void prepareVisitData() {

        for(int i=0; i<mProductName.length;i++) {
            AdsData newsAndPromosData = new AdsData(mImageUrl[i],mProductName[i], CatId[i]);
            adsData.add(newsAndPromosData);
        }
        mAdapter.notifyDataSetChanged();
    }
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Ads_Frag.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Ads_Frag.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }


    }

}