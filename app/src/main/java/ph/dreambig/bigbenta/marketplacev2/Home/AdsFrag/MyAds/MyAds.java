package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.MyAds;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag.AdsItemsAdapter;
import ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag.AdsItemsData;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyAds extends Fragment {
    SessionManager sessionManager;
    private List<AdsItemsData> storeItemsData = new ArrayList<>();
    private ArrayList<MyAdsResponse> latestClass = new ArrayList<>();
    private AdsItemsAdapter mAdapter;
    private RecyclerView.Adapter adapter4Lclassified;
    private RecyclerView recyclerView;
    private GridLayoutManager gaggeredGridLayoutManager;
    private boolean loading = false;
    private int requestCount = 1;
    private int page = 0;
    API_Retrofit service;
    View view;
    Bundle bundle;
    String  subcatid;
    private Handler handler;
    private Paginate paginate;
    TextView PesoSign;

    public MyAds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.my_ads2, container, false);
        sessionManager = new SessionManager(getActivity());
        adapter4Lclassified= new MyAdsAdapter(latestClass,getActivity());
        recyclerView = (RecyclerView)view.findViewById(R.id.myads_recycler_view);
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
        MyAdsRequest serviceRequest = new MyAdsRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory("classified");
        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(requestCount));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setUser_id(sessionManager.getUser_id());
        serviceRequest.setToken(sessionManager.getToken());
        serviceRequest.setType("1");
        serviceRequest.setSingle_edit_items("1");
        serviceRequest.setGet_all_status("1");
        sessionManager.setAdsitemcatId(subcatid);
         Log.d("USER_ID", sessionManager.getUser_id());
        Log.d("TOKEN", sessionManager.getToken());
        Call<MyAdsResponse> serviceResponseCall =  service.getMyAds(serviceRequest);

        serviceResponseCall.enqueue(new Callback<MyAdsResponse>() {
            @Override
            public void onResponse(Call<MyAdsResponse> call, retrofit2.Response<MyAdsResponse> response) {
                int statusCode = response.code();


                MyAdsResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                Log.d("COUNT_ITEMDETAIL",String.valueOf(latestClass.size()));
                latestClass.clear();
                for(int i=0;i< serviceResponsee.getData().getClassified().getClassified().size();i++) {
                    Log.d("HELLO MYADS",String.valueOf(serviceResponsee.getData().getClassified().getClassified().size()));
                    latestClass.add(serviceResponsee);


                    adapter4Lclassified.notifyDataSetChanged();
                }

                Log.d("RESPONSEBODYYYYY",response.body().toString());
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MyAdsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("ERROR_PO",t.toString());
//                Toast.makeText(getActivity(), "BWAHAHAHMore Items Available", Toast.LENGTH_SHORT).show();
            }
        });

        requestCount++;
        Log.d("requestCount",   String.valueOf(requestCount));
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
