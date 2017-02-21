package ph.dreambig.bigbenta.marketplacev2.StoreItems;


import android.content.Context;
import android.graphics.Color;
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
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.realm.RealmResults;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.RealmFolder.RealmDataAdapter;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;

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

public class StoreItems_Frag extends Fragment  {
    private ArrayList<StoreItemRetrofitResponse> latestClass = new ArrayList<>();
    private List<StoreItemsData> storeItemsData = new ArrayList<>();
    private StoreItemsAdapter mAdapter;
    private int requestCount = 1;
    ProgressBar progressBar;
    View view;
    int lastVisibleItemPosition;
    private final static int FADE_DURATION = 1000;
    private final static int PAGE_SIZE = 12;
    private static int SPLASH_TIME_OUT = 2000;
   static TextView labelNotification;
    API_Retrofit service;
    JSONArray strs = null;
    JSONObject stores = null;
    Bundle args;
    Bundle bundle;
    SessionManager sessionManager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter4Lclassified;
    private GridLayoutManager gaggeredGridLayoutManager;
    TextView PesoSign,titleLabel;
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
    public StoreItems_Frag() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new StoreItemsAdapter(storeItemsData);
    }
    String catid,subcat_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_storeitems, container, false);
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
       // titleLabel= (TextView)view.findViewById(R.id.titleLabel);
        labelNotification = (TextView)view.findViewById(R.id.labelNotification);
        labelNotification.setTypeface(bariol);
        setHasOptionsMenu(true);

         bundle = getArguments();
        if (bundle != null){
             catid = getArguments().getString("catid");
            subcat_name= getArguments().getString("subcat_name");
    }
        ((MainActivity) getActivity()).setTextName(subcat_name);
        sessionManager = new SessionManager(getActivity());

        adapter4Lclassified= new StoreItemAdapter(latestClass,getActivity());
        recyclerView = (RecyclerView)view.findViewById(R.id.storeitems_recycler_view);
        recyclerView.setHasFixedSize(true);

        gaggeredGridLayoutManager = new GridLayoutManager(getActivity(),2);

        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        getData();


            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                if(progressBar.isShown() ) {
//                    titleLabel.setVisibility(View.GONE);
//                }else{
//                    if(adapter4Lclassified.getItemCount() == 0) {
//                        titleLabel.setVisibility(View.GONE);
//                    }else{
//                        titleLabel.setVisibility(View.VISIBLE);
//                    }
//                }

                if (isLastItemDisplaying(recyclerView)) {
                    //latestClass.clear();
                    getData();
                }

            }
        });



        recyclerView.setAdapter(adapter4Lclassified);
        return view;
    }

    private void getData() {

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        progressBar.setVisibility(View.VISIBLE);
        service = retrofit.create(API_Retrofit.class);
        StoreItemRequest serviceRequest = new StoreItemRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory("stores");
        serviceRequest.setCategory_id(catid);
        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(requestCount));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setSubcategory("1");

        Call<StoreItemRetrofitResponse> serviceResponseCall =  service.getStoreItems(serviceRequest);
        serviceResponseCall.enqueue(new Callback<StoreItemRetrofitResponse>() {

            @Override
            public void onResponse(Call<StoreItemRetrofitResponse> call, retrofit2.Response<StoreItemRetrofitResponse> response) {
                int statusCode = response.code();

                StoreItemRetrofitResponse  serviceResponsee = response.body();

                latestClass.clear();
                    for (int i = 0; i < serviceResponsee.getData().getStores().getStores().size(); i++) {
                        latestClass.add(serviceResponsee);
                        if (i == serviceResponsee.getData().getStores().getStores().size() - 1) {
                            requestCount++;
                        }
                        adapter4Lclassified.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<StoreItemRetrofitResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                requestCount=1;
                if (adapter4Lclassified.getItemCount() == 0 ) {
                    labelNotification.setText("No Store Item/s Yet for this Sub Category");
                    labelNotification.setVisibility(View.VISIBLE);
                    setScaleAnimation(labelNotification);
                }
            }
        });
    }

//    private JsonObjectRequest getDataFromServer(int requestCount) {
//        //Initializing ProgressBar
//        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
//
//        //Displaying Progressbar
//        progressBar.setVisibility(View.VISIBLE);
//        Map<String, String> paramscategories = new HashMap<String, String>();
//        paramscategories.put("app_key", "88888");
//        paramscategories.put("category", "stores");
//
//
//        if (bundle != null) {
//            paramscategories.put("category_id", catid);
//        }
//
//        paramscategories.put("is_mobile", "1");
//        paramscategories.put("is_primary", "1");
//        paramscategories.put("limit", "12");
//        paramscategories.put("offset", String.valueOf(requestCount));
//        paramscategories.put("sort_by", "date_created");
//        paramscategories.put("order", "desc");
//        paramscategories.put("subcategory", "1");
//        //JsonArrayRequest of volley
//        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,Constants.API_DOMAINTest+Constants.API_search,new JSONObject(paramscategories),
//                new Response.Listener<JSONObject>() {
//
//                    StoreItemModel   setterGetter;
//
//                    @Override
//                    public void onResponse(JSONObject req) {
//                        //Calling method parseData to parse the json response
//                        parseData(req);
//                        //Hiding the progressbar
//                        progressBar.setVisibility(View.GONE);
//                    }
//                },
//                 new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressBar.setVisibility(View.GONE);
//                        //If an error occurs that means end of the list has reached
//                        Toast.makeText(getActivity(), "No More Items Available", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        //Returning the request
//        return jsObjReques;
//    }
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

//    @Override
//    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//    }

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


                        StoreItemModel   setterGetter = new StoreItemModel(jsonn.getString(Constants.PRICE), jsonn.getString(Constants.PRODUCT_NAME),
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

//    @Override
//    public void onResume() {
//        super.onResume();
//        latestClass.clear();
//            if (adapter4Lclassified.getItemCount() == 0 ) {
////                if (progressBar.isShown()) {
////                    labelNotification.setVisibility(View.GONE);
////
////                }else{
//
//
//
//                    labelNotification.setText("No Item/s Yet for this Category");
//                    labelNotification.setVisibility(View.VISIBLE);
//                    setScaleAnimation(labelNotification);
//
////                }
////
//
////            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
////                    .setTitleText("Empty Shopping Cart")
////                    .setContentText("You have no items in your shopping cart.")
////                    .setConfirmText("Continue shopping")
////                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                        @Override
////                        public void onClick(SweetAlertDialog sDialog) {
//////                            MainActivity.popFragment("Shopping Cart");
//////                            MainActivity.setFragment("Visit Us");
////                            sDialog.dismissWithAnimation();
////                        }
////                    })
////                    .show();
//
//            }
//
//
//
//
//    }
    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
             lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
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
