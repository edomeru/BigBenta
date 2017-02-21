package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;

import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import com.paginate.Paginate;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@RequiresApi(api = Build.VERSION_CODES.M)
public class AdsItems_Frag extends Fragment {
   // implements RecyclerView.OnScrollChangeListener
   SessionManager sessionManager;
    private List<AdsItemsData> storeItemsData = new ArrayList<>();
    private ArrayList<AdsItemRetrofitResponse> latestClass = new ArrayList<>();
    private AdsItemsAdapter mAdapter;
    private RecyclerView.Adapter adapter4Lclassified;
    private RecyclerView recyclerView;
    private GridLayoutManager gaggeredGridLayoutManager;
    private boolean loading = false;
    private int requestCount = 1;
    private int page = 0;
    API_Retrofit service;
    String sub_catname;
    View view;
    Bundle bundle;
    String  subcatid;
    private Handler handler;
    private Paginate paginate;
    TextView PesoSign;
    String mProductName[] ={"chevy silverado","Montero",
            "Mercedez", "Mazda",
            "Mitsubishi", "Honda",
            };

    String mPrice[] ={"360,000","500,000",
            "360,000", "300,000",
            "400,000", "300,000"
           };

    String mImageUrl[]= {"http://blog.caranddriver.com/wp-content/uploads/2014/04/2015-Chevrolet-Silverado-2500HD-High-Country-1012-626x382.jpg",
            "http://auto-mane.com/wp-content/uploads/2015/07/cover10.jpg",
            "http://www.designbolts.com/wp-content/uploads/2013/06/Mercedes-Benz-S-Class-Wallpaper_HD.jpg",
            "http://www.hdcarwallpapers.com/download/2012_mazda_takeri_concept-2560x1600.jpg",
            "http://orig02.deviantart.net/0afa/f/2008/332/f/4/mitsubishi_lancer_evo_hd_by_ilpoli.jpg",
    "http://www.hdwallpapers.in/download/honda_cr_z-2560x1600.jpg"
   };

    AdsItems_Frag newInstance(String mProductName, String mProductImage, String mPrice ) {
        AdsItems_Frag ViewProductFragment = new AdsItems_Frag();

        Bundle args = new Bundle();
        args.putString("Name", mProductName);
        args.putString("Url", mProductImage);
        args.putString("Price", mPrice);
        ViewProductFragment.setArguments(args);

        return ViewProductFragment;
    }
    public AdsItems_Frag() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AdsItemsAdapter(storeItemsData);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_adsitems, container, false);
        sessionManager = new SessionManager(getActivity());
        bundle = getArguments();
        if (bundle != null){
            subcatid = getArguments().getString("sub_catid");
            sub_catname= getArguments().getString("sub_catname");
        }

        ((MainActivity) getActivity()).setTextName(sub_catname);


        adapter4Lclassified= new ClassifiedItemAdapter(latestClass,getActivity());
        recyclerView = (RecyclerView)view.findViewById(R.id.adsitems_recycler_view);
        recyclerView.setHasFixedSize(true);
        //int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());
        gaggeredGridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);

        getData();
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
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
//        } else {
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


//        recyclerView.addOnItemTouchListener(new AdsItems_Frag.RecyclerTouchListener(getActivity(), recyclerView, new AdsItems_Frag.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//
//
//                Fragment fr=new AdsItemDetail();
//                FragmentManager fm= getFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                Bundle args = new Bundle();
//                args.putString("pic", latestClass.get(position).getData().getClassified().getClassified().get(position).getImage().get(0).getImageMd().toString());
//                args.putString("name", latestClass.get(position).getData().getClassified().getClassified().get(position).getTitle().toString());
//                args.putString("price", latestClass.get(position).getData().getClassified().getClassified().get(position).getPrice().toString());
//                args.putString("item_id", latestClass.get(position).getData().getClassified().getClassified().get(position).getId().toString());
//                fr.setArguments(args);
//                ft.replace(R.id.containerView, fr);
//                ft.addToBackStack(null);
//                ft.commit();
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
//              Map<String, String> paramscategories = new HashMap<String, String>();
//        paramscategories.put("app_key", "88888");
//        paramscategories.put("category", "classified");
//        paramscategories.put("category_id", subcatid);
//        paramscategories.put("is_mobile", "1");
//        paramscategories.put("is_primary", "1");
//        paramscategories.put("limit", "12");
//        paramscategories.put("offset", "1");
//        paramscategories.put("sort_by", "date_created");
//        paramscategories.put("order", "desc");



//        ClassifiedItemsService latestClassified = new ClassifiedItemsService(getActivity());
//        latestClassified.serviceLatestClassified(Constants.API_DOMAINTest,Constants.API_search   ,adapter4Lclassified,paramscategories,latestClass);

       // prepareVisitData();
        return view;
    }
    private void prepareVisitData() {

        for(int i=0; i<mProductName.length;i++) {
            AdsItemsData newsAndPromosData = new AdsItemsData(mImageUrl[i],mProductName[i], mPrice[i]);
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
        private AdsItems_Frag.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final AdsItems_Frag.ClickListener clickListener) {
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
    private void getData() {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        progressBar.setVisibility(View.VISIBLE);
        service = retrofit.create(API_Retrofit.class);
        AdsItemRequest serviceRequest = new AdsItemRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory("classified");
        serviceRequest.setCategory_id(subcatid);
        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(requestCount));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setSubcategory("1");
     sessionManager.setAdsitemcatId(subcatid);
         Log.d("CAT ID111111", subcatid);
        Call<AdsItemRetrofitResponse> serviceResponseCall =  service.getAdsItems(serviceRequest);

        serviceResponseCall.enqueue(new Callback<AdsItemRetrofitResponse>() {
            @Override
            public void onResponse(Call<AdsItemRetrofitResponse> call, retrofit2.Response<AdsItemRetrofitResponse> response) {
                int statusCode = response.code();


                AdsItemRetrofitResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                Log.d("COUNT_ITEMDETAIL",String.valueOf(latestClass.size()));
                latestClass.clear();
                for(int i=0;i< serviceResponsee.getData().getClassified().getClassified().size();i++) {

                    latestClass.add(serviceResponsee);


                    adapter4Lclassified.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<AdsItemRetrofitResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
//                Toast.makeText(getActivity(), "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });

        requestCount++;
        Log.d("requestCount",   String.valueOf(requestCount));
    }
    private JsonObjectRequest getDataFromServer(int requestCount) {
        //Initializing ProgressBar
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);

        //Displaying Progressbar
        progressBar.setVisibility(View.VISIBLE);
        //setProgressBarIndeterminateVisibility(true);
        Map<String, String> paramscategories = new HashMap<String, String>();
        paramscategories.put("app_key", "88888");
        paramscategories.put("category", "classified");


        if (bundle != null) {
            paramscategories.put("category_id", subcatid);
        }

        paramscategories.put("is_mobile", "1");
        paramscategories.put("is_primary", "1");
        paramscategories.put("limit", "12");
        paramscategories.put("offset", String.valueOf(requestCount));
        paramscategories.put("sort_by", "date_created");
        paramscategories.put("order", "desc");
        paramscategories.put("subcategory", "1");
        //JsonArrayRequest of volley
        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,Constants.API_DOMAINTest+Constants.API_search,new JSONObject(paramscategories),
                new Response.Listener<JSONObject>() {



                    @Override
                    public void onResponse(JSONObject req) {
                        //Calling method parseData to parse the json response
                       // parseData(req);
                        //Hiding the progressbar
                        progressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        //If an error occurs that means end of the list has reached
                        Toast.makeText(getActivity(), "No More Items Available", Toast.LENGTH_SHORT).show();
                    }
                });

        //Returning the request
        return jsObjReques;
    }

//    private void parseData(JSONObject req) {
//
//        //Creating the superhero object
//        //SuperHero superHero = new SuperHero();
//        JSONObject json = null;
//        System.out.println(req);
//
//        String messege = "";
//        String code = "";
//        String pname= "";
//        String clas = "";
//        String cod= "";
//        String name="";
//        String cdoo= "";
//        String imgs="";
//        String imgclassi = "";
//        String itemId= "";
//        JSONArray strs = null;
//        try {
//
//            messege = req.getString("message");
//            code = req.getString("code");
//            JSONObject data = req.getJSONObject("data");
//            JSONObject classifie = data.getJSONObject("classified");
//
//            //classified objects
//            JSONArray  clasi = classifie.getJSONArray("classified");
//            cod = classifie.getString("code");
//
//            for (int i = 0; i < clasi.length(); i++) {
//
//                JSONObject jsonn = clasi.getJSONObject(i);
//                itemId = jsonn.getString("id");
//                JSONArray imge = jsonn.getJSONArray("image");
//
//                for (int j = 0; j < imge.length(); j++) {
//                    JSONObject jsonImg = imge.getJSONObject(j);
//
//                    imgclassi = jsonImg.getString(Constants.IMAGESY);
//
//
//                }
//
//                JSONArray loc = jsonn.getJSONArray("location");
//
//                for (int j = 0; j < loc.length(); j++) {
//                    JSONObject jsonloc = loc.getJSONObject(j);
//
//                    AdsItemModel setterGetter = new AdsItemModel(jsonn.getString(Constants.PRICE), jsonn.getString(Constants.PRODUCT_NAME), jsonloc.getString(Constants.API_REGIONNAME), imgclassi, jsonn.getString(Constants.API_CREATEDDATE),itemId);
//                    Log.d("SGCLASSIFIED", setterGetter.toString());
//                    latestClass.add(setterGetter);
//                    Log.d("RECYCLERVIEW CLASSIFIED", String.valueOf(latestClass.size()));
//
//                    adapter4Lclassified.notifyDataSetChanged();
//                    Log.d("ADAPTER CLASSIFIED", String.valueOf(adapter4Lclassified.getItemCount()));
//
//                }
//
//
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        //Adding the superhero object to the list
//        // latestClass.add(superHero);
//    }

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
//
//            getData();
//        }
//    }
}
