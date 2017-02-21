package ph.dreambig.bigbenta.marketplacev2.Store_SubCat;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.AdsSubCat.LatestCategory;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItems_Frag;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Store_SubCat_Frag extends Fragment {
    SessionManager sessionManager;
    Bundle bundle;
    API_Retrofit service;
    String catName;
    String catid;
    String catPic;
    TextView labelNotification;
    private List<StoreSubData> storeSubData = new ArrayList<>();
    private StoreSubAdapter mAdapter;
    private ArrayList<StoreSubRetrofit> latestStr = new ArrayList<>();
    private ArrayList<LatestCategory> latestClass = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    private RecyclerView recyclerView;
    private LinearLayoutManager gaggeredGridLayoutManager;
    private ImageView subcatImg;
    String mProductName[] ={"Apparel Design Services","Apparel Stock",
            "Children's Clothing", "Ethnic Clothing", "Girl's Clothing",
            "Jackets", "Ladies Blouses & Tops",
            "Maternity Clothing", "Mens Shirts","Plus Size Clothing",
            "Shorts", "Sleepwear","Sweaters"};
    String mImageUrl[]= {"http://edmer.x10host.com/bb/Design.jpg","http://edmer.x10host.com/bb/stock.jpg",
            "http://edmer.x10host.com/bb/children.jpeg", "http://edmer.x10host.com/bb/ethnic.jpg",
            "http://edmer.x10host.com/bb/girl.jpg","http://edmer.x10host.com/bb/jackets.jpeg",
            "http://edmer.x10host.com/bb/ladies.jpg", "http://edmer.x10host.com/bb/preg.jpg",
            "http://edmer.x10host.com/bb/menshirt.jpg",
            "http://edmer.x10host.com/bb/plus.jpg","http://edmer.x10host.com/bb/shrts.jpg",
            "http://edmer.x10host.com/bb/sleep.jpg",
            "http://edmer.x10host.com/bb/sweat.jpg"};
    Store_SubCat_Frag newInstance(String mProductName, String mProductImage) {
        Store_SubCat_Frag ViewProductFragment = new Store_SubCat_Frag();

        Bundle args = new Bundle();
        args.putString("Name", mProductName);
        args.putString("Url", mProductImage);
        ViewProductFragment.setArguments(args);





        return ViewProductFragment;
    }

    public Store_SubCat_Frag() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new StoreSubAdapter(storeSubData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.fragment_store__sub_cat_, container, false);
        setHasOptionsMenu(true);
        sessionManager = new SessionManager(getActivity());
        TextView sub_cat_name;
        ImageView sub_cat_img;

        bundle = getArguments();
        if (bundle != null) {
             catName = getArguments().getString("catname");
             catid = getArguments().getString("catid");
             catPic = getArguments().getString("catPic");
        }

        Log.d("SUCAT_catid",  catid);
        sessionManager.getcatId(catid);
        ((MainActivity) getActivity()).setTextTitle(catName);
        adapter = new LatestStoreItemsAdapter(latestStr,getActivity() );
        recyclerView = (RecyclerView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.recycler_view_subcat);
        sub_cat_img  = (ImageView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.sub_cat_img);
        sub_cat_name = (TextView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.sub_cat_name);
        sub_cat_name.setText(catName);
        Typeface Caviarr = Typeface.createFromAsset(getActivity().getAssets(), "Bariol_Regular.otf");
       sub_cat_name.setTypeface(Caviarr);
        Glide.with(getActivity())
                .load(catPic)
                .centerCrop()
                .dontAnimate()
                .placeholder(ph.dreambig.bigbenta.marketplacev2.R.drawable.bbpaula)
                .into(sub_cat_img);
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        labelNotification = (TextView)x.findViewById(R.id.labelNotification);
        labelNotification.setTypeface(bariol);
        subcatImg = (ImageView) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.sub_cat_img);
        recyclerView.setHasFixedSize(true);
        gaggeredGridLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new Store_SubCat_Frag.RecyclerTouchListener(getActivity(), recyclerView, new Store_SubCat_Frag.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                StoreItems_Frag fr=new StoreItems_Frag();
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();

                args.putString("catid", String.valueOf(latestStr.get(position).getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().get(position).getCategoryId().toString()));
                args.putString("subcat_name", String.valueOf(latestStr.get(position).getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().get(position).getName().toString()));
                fr.setArguments(args);
                ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
                ft.addToBackStack(null);
                ft.commit();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

//        Map<String, String> jsonParams = new HashMap<String, String>();
//        jsonParams.put("app_key", "88888");
//        jsonParams.put("category_id", "1");
//        jsonParams.put("is_mobile", "1");


//        StoreCategoryService latestItem = new StoreCategoryService(getActivity());
//        latestItem.serviceStoreCategory(Constants.API_DOMAINTest, Constants.API_StoreSub,adapter,latestStr, Integer.valueOf(catid));
        sessionManager = new SessionManager(getActivity());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API_Retrofit.class);
        StoreSubCatRequest serviceRequest = new StoreSubCatRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory_id(String.valueOf(sessionManager.getcatid()));
//        sessionManager.getcatId(catid);
        serviceRequest.setIs_mobile("1");
        //String.valueOf(catid)
        Call<StoreSubRetrofit> serviceResponseCall =  service.getStoreSubCat(serviceRequest);

        serviceResponseCall.enqueue(new Callback<StoreSubRetrofit>() {
            @Override
            public void onResponse(Call<StoreSubRetrofit> call, retrofit2.Response<StoreSubRetrofit> response) {
                int statusCode = response.code();


                StoreSubRetrofit  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("CAT_ID", String.valueOf(sessionManager.getcatid()));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                Log.d("COUNT_latestStr",String.valueOf(latestStr.size()) );
                latestStr.clear();
                if(serviceResponsee.getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().size()==0){
                    labelNotification.setText("No Item/s for this Category Yet");
                    labelNotification.setVisibility(View.VISIBLE);
                }else {
                    for (int i = 0; i < serviceResponsee.getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().size(); i++) {
                        Log.d("LOOP", String.valueOf(serviceResponsee.getData().get(Integer.valueOf(sessionManager.getcatid())).getSubcategory().size()));
                        Log.d("GETCATIDSESSION", String.valueOf(sessionManager.getcatid()));
                        Log.d("ARRAYITEMS", Arrays.toString(latestStr.toArray()));
                        latestStr.add(serviceResponsee);

                        adapter.notifyDataSetChanged();


                    }
                }
            }

            @Override
            public void onFailure(Call<StoreSubRetrofit> call, Throwable t) {

            }
        });
       // prepareVisitData();

        return x;
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
            StoreSubData newsAndPromosData = new StoreSubData(mImageUrl[i],mProductName[i]);
            storeSubData.add(newsAndPromosData);
        }
        mAdapter.notifyDataSetChanged();
        Glide.with(getActivity())
                .load("http://edmer.x10host.com/bb/apparel.jpeg")
                .centerCrop()
                .into(subcatImg);

    }
    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    public static  class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Store_SubCat_Frag.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Store_SubCat_Frag.ClickListener clickListener) {
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
