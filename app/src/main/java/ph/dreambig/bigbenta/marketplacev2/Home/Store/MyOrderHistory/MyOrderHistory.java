package ph.dreambig.bigbenta.marketplacev2.Home.Store.MyOrderHistory;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.RealmResults;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.FavoritesCartData;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.RealmFolder.RealmDataAdapter;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.LogInTabFragment;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Frag1;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.StoreItems.StoreItemsAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyOrderHistory extends Fragment {
        View x;
     List<OrderHistResponse> latestClass;
    private StoreItemsAdapter mAdapter;
    private int requestCount = 1;
     ProgressBar progressBar;
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
    public MyOrderHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        latestClass = new ArrayList<>();
        sessionManager = new SessionManager(getActivity());
         x = inflater.inflate(R.layout.my_order_history, container, false);

     progressBar = (ProgressBar) x.findViewById(R.id.progressBar1);
        if(!sessionManager.isLoggedIn()) {
            progressBar.setVisibility(View.GONE);
        }
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(),
                "Bariol_Regular.otf");
        labelNotification = (TextView)x.findViewById(R.id.labelNotification);
        labelNotification.setTypeface(bariol);
        sessionManager = new SessionManager(getActivity());
        adapter4Lclassified= new OrderHistAdapter(latestClass,getActivity());
        recyclerView = (RecyclerView)x.findViewById(R.id.storeitems_recycler_view);
        recyclerView.setHasFixedSize(true);
        //int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());
        gaggeredGridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        Log.d("LOGINORNOT?", String.valueOf(sessionManager.isLoggedIn()));
        if(sessionManager.isLoggedIn()) {
            getData();

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

        }else{

            labelNotification.setText("Pls Login To View Your Order History");
            labelNotification.setVisibility(View.VISIBLE);
            labelNotification.setTextColor(Color.BLUE);
            labelNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("You Are NOT Logged In yet" )
                            .setContentText("Please Log in To Proceed")
                            .setConfirmText("YES")
                            .setCancelText("NO")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {

                                    Frag1 fr=new Frag1();
                                    FragmentManager fm= getActivity().getSupportFragmentManager();
                                    FragmentTransaction ft=fm.beginTransaction();
                                    Bundle args = new Bundle();
                                    args.putString("position", "1");
                                    args.putString("from", "MainActivity");
                                    fr.setArguments(args);
                                    ft.addToBackStack(null);
                                    ft.replace(R.id.containerView, fr);
                                    ft.commit();
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
            });
        }

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
////Ifscrolled at last then
//                if (isLastItemDisplaying(recyclerView)) {
////Calling the method getdata again
//                    getData();
//                }
//            }
//        });


//        recyclerView.setAdapter(adapter4Lclassified);


//        requestCount++;
//        Log.d("requestCount",   String.valueOf(requestCount));




        return x;
    }

//    public void showInfo(int pos){
//        LayoutInflater layoutInfralte=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LinearLayout linearLayout=(LinearLayout)x.findViewById(R.id.infolayout);
//        List views=new ArrayList();
//
//        Iterator<OrderHistResponse> ite = NavigationContext.getInstance().getArray().iterator();
//        OrderHistResponse temp;
//        while(ite.hasNext()){
//            temp=ite.next();
//            View view=layoutInfralte.inflate(R.layout.infoitem, null);
//            // Edit
//            TextView tv=(TextView)view.findViewById(R.id.TextAddress);
//            tv.setText(temp.getData().get(pos).getItems().get(0).getName().toString());
//            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            views.add(view);
//        }
//        for(int i = 0; i<views.size(); i++)
//            linearLayout.addView((View) views.get(i));
//    }

    private void getData() {


        //Adding the method to the queue by calling the method getDataFromServer
        //  AppController.getInstance().addToRequestQueue(getDataFromServer(requestCount));
        //Incrementing the request counter

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        progressBar.setVisibility(View.VISIBLE);
        service = retrofit.create(API_Retrofit.class);
        OrderHistRequest serviceRequest = new OrderHistRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setUser_id(sessionManager.getUser_id().toString());
        serviceRequest.setIs_mobile("1");
        serviceRequest.setIs_primary("1");
        serviceRequest.setLimit("12");
        serviceRequest.setOffset(String.valueOf(requestCount));
        serviceRequest.setSort_by("date_created");
        serviceRequest.setOrder("desc");
        serviceRequest.setToken(sessionManager.getToken().toString());

//        sessionManager.setSubcatId(catid);
//        Log.d("CAT ID111111", catid);


        Call<OrderHistResponse> serviceResponseCall =  service.getOrderHist(serviceRequest);

        serviceResponseCall.enqueue(new Callback<OrderHistResponse>() {
            @Override
            public void onResponse(Call<OrderHistResponse> call, retrofit2.Response<OrderHistResponse> response) {
                int statusCode = response.code();


                OrderHistResponse  serviceResponsee = response.body();

//                Log.d("STATUS CODE", String.valueOf(statusCode));
//                Log.d("RESPONSE_BODY",  response.body().toString());
//                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
//                Log.d("COUNT_ITEMDETAIL",String.valueOf(latestClass.size()) );
                latestClass.clear();
                for(int i=0;i< serviceResponsee.getData().size();i++) {

                    latestClass.add(serviceResponsee);
//                    LayoutInflater layoutInfralte=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    LinearLayout linearLayout=(LinearLayout)x.findViewById(R.id.infolayout);
//                    List views=new ArrayList();
//
//                    View view=layoutInfralte.inflate(R.layout.infoitem, null);
//                    // Edit
//                    TextView tv=(TextView)view.findViewById(R.id.TextAddress);
//                    tv.setText(serviceResponsee.getData().get(14).getItems().get(i).getName().toString());
//                    view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    views.add(view);
//
//
//                    //  linearLayout.addView((View) views.get(i));
//                    for(int j = 0; j<views.size(); j++){
//                        linearLayout.addView((View) views.get(j));
//                   }



                    if (i==serviceResponsee.getData().size() -1) {
                        requestCount++;
                    }
                     adapter4Lclassified.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<OrderHistResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                labelNotification.setText("No Order History yet");
                labelNotification.setVisibility(View.VISIBLE);
                //Toast.makeText(getActivity(), "No More Items Available", Toast.LENGTH_SHORT).show();
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
