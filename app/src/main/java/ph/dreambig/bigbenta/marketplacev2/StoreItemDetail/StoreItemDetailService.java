package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreItemDetailService {

    public final String TAG = this.getClass().getSimpleName();

    Context context;

    ProgressDialog progressDialog;
    StoreItemDetailModel setterGetter;
    public StoreItemDetailService(Context context) {
        this.context = context;
    }

    public void serviceLatestClassified(final String appKey, final String isMobile, final String item_id, final  ArrayList<StoreItemDetailModel> latestStr, final Context contextpo) {
       StoreItemDetailModel.setItem_id(item_id);
        Map<String, String> paramscategories = new HashMap<String, String>();
        paramscategories.put("app_key", "88888");
        paramscategories.put("category", "stores");
        paramscategories.put("is_mobile", "1");
        paramscategories.put("subcategory", "1");
        paramscategories.put("item_id", item_id);
        Log.d("PARAMCAT_SERVICE", paramscategories.toString());
        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,appKey+isMobile,new JSONObject(paramscategories),
                new Response.Listener<JSONObject>() {



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
                            Log.d("REQ_SERVICE", req.toString());

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

                                    Log.d("JSON_IMG", jsonImg.getString("image_md"));
                                       setterGetter = new StoreItemDetailModel(json.getString(Constants.PRICE), json.getString(Constants.PRODUCT_NAME),
                                            json.getString(Constants.STORE_NAME), jsonImg.getString("image_md"),json.getString("id"),json.getString("long_description"));

                                    latestStr.add(setterGetter);

                                    StoreItemDetail.product_name.setText(json.getString(Constants.PRODUCT_NAME));
                                    StoreItemDetail.textView.setText(json.getString(Constants.PRICE));
                                    StoreItemDetail.store_name.setText(json.getString(Constants.STORE_NAME));
                                    StoreItemDetail.product_info.setText(Html.fromHtml(json.getString("long_description")));
                                    StoreItemDetail.min_order.setText(json.getString("minimum_order"));
                                    StoreItemDetail.available.setText(json.getString("quantity"));
                                    HashMap<String, String> url_maps = new HashMap< >();
                                 ;
                                    StoreItemDetail.pic =   jsonImg.getString("image_md");
                                        url_maps.put("one", jsonImg.getString("image_md"));

                                    Log.d("HASH", latestStr.get(j).getImagef());

                                    for(String namei : url_maps.keySet()){
                                        DefaultSliderView defaultSliderView = new DefaultSliderView(contextpo);
                                        // initialize a SliderLayout
                                        defaultSliderView
                                                .description(namei)
                                                .image(url_maps.get(namei))
                                                .setScaleType(BaseSliderView.ScaleType.Fit);

                                        //add your extra information
                                        defaultSliderView.bundle(new Bundle());
                                        defaultSliderView.getBundle()
                                                .putString("extra",namei);


                                       StoreItemDetail. mProductImg.addSlider(defaultSliderView);

                                    }
                                    StoreItemDetail. mProductImg.startAutoCycle();

                                    StoreItemDetail.mProductImg.setCustomAnimation(new DescriptionAnimation());
                                 //   StoreItemDetail.mProductImg.setCustomIndicator((PagerIndicator) x.findViewById(R.id.custom_indicator));
                                    StoreItemDetail.mProductImg.setDuration(9000);





                                }



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }



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