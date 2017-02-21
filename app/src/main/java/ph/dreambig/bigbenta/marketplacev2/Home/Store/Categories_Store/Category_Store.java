package ph.dreambig.bigbenta.marketplacev2.Home.Store.Categories_Store;

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
import android.widget.TextView;
import android.widget.ViewFlipper;


import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;

import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.Store_SubCat.Store_SubCat_Frag;


import java.util.ArrayList;
import java.util.List;


public class Category_Store extends Fragment  {
    SessionManager sessionManager;
    ViewFlipper viewFlipper;
    private List<StoreData> storeData = new ArrayList<>();
    private StoreAdapter mAdapter;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    TextView toolbar_title1, toolbar_title2,toolbar_subtitle1;
    String bannerUrl[] = {Constants.API_BIGBENTA_DOMAIN+"/bb/banner1.jpg",
            Constants.API_BIGBENTA_DOMAIN+"/bb/banner2.jpg",
            Constants.API_BIGBENTA_DOMAIN+"/bb/banner3.jpg",

    };
    int categoryId[] ={


            2,
            21,
            12,
            8,
            18,

            6,

            6,
            1,
            23,
            9,
            5,


            3,
            19,
            28,
            17,
            38,

            26,
            20,
            22,
            36,  ///cleaning   40
            36,

            37,  //41 kitchenware
            7,
            6,
            10,
            14,

            14,
            4,
            11,
            16,
            0
    };

    String mProductName[] ={
            "Apparel",
            "Beauty & Care",
            "Electronics",
            "Bags & Luggages",
            "Gifts & Crafts",

            "Basic Needs",



            "Auto accessories",
            "Food & Beverages",
            "Home & Garden",
            "Shoes & Footwear",
            "Jewelry & Eyewear",

            "Textiles",
            "Toys & Hobbies",
            "Tools",
            "Sports & Entertainment",
            "Service & Equipment",

            "Machinery",
            "Health & Medical",
            "Construction & RealEstate",
            "Cleaning Products",
            "Packaging & Printing",

            "Kitchenwares",
            "Transportation",
            "Automobiles & Motorcycles",
            "Computer Hardware & Software",
            "Electrical Equipment & Supplies",

            "Electronic Components & Supplies",
            "Fashion Accessories",
            "Home Appliance",
            "Telecommunication",
            "Agriculture"};

    String mImageUrl[]= {
            "http://edmer.x10host.com/bb/apparel.jpg",
            "http://edmer.x10host.com/bb/beauty.jpg",
            "http://edmer.x10host.com/bb/electronics.jpg",
            "http://edmer.x10host.com/bb/luggages.jpg",
            "http://edmer.x10host.com/bb/gc.jpg",



            "http://edmer.x10host.com/bb/basicneeds.jpg",


            "http://edmer.x10host.com/bb/autoaccess.jpg",
            "http://edmer.x10host.com/bb/f&bCAKE.jpeg",
            "http://edmer.x10host.com/bb/hg.jpg",
            "http://edmer.x10host.com/bb/sf.jpg",
            "http://edmer.x10host.com/bb/JE.jpg",

            "http://edmer.x10host.com/bb/Textiles.jpg",
            "http://edmer.x10host.com/bb/th.jpg",
            "http://edmer.x10host.com/bb/Tools.jpg",
            "http://edmer.x10host.com/bb/SportsEntertainment.jpg",
            "http://edmer.x10host.com/bb/ServiceEquipment.jpg",

            "http://edmer.x10host.com/bb/Machinery.jpg",
            "http://edmer.x10host.com/bb/HealthMedical.jpg",
            "http://edmer.x10host.com/bb/ConstructionRealEstate.jpg",
            "http://edmer.x10host.com/bb/CleaningProducts.jpg",
            "http://edmer.x10host.com/bb/PackagingPrinting.jpg",

            "http://edmer.x10host.com/bb/kitchenware.jpg",
            "http://edmer.x10host.com/bb/trans.jpg",
            "http://edmer.x10host.com/bb/auto.jpeg",
            "http://edmer.x10host.com/bb/computer.png",
            "http://edmer.x10host.com/bb/electricalequip.jpg",

            "http://edmer.x10host.com/bb/electroniccompo.jpg",
            "http://edmer.x10host.com/bb/fa.jpg",
            "http://edmer.x10host.com/bb/HA.jpg",
            "http://edmer.x10host.com/bb/telecommunication.jpg",
            "http://edmer.x10host.com/bb/agriculture.jpg",
    };

    Category_Store newInstance(String mProductName, String mProductImage) {
        Category_Store ViewProductFragment = new Category_Store();

        Bundle args = new Bundle();
        args.putString("Name", mProductName);
        args.putString("Url", mProductImage);
        ViewProductFragment.setArguments(args);

        return ViewProductFragment;
    }
    public Category_Store() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        setHasOptionsMenu(true);

        sessionManager = new SessionManager(getActivity());
        toolbar_subtitle1 = (TextView)view.findViewById(R.id.toolbar_subtitle1);
        toolbar_title1 = (TextView)view.findViewById(R.id.toolbar_title);
        toolbar_title2 = (TextView)view.findViewById(R.id.toolbar_right_subtitle);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        mAdapter = new StoreAdapter(storeData);
        if(mAdapter.getItemCount()==0) {
            prepareVisitData();
            mAdapter = new StoreAdapter(storeData);
            recyclerView.setAdapter(mAdapter);

        }else {

            recyclerView.setAdapter(mAdapter);

        }
//        viewFlipper = (ViewFlipper)view. findViewById(R.id.ViewFlipper01);
//        viewFlipper.setMeasureAllChildren (false);
//
//        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
//                android.R.anim.fade_in));
//        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
//                android.R.anim.fade_out));
//        viewFlipper.showNext();
//        for (int i = 0; i < bannerUrl.length; i++) {
//            ImageView imageView = new ImageView(getActivity());
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//            Glide.with(getActivity())
//                    .load(bannerUrl[i])
//                    .centerCrop()
//                    .dontAnimate()
//                    .placeholder(R.drawable.dialog_background)
//                    .into(imageView);
//            viewFlipper.addView(imageView);
//        }
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setFlipInterval(3000);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
               // ((MainActivity) getActivity()).setTextName(storeData.get(position).getName());



                Store_SubCat_Frag fr=new Store_SubCat_Frag();
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("catname", storeData.get(position).getName());
                args.putString("catid", String.valueOf(storeData.get(position).getCatId()));
                sessionManager.getcatId(String.valueOf(storeData.get(position).getCatId()));
                args.putString("catPic", storeData.get(position).getImgUrl());
                fr.setArguments(args);
                ft.replace(R.id.containerView, fr);
                ft.addToBackStack(null);
                ft.commit();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        //mAdapter.notifyDataSetChanged();


                        // Update your UI here.
                        //((MainActivity) getActivity()).setTextName("STORE");

        ((MainActivity) getActivity()).setTextName("Store Categories");
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        // Set title
//        ((MainActivity) getActivity()).changeTitle("OWRK WORK WORK");
//    }

    private void prepareVisitData() {

        for(int i=0; i<mProductName.length;i++) {
            StoreData newsAndPromosData = new StoreData(mImageUrl[i],mProductName[i],categoryId[i]);
            storeData.add(newsAndPromosData);
        }

    }


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
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
