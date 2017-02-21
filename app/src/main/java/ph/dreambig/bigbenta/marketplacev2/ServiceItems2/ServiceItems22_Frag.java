package ph.dreambig.bigbenta.marketplacev2.ServiceItems2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemsAdapter;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemsData;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItems_Frag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceItems22_Frag extends Fragment implements Serializable {
    private ArrayList<ServiceResponse> latestClass = new ArrayList<>();
    private List<StoreItemsData> storeItemsData = new ArrayList<>();
    private StoreItemsAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter4Lclassified;
    private GridLayoutManager gaggeredGridLayoutManager;
    String criteria_id,category_name;
    TextView PesoSign;
    String mProductName[] ={"Charisse J007","Margaux J006 Black / White",
            "Marie J005", "Tisha J004",
            "Julie J002", "Diane J001",
            "AH09", "AH10","AH30","AH26","AH28"};

    String mPrice[] ={"600","480",
            "480", "600",
            "480", "600",
            "600", "600","900","480","1,176"};

    String mImageUrl[]= {"https://data.bigbenta.com/storeapp/images/2363/2016080914583857a97f1e8764e-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2362/2016080914524457a97dbc897f0-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2361/2016080912165157a95933d530d-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2360/2016080911543857a953fe48484-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2359/2016080910592957a94711c7c91-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2358/2016080910282157a93fc57fcec-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2357/2016080910124157a93c19608eb-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2356/2016080909494057a936b452ba2-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2355/2016080909413257a934ccc1121-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2353/2016080819342957a86e458eb6c-smd.jpeg",
            "https://data.bigbenta.com/storeapp/images/2353/2016080819342957a86e458eb6c-smd.jpeg"};

    StoreItems_Frag newInstance(String mProductName, String mProductImage, String mPrice ) {
        StoreItems_Frag ViewProductFragment = new StoreItems_Frag();

        Bundle args = new Bundle();
        args.putString("Name", mProductName);
        args.putString("Url", mProductImage);
        args.putString("Price", mPrice);
        ViewProductFragment.setArguments(args);

        return ViewProductFragment;
    }
    public ServiceItems22_Frag() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new StoreItemsAdapter(storeItemsData);
    }
    String catid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.fragment_serviceitems2, container, false);
        setHasOptionsMenu(true);
        Bundle bundle = getArguments();
        if (bundle != null){
            criteria_id = getArguments().getString("criteria_id");
            category_name = getArguments().getString("category_name");
        }
        ((MainActivity) getActivity()).setTextName(category_name);
        adapter4Lclassified= new SterviceItem2Adapter(latestClass,getActivity());
        recyclerView = (RecyclerView)view.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.serviceitems_recycler_view);
        recyclerView.setHasFixedSize(true);
      //  int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());
        gaggeredGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setAdapter(adapter4Lclassified);
//        recyclerView.addOnItemTouchListener(new ServiceItems22_Frag.RecyclerTouchListener(getActivity(), recyclerView, new ServiceItems22_Frag.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
////                StoreItems_Frag fragment = new StoreItems_Frag();
////                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
////                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.replace(R.id.containerView, fragment);
////                fragmentTransaction.addToBackStack(null);
////                fragmentTransaction.commit();
//
//                Fragment fr=new ServiceItemDetail();
//                FragmentManager fm=getFragmentManager();
//                android.support.v4.app.FragmentTransaction ft=fm.beginTransaction();
//                Bundle args = new Bundle();
//                args.putString("srvce_id", latestClass.get(position).getData().get(position).getSrvcId());
//                fr.setArguments(args);
//                ft.replace(ph.dreambig.bigbenta.marketplace.R.id.containerView, fr);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));


//        Map<String, String> paramscategories = new HashMap<String, String>();
//
//        if (bundle != null) {
//            paramscategories.put("criteria_id", criteria_id);
//        }
//        paramscategories.put("filter", "main");


//        ServiceITEM latestClassified = new ServiceITEM(getActivity());
//        latestClassified.serviceStoreCategory(Constants.API_SERVICETESTDOMAIN,Constants.API_SERVICESEARCH   ,adapter4Lclassified,paramscategories,latestClass);





        ServiceITEM latestClassified = new ServiceITEM(getActivity());
        latestClassified.serviceStoreCategory(Constants.API_SERVICETESTDOMAIN,Constants.API_SERVICESEARCH   ,adapter4Lclassified,criteria_id,latestClass);
        //prepareVisitData();
        return view;
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.action_service).setVisible(false);
//        menu.findItem(R.id.action_cads).setVisible(false);
//        menu.findItem(R.id.action_cart).setVisible(true);
       // menu.findItem(R.id.action_chat).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }
    private void prepareVisitData() {

        for(int i=0; i<mProductName.length;i++) {
            StoreItemsData newsAndPromosData = new StoreItemsData(mImageUrl[i],mProductName[i], mPrice[i]);
            storeItemsData.add(newsAndPromosData);
        }
        mAdapter.notifyDataSetChanged();
    }
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ServiceItems22_Frag.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ServiceItems22_Frag.ClickListener clickListener) {
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
