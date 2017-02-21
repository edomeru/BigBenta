package ph.dreambig.bigbenta.marketplacev2.AdsSubCat;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LatestClassifiedService {

    public final String TAG = this.getClass().getSimpleName();
    API_Retrofit service;
    Context context;

    ProgressDialog progressDialog;

    public LatestClassifiedService(Context context) {
        this.context = context;
    }

    public void serviceLatestClassified(final String appKey, final String isMobile, final RecyclerView.Adapter adapter, final ArrayList<AdsSubCatRetrofitResponse> latestStr ,String catid) {

//        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,appKey+isMobile,new JSONObject(jsonParams),
//                new Response.Listener<JSONObject>() {
//
//                    LatestCategory   setterGetter;
//
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
//                        String imgclassi = "";
//                        JSONArray strs = null;
//                        try {
//
//                            messege = req.getString("message");
//                            code = req.getString("code");
//                            JSONArray data = req.getJSONArray("data");
//
//                            for (int i = 0; i < data.length(); i++) {
//                                JSONObject cat = data.getJSONObject(i);
//                                String nameAds = cat.getString("name");
//                                int category_id = Integer.valueOf(cat.getString("category_id"));
//                                setterGetter = new LatestCategory(nameAds,category_id);
//
//                                latestStr.add(setterGetter);
//                                Log.d("SIZE OF RECYCLERVIEW", String.valueOf(latestStr.size()));
//
//                                adapter.notifyDataSetChanged();
//                            }
//
//
//
//
//
////
////                            for (int i = 0; i < clasi.length(); i++) {
////
////                                JSONObject json = clasi.getJSONObject(i);
////
////                                JSONArray imge = json.getJSONArray("image");
////
////                                for (int j = 0; j < imge.length(); j++) {
////                                    JSONObject jsonImg = imge.getJSONObject(j);
////
////                                imgclassi =  jsonImg.getString(Constants.IMAGESY);
////
////
////
////
////
////
////                                }
////
////                                JSONArray loc = json.getJSONArray("location");
////
////                                for (int j = 0; j < loc.length(); j++) {
////                                    JSONObject jsonloc = loc.getJSONObject(j);
////
////                                    LatestCategory setterGetter = new LatestCategory(json.getString(Constants.PRICE), json.getString(Constants.PRODUCT_NAME), jsonloc.getString(Constants.API_REGIONNAME) , imgclassi,json.getString(Constants.API_CREATEDDATE));
////                                    Log.d("SGCLASSIFIED", setterGetter.toString());
////                                    latestStr.add(setterGetter);
////                                    Log.d("RECYCLERVIEW CLASSIFIED",  String.valueOf(latestStr.size()));
////
////                                    adapter.notifyDataSetChanged();
////                                    Log.d("ADAPTER CLASSIFIED",  String.valueOf(adapter.getItemCount()));
////
////                                }
//
//
//                           // }
//
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
//
//        AppController.getInstance().addToRequestQueue(jsObjReques);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(appKey)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API_Retrofit.class);
        AdsSubCatRequest serviceRequest = new AdsSubCatRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setIs_mobile("1");
        serviceRequest.setCategory_id(catid);

        Call<AdsSubCatRetrofitResponse> serviceResponseCall =  service.getAdsSubCat(serviceRequest);

        serviceResponseCall.enqueue(new Callback<AdsSubCatRetrofitResponse>() {
            @Override
            public void onResponse(Call<AdsSubCatRetrofitResponse> call, retrofit2.Response<AdsSubCatRetrofitResponse> response) {
                int statusCode = response.code();


                AdsSubCatRetrofitResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));

                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                Log.d("COUNT_latestStr",String.valueOf(latestStr.size()) );
                latestStr.clear();
                for(int i=0;i< serviceResponsee.getData().size();i++) {

                    latestStr.add(serviceResponsee);
                  //  Log.d("IMAGGEEEEEEEEEEE",   serviceResponsee.getData().get(i).get+ serviceResponsee.getData().get(i).getSmGroup()+serviceResponsee.getData().get(i).getSmName()+"."+serviceResponsee.getData().get(i).getSmExtension());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<AdsSubCatRetrofitResponse> call, Throwable t) {

            }
        });
    }





}