package ph.dreambig.bigbenta.marketplacev2.AdsSubCat;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag.AdsItems_Frag;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;


import java.util.ArrayList;
import java.util.List;


public class Ads_SubCat_Frag extends Fragment {
    private List<AdsSubData> storeSubData = new ArrayList<>();
    private ArrayList<AdsSubCatRetrofitResponse> latestClass = new ArrayList<>();
    private AdsSubAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter4Lclassified;
    private LinearLayoutManager gaggeredGridLayoutManager;
    private ImageView subcatImg;
    String mProductName[] ={"Cars and Sedan","Coupe/ Sports Cars",
            "UAV/Van/ Mini Van", "SUV's& 4x$", "Pickup& Utility Vehicles",
            "Motorcycles", "Scooters",
            "MPV's&RV's", "Trucks&Trailers"};
    String mImageUrl[]= {"http://moshlab.com/wp-content/uploads/2015/03/Black-Hyundai-Luxury-Sedan-Car-Wallpaper-HD-Images-Free-287323293.jpg","http://edmer.x10host.com/bb/stock.jpg",
            "https://w-dog.net/wallpapers/0/17/311838811322798/peugeot-rcz-sport-coupe-sports-car-coupe.jpg",
            "http://www.aveoflorida.com/wp-content/uploads/2015/10/EyeBeamMini-01.png",
            "http://www.wlivenews.com/wp-content/uploads/2015/03/Mahindra-Komodo-4X4-SUV-Concept.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/3/3f/Gaz69-2.jpg",
            "http://www.hdwallpapersnews.com/wp-content/uploads/2015/03/Custom-Motorcycles-Full-HD-Wallpapers.jpg",
"http://www.ramuzzi.com/upload/noleggio/foto/symphony125.jpg",
            "https://static.vigattintrade.com/images/2013/1025/1_526ab301d328d_800_426_.jpg",
            "http://oldchevycars.com/wp-content/uploads/2015/08/chevy-truck-oldchevycars.com-HD-51.jpg"
    };
    Ads_SubCat_Frag newInstance(String mProductName, String mProductImage) {
        Ads_SubCat_Frag ViewProductFragment = new Ads_SubCat_Frag();

        Bundle args = new Bundle();
        args.putString("Name", mProductName);
        args.putString("Url", mProductImage);
        ViewProductFragment.setArguments(args);

        return ViewProductFragment;
    }

    public Ads_SubCat_Frag() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AdsSubAdapter(storeSubData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ads__sub_cat_, container, false);


        String  catName = getArguments().getString("catname");
        String  catid = getArguments().getString("catid");
        String  catPic = getArguments().getString("catPic");

        ((MainActivity) getActivity()).setTextName(catName);

        TextView sub_ads_name;
        ImageView sub_ads_img;
        adapter4Lclassified= new LatestClassifiedAdapter(latestClass,getActivity());
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_subcat_ads);
        subcatImg = (ImageView) view.findViewById(R.id.sub_cat_img);
        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setAdapter(adapter4Lclassified);
        sub_ads_img  = (ImageView)view.findViewById(R.id.sub_ads_img);
        sub_ads_name = (TextView)view.findViewById(R.id.sub_ads_name);
        Typeface Abold = Typeface.createFromAsset(getActivity().getAssets(), "Bariol_Regular.otf");
        sub_ads_name.setTypeface(Abold);
        sub_ads_name.setText(catName);
        Glide.with(getActivity())
                .load(catPic)
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.bbpaula)
                .into(sub_ads_img);


        recyclerView.addOnItemTouchListener(new Ads_SubCat_Frag.RecyclerTouchListener(getActivity(), recyclerView, new Ads_SubCat_Frag.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                AdsItems_Frag fr=new AdsItems_Frag();
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();

                args.putString("sub_catid", String.valueOf(latestClass.get(position).getData().get(position).getCategoryId()));
                args.putString("sub_catname", String.valueOf(latestClass.get(position).getData().get(position).getName().toString()));
                fr.setArguments(args);
                ft.replace(R.id.containerView, fr);
                ft.addToBackStack(null);
                ft.commit();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

//        Map<String, String> paramscategories = new HashMap<String, String>();
//        paramscategories.put("app_key", "88888");
//        paramscategories.put("category_id", catid);
//        paramscategories.put("is_mobile", "1");


        LatestClassifiedService latestClassified = new LatestClassifiedService(getActivity());
        latestClassified.serviceLatestClassified(Constants.API_DOMAINTest,Constants.API_ClassifiedSub,adapter4Lclassified,latestClass,catid);


       //prepareVisitData();
        return view;
    }
    private void prepareVisitData() {

        for(int i=0; i<mProductName.length;i++) {
            AdsSubData newsAndPromosData = new AdsSubData(mImageUrl[i],mProductName[i]);
            storeSubData.add(newsAndPromosData);
        }
        mAdapter.notifyDataSetChanged();
        Glide.with(getActivity())
                .load("http://www.hdcarwallpapers.com/walls/audi_a6_sedan-normal.jpg")
                .centerCrop()
                .into(subcatImg);

    }
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    public static  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Ads_SubCat_Frag.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Ads_SubCat_Frag.ClickListener clickListener) {
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
