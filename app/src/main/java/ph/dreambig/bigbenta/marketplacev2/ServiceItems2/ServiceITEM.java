package ph.dreambig.bigbenta.marketplacev2.ServiceItems2;

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

/**
 * Created by edmeralarte on 03/10/2016.
 */
public class ServiceITEM {public final String TAG = this.getClass().getSimpleName();
    API_Retrofit service;
    Context context;

    ProgressDialog progressDialog;

    public ServiceITEM(Context context) {
        this.context = context;
    }

    public void serviceStoreCategory(final String appKey, final String isMobile, final RecyclerView.Adapter adapter, final String criteria_id, final ArrayList<ServiceResponse> latestStr ) {

//        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,appKey+isMobile,new JSONObject(jsonParams),
//                new Response.Listener<JSONObject>() {
//                    ServiceItem2Model   setterGetter ;
//                    @Override
//                    public void onResponse(JSONObject req) {
//                        JSONObject json = null;
//                        System.out.println("REQ"+req);
//
//                        String rrate="";
//                        String code = "";
//                        String srvc_id= "";
//                        String sm_name = "";
//                        String cod= "";
//                        String sm_extension="";
//                        String sm_domain= "";
//                        String sm_group="";
//                        String last_name="";
//                        String first_name="";
//                        String business_name = "";
//                        String cat_item_name ="";
//                        String cat_item_mask="";
//                        JSONArray strs = null;
//
//                        try {
//
//                            code = req.getString("code");
//                            JSONArray data = req.getJSONArray("data");
//                            Log.d("JARRAY", data.toString());
//                            for (int i = 0; i < data.length(); i++) {
//                                json =  data.getJSONObject(i);
//
//                                srvc_id  = json.getString("srvc_id");
//                                business_name  = json.getString("business_name");
//                                cat_item_mask  = json.getString("cat_item_mask");  //masus
//                                cat_item_name  = json.getString("cat_item_name");  //full body massage
//                                rrate = json.getString("rate");//PRICE
//
//                                sm_domain    = json.getString("sm_domain");
//                                sm_group      = json.getString("sm_group");
//                                sm_name      = json.getString("sm_name");
//                                sm_extension      = json.getString("sm_extension");
//
//                               // String imgUrl =  getImageUrl(sm_domain,sm_group,sm_name,sm_extension);
//                                Log.d("imgUrl", sm_domain+sm_group+sm_name+"."+sm_extension);
//                                Log.d("IKOT", Integer.toString(i));
//                              setterGetter = new ServiceItem2Model(sm_domain+sm_group+sm_name+"."+sm_extension, business_name,rrate, cat_item_mask,cat_item_name,srvc_id);
//                                Log.d("setterGetter",  setterGetter.toString());
//                                latestStr.add(setterGetter);
//
//
//                                adapter.notifyDataSetChanged();
//                            }
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
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
//
//    }
//    private void parseData(JSONObject req) {
//        String messege = "";
//        String code = "";
//        String pname = "";
//        String clas = "";
//        String cod = "";
//        String name = "";
//        String cdoo = "";
//        String imgs = "";
//        JSONArray strs = null;
//
//    }




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(appKey)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API_Retrofit.class);
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setCriteria_id(criteria_id);
        serviceRequest.setFilter("main");

     Call<ServiceResponse> serviceResponseCall =  service.getSubCat(serviceRequest);

        serviceResponseCall.enqueue(new Callback<ServiceResponse>() {
            @Override
            public void onResponse(Call<ServiceResponse> call, retrofit2.Response<ServiceResponse> response) {
                int statusCode = response.code();


                ServiceResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));

                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                Log.d("COUNT_latestStr",String.valueOf(latestStr.size()) );
                latestStr.clear();
              for(int i=0;i< serviceResponsee.getData().size();i++) {

                  latestStr.add(serviceResponsee);
                  Log.d("IMAGGEEEEEEEEEEE",   serviceResponsee.getData().get(i).getSmDomain()+ serviceResponsee.getData().get(i).getSmGroup()+serviceResponsee.getData().get(i).getSmName()+"."+serviceResponsee.getData().get(i).getSmExtension());
                  adapter.notifyDataSetChanged();
              }

            }

            @Override
            public void onFailure(Call<ServiceResponse> call, Throwable t) {

            }
        });

    }

    public String  getImageUrl(String Domain, String sm_group, String name, String ext ){


        return  Domain+sm_group+name+"."+ext;
    }
//    public void display(Datum datum){
//if(datum!= null)
//        Log.d("BISNESS NAME ", datum.getBusinessName());
//        else
//    Log.d("NULL ", null);
//
//    }

}