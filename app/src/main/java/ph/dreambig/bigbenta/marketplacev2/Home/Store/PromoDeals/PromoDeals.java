package ph.dreambig.bigbenta.marketplacev2.Home.Store.PromoDeals;


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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemModel;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemsAdapter;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemsData;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItems_Frag;

import org.json.JSONArray;
import org.json.JSONException;
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
public class PromoDeals extends Fragment {

    private ArrayList<DealsResponse> latestClass = new ArrayList<>();
    private List<StoreItemsData> storeItemsData = new ArrayList<>();
    private StoreItemsAdapter mAdapter;
    private int requestCount = 1;
    View view;
    TextView labelNotification;
    API_Retrofit service;
    JSONArray strs = null;
    JSONObject stores = null;
    Bundle args;
    Bundle bundle;
    SessionManager sessionManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter4Lclassified;
    private GridLayoutManager gaggeredGridLayoutManager;
    TextView PesoSign;
    public PromoDeals() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.promo_deals_store, container, false);

        setHasOptionsMenu(true);
//        bundle = getArguments();
//        if (bundle != null){
//            catid = getArguments().getString("catid");
//        }
        // Log.d("CAT IDITEM", catid);
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        sessionManager = new SessionManager(getActivity());
        adapter4Lclassified= new DealsAdapter(latestClass,getActivity());
        recyclerView = (RecyclerView)view.findViewById(R.id.deals_recycler_view);
        recyclerView.setHasFixedSize(true);
        labelNotification = (TextView)view.findViewById(R.id.labelNotification);
        labelNotification.setTypeface(bariol);
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

        return view;
    }
    private void getData() {

        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        progressBar.setVisibility(View.VISIBLE);
        service = retrofit.create(API_Retrofit.class);
        DealsRequest serviceRequest = new DealsRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory("stores");

        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(requestCount));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setIs_sale("1");

       // Log.d("CAT ID111111", catid);
        Call<DealsResponse> serviceResponseCall =  service.getDeals(serviceRequest);

        serviceResponseCall.enqueue(new Callback<DealsResponse>() {
            @Override
            public void onResponse(Call<DealsResponse> call, retrofit2.Response<DealsResponse> response) {
                int statusCode = response.code();


                DealsResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                Log.d("COUNT_ITEMDETAIL",String.valueOf(latestClass.size()) );
                latestClass.clear();
                if(serviceResponsee.getData().getStores().getStores().size()==0){
                    labelNotification.setText("No Promos/ Deals Yet");
                    labelNotification.setVisibility(View.VISIBLE);
                }else {
                    for (int i = 0; i < serviceResponsee.getData().getStores().getStores().size(); i++) {

                        latestClass.add(serviceResponsee);

                        if (i==serviceResponsee.getData().getStores().getStores().size() -1) {
                            requestCount++;
                        }
                        adapter4Lclassified.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<DealsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                requestCount=1;
                //Toast.makeText(getActivity(), "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });

        requestCount++;
        Log.d("requestCount",   String.valueOf(requestCount));


    }

//    @Override
//    public void onResume() {
//        super.onResume();
//       if(adapter4Lclassified.getItemCount()==0){
//
//       }
//
//    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        super.onPrepareOptionsMenu(menu);
    }




    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    static  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private StoreItems_Frag.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final StoreItems_Frag.ClickListener clickListener) {
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
    private void parseData(JSONObject req) {

        //Creating the superhero object
        //SuperHero superHero = new SuperHero();
        JSONObject json = null;
        System.out.println(req);
        String messege = "";
        String code = "";
        String pname= "";
        String clas = "";
        String cod= "";
        String name="";
        String cdoo= "";
        String imgs="";
        String imgclassi = "";

        try {

            messege = req.getString("message");
            code = req.getString("code");
            JSONObject data = req.optJSONObject("data");
//                            JSONObject classifie = data.getJSONObject("classified");
//
//                            //classified objects
//                            clas = classifie.getString("classified");
//                            cod = classifie.getString("code");

            stores = data.optJSONObject("stores");
            cdoo = stores.optString("code");



            strs = stores.optJSONArray("stores");

            for (int i = 0; i < strs.length(); i++) {

                JSONObject jsonn = strs.getJSONObject(i);

                //  setterGetter = new LatestStoreItems(json.getInt(Constants.PRICE), json.getString(Constants.PRODUCT_NAME), json.getString(Constants.STORE_NAME), json.getString(Constants.IMAGE));




                JSONArray imge = jsonn.getJSONArray("image");

                for (int j = 0; j < imge.length(); j++) {
                    JSONObject jsonImg = imge.getJSONObject(j);


                    StoreItemModel setterGetter = new StoreItemModel(jsonn.getString(Constants.PRICE), jsonn.getString(Constants.PRODUCT_NAME),
                            jsonn.getString(Constants.STORE_NAME), jsonImg.getString(Constants.IMAGE_MD),jsonn.getString("id"));
//                                            setterGetter.setid(json.getString(Constants.ID));
//                                            setterGetter.setprice(json.getString(Constants.PRICE));
//                                            setterGetter.setprod_name(json.getString(Constants.PRODUCT_NAME));
//                                            setterGetter.setstore_name(json.getString(Constants.STORE_NAME));
//
//
//
//                                                setterGetter.setimage(jsonImg.optString(Constants.IMAGESY));
//
//
//
//                                                setterGetter.setIMAGE_LG(jsonImg.optString(Constants.IMAGE_LG));




                    //  storeItemsData.add(setterGetter);


                    adapter4Lclassified.notifyDataSetChanged();


                }



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Adding the superhero object to the list
        // latestClass.add(superHero);
    }

    //Notifying the adapter that data has been added or changed
    // adapter4Lclassified.notifyDataSetChanged();


    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }

    //Overriden method to detect scrolling
//    @Override
//    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        //Ifscrolled at last then
//        if (isLastItemDisplaying(recyclerView)) {
//            //Calling the method getdata again
//            getData();
//        }
//    }



}
