package ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag;

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

public class ClassifiedItemsService {

    public final String TAG = this.getClass().getSimpleName();

    Context context;

    ProgressDialog progressDialog;

    public ClassifiedItemsService(Context context) {
        this.context = context;
    }

    public void serviceLatestClassified(final String appKey, final String isMobile, final RecyclerView.Adapter adapter, final Map<String, String> jsonParams, final ArrayList<AdsItemModel> latestStr ) {

        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,appKey+isMobile,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {

                    AdsItemModel   setterGetter;

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
                        String  itemId="";
                        String imgclassi = "";
                        JSONArray strs = null;
                        try {

                            messege = req.getString("message");
                            code = req.getString("code");
                            JSONObject data = req.getJSONObject("data");
                            JSONObject classifie = data.getJSONObject("classified");

                            //classified objects
                            JSONArray  clasi = classifie.getJSONArray("classified");
                            cod = classifie.getString("code");



                            for (int i = 0; i < clasi.length(); i++) {

                                JSONObject json = clasi.getJSONObject(i);
                               itemId = json.getString("id");
                                JSONArray imge = json.getJSONArray("image");

                                for (int j = 0; j < imge.length(); j++) {
                                    JSONObject jsonImg = imge.getJSONObject(j);

                                    imgclassi = jsonImg.getString(Constants.IMAGESY);


                                }

                                JSONArray loc = json.getJSONArray("location");

                                for (int j = 0; j < loc.length(); j++) {
                                    JSONObject jsonloc = loc.getJSONObject(j);

                                    AdsItemModel setterGetter = new AdsItemModel(json.getString(Constants.PRICE), json.getString(Constants.PRODUCT_NAME), jsonloc.getString(Constants.API_REGIONNAME), imgclassi, json.getString(Constants.API_CREATEDDATE),itemId);
                                    Log.d("SGCLASSIFIED", setterGetter.toString());
                                    latestStr.add(setterGetter);
                                    Log.d("RECYCLERVIEW CLASSIFIED", String.valueOf(latestStr.size()));

                                    adapter.notifyDataSetChanged();
                                    Log.d("ADAPTER CLASSIFIED", String.valueOf(adapter.getItemCount()));

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