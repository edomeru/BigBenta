package ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Frag1;
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
 * Created by Edmer on 24/11/2016.
 */

public class SearchALLActivity extends AppCompatActivity {
    String search="";
    private ArrayList<SearchALLResponse> latestClass = new ArrayList<>();
    private ArrayList<SearchALLResponse> listads = new ArrayList<>();
    private List<StoreItemsData> storeItemsData = new ArrayList<>();
    private StoreItemsAdapter mAdapter;
    private int requestCount = 1;
    private int requestCount2 = 1;
    View x;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_store_all);
//        setHasOptionsMenu(true);
//        bundle = getArguments();
//        if (bundle != null){
//            search = getArguments().getString(Constants.KEY_SEARCH);
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(24,53,68));
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundleText = getIntent().getExtras();
        search = bundleText.getString(Constants.KEY_SEARCH);
        // Log.d("CAT IDITEM", catid);
        resultsforads = (TextView) findViewById(R.id.resultsforads);
        resultsforstore = (TextView) findViewById(R.id.resultsforstore);
        sessionManager = new SessionManager(this);
        adapter4Lclassified= new SearchALLStoreAdapter(latestClass,this);
        adapter4Ads= new SearchALLAdsAdapter(listads,this);
        recyclerView = (RecyclerView)findViewById(R.id.store_search_all_recycler_view);
        recyclerViewAds = (RecyclerView)findViewById(R.id.ads_search_all_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerViewAds.setHasFixedSize(true);
        //int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());
        gaggeredGridLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the com.example.edmeralarte.bigbenta2.Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login) {
            Frag1 fr=new Frag1();
            FragmentManager fm= this.getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            Bundle args = new Bundle();
            args.putString("position", "1");
            fr.setArguments(args);
            ft.addToBackStack(null);
            ft.replace(R.id.containerView, fr);
            ft.commit();

            return true;
        }

//        if (id == R.id.action_chat) {
//            return true;
//        }
//        if (id == R.id.action_cart) {
//            StoreTabFragment fr=new StoreTabFragment();
//            FragmentManager fm= this.getSupportFragmentManager();
//            FragmentTransaction ft=fm.beginTransaction();
//            Bundle args = new Bundle();
//            args.putString("position", "1");
//            fr.setArguments(args);
//            ft.replace(R.id.containerView, fr);
//            ft.commit();
//            return true;
//        }
//
//        if (id == R.id.action_service) {
//            ServicesTabFragment fr=new ServicesTabFragment();
//            FragmentManager fm= this.getSupportFragmentManager();
//            FragmentTransaction ft=fm.beginTransaction();
//            Bundle args = new Bundle();
//            args.putString("position", "1");
//            fr.setArguments(args);
//            ft.replace(R.id.containerView, fr);
//            ft.commit();
//            return true;
//        }

        if (id == R.id.search) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        return true;
    }

    private void getData() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
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
                for(int i=0;i< serviceResponsee.getData().getStores().getStores().size()-1;i++) {

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
                resultsforstore.setText("");
                Toast.makeText(SearchALLActivity.this, "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });
//        requestCount++;
//        Log.d("requestCount",   String.valueOf(requestCount));


    }

    private void getData2() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
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
                resultsforads.setText("");
                Toast.makeText(SearchALLActivity.this, "No More Items Available", Toast.LENGTH_SHORT).show();
            }
        });
//        requestCount++;
//        Log.d("requestCount",   String.valueOf(requestCount));


    }




    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }
}
