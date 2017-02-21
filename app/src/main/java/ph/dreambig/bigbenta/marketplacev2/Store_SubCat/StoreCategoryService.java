package ph.dreambig.bigbenta.marketplacev2.Store_SubCat;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;


import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edmeralarte on 03/10/2016.
 */
public class StoreCategoryService {public final String TAG = this.getClass().getSimpleName();
    API_Retrofit service;
    Context context;
    SessionManager sessionManager;
    ProgressDialog progressDialog;

    public StoreCategoryService(Context context) {
        this.context = context;
    }

    public void serviceStoreCategory(final String appKey, final String isMobile, final RecyclerView.Adapter adapter, final ArrayList<StoreSubRetrofit> latestStr, final int catId ) {

//        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,appKey+isMobile,new JSONObject(jsonParams),
//                new Response.Listener<JSONObject>() {
//                    LatestStoreItems   setterGetter ;
//                    @Override
//                    public void onResponse(JSONObject req) {
//                        System.out.println(req);
//                        String messege = "";
//                        String code = "";
//                        String pname= "";
//                        String clas = "";
//                        String cod= "";
//                        String name="";
//                        String cdoo= "";
//                        String imgs="";
//                        JSONArray strs = null;
//
//                        try {
//
//                            messege = req.getString("message");
//                            code = req.getString("code");
//                            JSONArray data = req.getJSONArray("data");
//                            JSONObject cat = data.getJSONObject(catId-1);
//                            JSONArray subcat  = cat.getJSONArray("subcategory");
//                            for (int i = 0; i < subcat.length(); i++) {
//
//                                JSONObject subcateg = subcat.getJSONObject(i);
//                               String namesubcat =  subcateg.getString("name");
//                                int category_id = Integer.valueOf(subcateg.getString("category_id")) ;
//                                setterGetter = new LatestStoreItems(namesubcat,category_id);
//
//                                latestStr.add(setterGetter);
//                                Log.d("setterGetter",  setterGetter.toString());
//                                    Log.d("SIZE OF RECYCLERVIEW", String.valueOf(latestStr.size()));
//
//                                adapter.notifyDataSetChanged();
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//
//                        Log.d("SIZE OF ADAPTER 2",  String.valueOf(adapter.getItemCount()));
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //hideProgressDialog();
//                    }
//                });
//
//        AppController.getInstance().addToRequestQueue(jsObjReques);

        sessionManager = new SessionManager(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(appKey)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API_Retrofit.class);
        StoreSubCatRequest serviceRequest = new StoreSubCatRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory_id(String.valueOf(catId));
        serviceRequest.setIs_mobile("1");
        Call<StoreSubRetrofit> serviceResponseCall =  service.getStoreSubCat(serviceRequest);

        serviceResponseCall.enqueue(new Callback<StoreSubRetrofit>() {
            @Override
            public void onResponse(Call<StoreSubRetrofit> call, retrofit2.Response<StoreSubRetrofit> response) {
                int statusCode = response.code();


                StoreSubRetrofit  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));
                Log.d("CAT_ID", String.valueOf(catId));
                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());

                for(int i=0;i< serviceResponsee.getData().get(catId).getSubcategory().size();i++) {
                    Log.d("LOOP",  String.valueOf(serviceResponsee.getData().get(catId).getSubcategory().size()));
                    Log.d("GETCATIDSESSION",  String.valueOf(sessionManager.getcatid()));
                    Log.d("ARRAYITEMS", Arrays.toString(latestStr.toArray()));
                    latestStr.add(serviceResponsee);
                        adapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onFailure(Call<StoreSubRetrofit> call, Throwable t) {

            }
        });



    }


}