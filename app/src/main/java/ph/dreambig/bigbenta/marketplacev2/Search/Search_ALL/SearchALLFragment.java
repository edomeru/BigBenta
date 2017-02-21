package ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.Search.SearchActivitySample;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemsAdapter;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemsData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchALLFragment extends Fragment {
    String search="";
    private ArrayList<SearchALLResponse> latestClass = new ArrayList<>();
    private ArrayList<SearchALLResponse> listads = new ArrayList<>();
    private List<StoreItemsData> storeItemsData = new ArrayList<>();
    private StoreItemsAdapter mAdapter;
    private int requestCount = 1;
    private int requestCount2 = 1;
    View x;
    static TextView labelNotification,labelNotificationAds;
    private final static int FADE_DURATION = 1000;
    API_Retrofit service;
    JSONArray strs = null;
    JSONObject stores = null;
    Bundle args;
    Bundle bundle;
    SessionManager sessionManager;
    private RecyclerView recyclerView;
    RecyclerView recyclerViewAds;
    private RecyclerView.Adapter adapter4Lclassified;
    private RecyclerView.Adapter adapter4Ads;
    private LinearLayoutManager gaggeredGridLayoutManager;
    private LinearLayoutManager LayoutManager;
    TextView resultsforstore,resultsforads;
    public SearchALLFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        x = inflater.inflate(R.layout.fragment_store_all, container, false);
        setHasOptionsMenu(true);
        bundle = getArguments();
        if (bundle != null){
            search = getArguments().getString(Constants.KEY_SEARCH);
        }
        ((SearchActivitySample) getActivity()).setTextTitle(search.toUpperCase());
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        labelNotificationAds= (TextView)x.findViewById(R.id.labelNotificationAds);
        labelNotificationAds.setTypeface(bariol);
        labelNotification = (TextView)x.findViewById(R.id.labelNotification);
        labelNotification.setTypeface(bariol);
        resultsforads = (TextView) x.findViewById(R.id.resultsforads);
        resultsforstore = (TextView) x.findViewById(R.id.resultsforstore);
        sessionManager = new SessionManager(getActivity());
        adapter4Lclassified= new SearchALLStoreAdapter(latestClass,getActivity());
        adapter4Ads= new SearchALLAdsAdapter(listads,getActivity());
        recyclerView = (RecyclerView)x.findViewById(R.id.store_search_all_recycler_view);
        recyclerViewAds = (RecyclerView)x.findViewById(R.id.ads_search_all_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerViewAds.setHasFixedSize(true);
        Typeface Abold = Typeface.createFromAsset(getContext().getAssets(), "Bariol_Regular.otf");
        resultsforstore.setTypeface(Abold);
        resultsforads.setTypeface(Abold);
        //int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());
        gaggeredGridLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        LayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerViewAds.setLayoutManager(LayoutManager);
        getData();
        getData2();
        //if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//Ifscrolled at last then
                if (isLastItemDisplaying(recyclerView)) {
//Calling the method getdata again
                    getData();
                }
            }
        });
        recyclerView.setAdapter(adapter4Lclassified);

        recyclerViewAds.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//Ifscrolled at last then
                if (isLastItemDisplaying(recyclerView)) {
//Calling the method getdata again
                    getData2();
                }
            }
        });
        recyclerViewAds.setAdapter(adapter4Ads);

        return x ;
    }
    private void getData() {
        final ProgressBar progressBar = (ProgressBar)x. findViewById(R.id.progressBar1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        progressBar.setVisibility(View.VISIBLE);
        service = retrofit.create(API_Retrofit.class);
        SearchALL serviceRequest = new SearchALL();
        serviceRequest.setApp_key("88888");
        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(requestCount));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setSearch(search);

        // Log.d("CAT ID111111", catid);
        Call<SearchALLResponse> serviceResponseCall =  service.getALLSearch(serviceRequest);

        serviceResponseCall.enqueue(new Callback<SearchALLResponse>() {
            @Override
            public void onResponse(Call<SearchALLResponse> call, retrofit2.Response<SearchALLResponse> response) {
                int statusCode = response.code();


                SearchALLResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                //Log.d("COUNT_ITEMDETAIL",String.valueOf(latestClass.size()) );

                latestClass.clear();
                for(int i=0;i< serviceResponsee.getData().getStores().getStores().size();i++) {

                    latestClass.add(serviceResponsee);

                    if (i==serviceResponsee.getData().getStores().getStores().size() -1) {
                        requestCount++;
                    }
                    Log.d("requestCountSTORE",   String.valueOf(requestCount));
                    adapter4Lclassified.notifyDataSetChanged();



                }
                progressBar.setVisibility(View.GONE);



            }

            @Override
            public void onFailure(Call<SearchALLResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                requestCount=1;
        if (adapter4Lclassified.getItemCount() == 0 ) {
            resultsforstore.setVisibility(View.INVISIBLE);
            labelNotification.setText("No Store Item/s Found for"+" "+search.toUpperCase());
            labelNotification.setVisibility(View.VISIBLE);
            setScaleAnimation(labelNotification);
        }
//                Toast.makeText(getActivity(), "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });
//        requestCount++;
//        Log.d("requestCount",   String.valueOf(requestCount));


    }

    private void getData2() {
        final ProgressBar progressBar = (ProgressBar)x. findViewById(R.id.progressBar1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        progressBar.setVisibility(View.VISIBLE);
        service = retrofit.create(API_Retrofit.class);
        SearchALL serviceRequest = new SearchALL();
        serviceRequest.setApp_key("88888");
        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(requestCount2));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setSearch(search);

        // Log.d("CAT ID111111", catid);
        Call<SearchALLResponse> serviceResponseCall =  service.getALLSearch(serviceRequest);

        serviceResponseCall.enqueue(new Callback<SearchALLResponse>() {
            @Override
            public void onResponse(Call<SearchALLResponse> call, retrofit2.Response<SearchALLResponse> response) {
                int statusCode = response.code();


                SearchALLResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                //Log.d("COUNT_ITEMDETAIL",String.valueOf(latestClass.size()) );



                listads.clear();
                for(int j=0;j< serviceResponsee.getData().getClassified().getClassified().size();j++) {

                    listads.add(serviceResponsee);

                    if (j ==serviceResponsee.getData().getClassified().getClassified().size() -1) {
                        requestCount2++;
                    }


                    Log.d("requestCountADS",   String.valueOf(requestCount2));
                    adapter4Ads.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SearchALLResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                requestCount2=1;
                if (adapter4Ads.getItemCount() == 0 ) {
                    resultsforads.setVisibility(View.INVISIBLE);
                    labelNotificationAds.setText("No Classified Ads Item/s Found for"+" "+search.toUpperCase());
                    labelNotificationAds.setVisibility(View.VISIBLE);
                    setScaleAnimation(labelNotificationAds);
                }
                //Toast.makeText(getActivity(), "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });
//        requestCount++;
//        Log.d("requestCount",   String.valueOf(requestCount));


    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.action_service).setVisible(false);
//        menu.findItem(R.id.action_cads).setVisible(false);
//        menu.findItem(R.id.action_cart).setVisible(true);
        // menu.findItem(R.id.action_chat).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SearchALLFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SearchALLFragment.ClickListener clickListener) {
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

    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        latestClass.clear();
//        if (adapter4Lclassified.getItemCount() == 0 ) {
////                if (progressBar.isShown()) {
////                    labelNotification.setVisibility(View.GONE);
////
////                }else{
//            labelNotification.setText("No Item/s Yet for this Category");
//            labelNotification.setVisibility(View.VISIBLE);
//            setScaleAnimation(labelNotification);
//        }
//    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

}
