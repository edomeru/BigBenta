package ph.dreambig.bigbenta.marketplacev2.Search;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;
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
public class SearchADSFragment extends Fragment {
String search="";
    static TextView labelNotification;
    private ArrayList<SearchADSResponse> latestClass = new ArrayList<>();
    private List<StoreItemsData> storeItemsData = new ArrayList<>();
    private StoreItemsAdapter mAdapter;
    private int requestCount = 1;
    View x;
    private final static int FADE_DURATION = 5000;
    API_Retrofit service;
    JSONArray strs = null;
    JSONObject stores = null;
    Bundle args;
    Bundle bundle;
    SessionManager sessionManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter4Lclassified;
    private GridLayoutManager gaggeredGridLayoutManager;
    public SearchADSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        x = inflater.inflate(R.layout.fragment_search_item, container, false);
        setHasOptionsMenu(true);
        bundle = getArguments();
        if (bundle != null){
            search = getArguments().getString(Constants.KEY_SEARCH);
        }
        ((MainActivity) getActivity()).setTextName(search);

        labelNotification = (TextView)x.findViewById(R.id.labelNotification);
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        labelNotification.setTypeface(bariol);
        sessionManager = new SessionManager(getActivity());
        adapter4Lclassified= new SearchADSAdapter(latestClass,getActivity());
        recyclerView = (RecyclerView)x.findViewById(R.id.store_recycler_view);
        recyclerView.setHasFixedSize(true);
        //int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());
        gaggeredGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        getData();

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
        //}
// else {
//            recyclerView.setOnScrollChangeListener( new RecyclerView.OnScrollChangeListener() {
//                @Override
//                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
////Ifscrolled at last then
//                    if (isLastItemDisplaying(recyclerView)) {
////Calling the method getdata again
//                        getData();
//                    }
//                }
//            });
//        }
        recyclerView.setAdapter(adapter4Lclassified);

        return x ;
    }
    private void getData() {
        final ProgressBar progressBar = (ProgressBar) x.findViewById(R.id.progressBar1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        progressBar.setVisibility(View.VISIBLE);
        service = retrofit.create(API_Retrofit.class);
        SearchADSRequest serviceRequest = new SearchADSRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory("classified");
        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(requestCount));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setSearch(search);

        // Log.d("CAT ID111111", catid);
        Call<SearchADSResponse> serviceResponseCall =  service.getStoreSearchInfo(serviceRequest);

        serviceResponseCall.enqueue(new Callback<SearchADSResponse>() {
            @Override
            public void onResponse(Call<SearchADSResponse> call, retrofit2.Response<SearchADSResponse> response) {
                int statusCode = response.code();


                SearchADSResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                //Log.d("COUNT_ITEMDETAIL",String.valueOf(latestClass.size()) );

                latestClass.clear();
                for(int i=0;i< serviceResponsee.getData().getClassified().getClassified().size();i++) {

                    latestClass.add(serviceResponsee);

//                    if (serviceResponsee.getData().getStores().getStores().size() != 0) {
//                        requestCount++;
//                    }
                    Log.d("requestCount",   String.valueOf(requestCount));
                    adapter4Lclassified.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<SearchADSResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                //Toast.makeText(getActivity(), "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });
        requestCount++;
        Log.d("requestCount",   String.valueOf(requestCount));


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
        private SearchADSFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SearchADSFragment.ClickListener clickListener) {
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

    @Override
    public void onResume() {
        super.onResume();
        latestClass.clear();
        if (adapter4Lclassified.getItemCount() == 0 ) {
//                if (progressBar.isShown()) {
//                    labelNotification.setVisibility(View.GONE);
//
//                }else{



            labelNotification.setText("No Item/s Yet for this Category");
            labelNotification.setVisibility(View.VISIBLE);
            setScaleAnimation(labelNotification);

//                }
//

//            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                    .setTitleText("Empty Shopping Cart")
//                    .setContentText("You have no items in your shopping cart.")
//                    .setConfirmText("Continue shopping")
//                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sDialog) {
////                            MainActivity.popFragment("Shopping Cart");
////                            MainActivity.setFragment("Visit Us");
//                            sDialog.dismissWithAnimation();
//                        }
//                    })
//                    .show();

        }




    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

}
