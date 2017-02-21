package ph.dreambig.bigbenta.marketplacev2.StoreItems;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class StoreItemsService {

    public final String TAG = this.getClass().getSimpleName();

    Context context;

    ProgressDialog progressDialog;

    public StoreItemsService(Context context) {
        this.context = context;
    }

    public void serviceLatestClassified(final String appKey, final String isMobile, final RecyclerView.Adapter adapter, final Map<String, String> jsonParams, final ArrayList<StoreItemModel> latestStr ) {

        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,appKey+isMobile,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {

                    StoreItemModel   setterGetter;

                    @Override
                    public void onResponse(JSONObject req) {
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
                        JSONArray strs = null;
                        try {

                            messege = req.getString("message");
                            code = req.getString("code");
                            JSONObject data = req.getJSONObject("data");
//                            JSONObject classifie = data.getJSONObject("classified");
//
//                            //classified objects
//                            clas = classifie.getString("classified");
//                            cod = classifie.getString("code");

                            JSONObject stores = data.getJSONObject("stores");
                            cdoo = stores.getString("code");



                            strs = stores.getJSONArray("stores");

                            for (int i = 0; i < strs.length(); i++) {

                                JSONObject json = strs.getJSONObject(i);

                                //  setterGetter = new LatestStoreItems(json.getInt(Constants.PRICE), json.getString(Constants.PRODUCT_NAME), json.getString(Constants.STORE_NAME), json.getString(Constants.IMAGE));




                                JSONArray imge = json.getJSONArray("image");

                                for (int j = 0; j < imge.length(); j++) {
                                    JSONObject jsonImg = imge.getJSONObject(j);


                                    StoreItemModel   setterGetter = new StoreItemModel(json.getString(Constants.PRICE), json.getString(Constants.PRODUCT_NAME),
                                            json.getString(Constants.STORE_NAME), jsonImg.getString(Constants.IMAGESY),json.getString("id"));
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




                                    latestStr.add(setterGetter);
                                    Log.d("SIZE OF RECYCLERVIEW", String.valueOf(latestStr.size()));

                                    adapter.notifyDataSetChanged();
                                    Log.d("SIZE OF ADAPTER", String.valueOf(adapter.getItemCount()));

                                }



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                        Log.d("SIZE OF ADAPTER 2",  String.valueOf(adapter.getItemCount()));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //hideProgressDialog();
                    }
                });

        AppController.getInstance().addToRequestQueue(jsObjReques);

    }
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
}