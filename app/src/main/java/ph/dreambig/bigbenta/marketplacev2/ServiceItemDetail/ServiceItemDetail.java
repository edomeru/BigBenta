package ph.dreambig.bigbenta.marketplacev2.ServiceItemDetail;


import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiceItemDetail extends Fragment {
    View  x;
    String rrate="";
    int code ;
    String srvc_id= "";
    String sm_name = "";
    String cod= "";
    String sm_extension="";
    String sm_domain= "";
    String sm_group="";
    String subcatitem_name="";
    String subcat_name="";
    String business_name = "";
    String last_name ="",cat_name;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    TextView bisname,rateprice,HS;
    private List<RelatedData> CardInfoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RelatedAdapter mAdapter;
    Button RateMe,bookNow;
    String namee ,  srvce_id ,price;
    private View  rate;
    private boolean rateIsVisible = false;
    public ServiceItemDetail() {
        // Required empty public constructor
    }
ImageView share;
    TextView sked,datesched,textView,call_rate,ratings,ratelabel,name,subcat_namee,subcatitem_namee,product_price,hour_rate,hday_rate,fday_rate,esurcharge,otsurcharge;
    TextView hour_rateValue,hday_rateValue,fday_rateValue,esurchargeValue,otsurchargeValue,call_rateValue;
    TextView SEcall_rate,SE,SEesurcharge,SEfday_rate,SEhday_rate,SEhour_rate,SEotsurcharge,SEhour_rateValue,SEhday_rateValue,SEfday_rateValue,SEesurchargeValue,SEotsurchargeValue,SEcall_rateValue;
    SliderLayout mProductImg;
CircleImageView circleImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       x =   inflater.inflate(R.layout.fragment_serviceitemdetail, container, false);

        share=(ImageView)x.findViewById(R.id.share);
        sked=(TextView)x.findViewById(R.id.sked);
        datesched=(TextView)x.findViewById(R.id.datesched);
        ratings=(TextView)x.findViewById(R.id.ratings);
        call_rateValue=(TextView)x.findViewById(R.id.call_rateValue);
        otsurchargeValue=(TextView)x.findViewById(R.id.otsurchargeValue);
        esurchargeValue=(TextView)x.findViewById(R.id.esurchargeValue);
        fday_rateValue=(TextView)x.findViewById(R.id.fday_rateValue);
        hday_rateValue=(TextView)x.findViewById(R.id.hday_rateValue);
        hour_rateValue=(TextView)x.findViewById(R.id.hour_rateValue);
        otsurcharge=(TextView)x.findViewById(R.id.otsurcharge);
        hour_rate=(TextView)x.findViewById(R.id.hour_rate);
        hday_rate=(TextView)x.findViewById(R.id.hday_rate);
        fday_rate=(TextView)x.findViewById(R.id.fday_rate);
        esurcharge=(TextView)x.findViewById(R.id.esurcharge);

        SEcall_rate=(TextView)x.findViewById(R.id.SEcall_rate);
        SEcall_rateValue=(TextView)x.findViewById(R.id.SEcall_rateValue);
        SEotsurchargeValue=(TextView)x.findViewById(R.id.SEotsurchargeValue);
        SEesurchargeValue=(TextView)x.findViewById(R.id.SEesurchargeValue);
        SEfday_rateValue=(TextView)x.findViewById(R.id.SEfday_rateValue);
        SEhday_rateValue=(TextView)x.findViewById(R.id.SEhday_rateValue);
        SEhour_rateValue=(TextView)x.findViewById(R.id.SEhour_rateValue);
        SEotsurcharge=(TextView)x.findViewById(R.id.SEotsurcharge);
        SEhour_rate=(TextView)x.findViewById(R.id.SEhour_rate);
        SEhday_rate=(TextView)x.findViewById(R.id.SEhday_rate);
        SEfday_rate=(TextView)x.findViewById(R.id.SEfday_rate);
        SEesurcharge=(TextView)x.findViewById(R.id.SEesurcharge);

        SE=(TextView)x.findViewById(R.id.SE);
        HS=(TextView)x.findViewById(R.id.HS);
        product_price=(TextView)x.findViewById(R.id.product_price);
        rate  = x.findViewById(R.id.rate);
        srvce_id = getArguments().getString("srvce_id");
        cat_name = getArguments().getString("cat_name");

        ((MainActivity) getActivity()).setTextName(cat_name);

        rateprice=(TextView)x.findViewById(R.id.rateprice);
        recyclerView = (RecyclerView) x.findViewById(R.id.recycler_view_related);
        textView = (TextView) x.findViewById(R.id.product_price);
        name = (TextView) x.findViewById(R.id.name);
        ratelabel= (TextView) x.findViewById(R.id.ratelabel);
        bisname = (TextView) x.findViewById(R.id.bisname);
        subcatitem_namee = (TextView) x.findViewById(R.id.subcatitem_name);
        subcat_namee = (TextView) x.findViewById(R.id.subcat_name);
        call_rate= (TextView) x.findViewById(R.id.call_rate);

        RateMe= (Button)x.findViewById(R.id.RateMe);
        bookNow= (Button)x.findViewById(R.id.bookNow);

       // available= (TextView) x.findViewById(R.id.isAvailable);
       // min_order= (TextView)x. findViewById(R.id.minOrder);
        circleImage = (CircleImageView) x.findViewById(R.id.profile_image);
        rate.setVisibility(View.GONE);
        Typeface Abold = Typeface.createFromAsset(getContext().getAssets(), "Bariol_Regular.otf");
        product_price.setTypeface(Abold);
        sked.setTypeface(Abold);
        datesched.setTypeface(Abold);
        call_rateValue.setTypeface(Abold);
        otsurchargeValue.setTypeface(Abold);
        esurchargeValue.setTypeface(Abold);
        fday_rateValue.setTypeface(Abold);
        hday_rateValue.setTypeface(Abold);
        hour_rateValue.setTypeface(Abold);
        otsurcharge.setTypeface(Abold);
        hour_rate.setTypeface(Abold);
        hday_rate.setTypeface(Abold);
        fday_rate.setTypeface(Abold);
        esurcharge.setTypeface(Abold);
        SEcall_rate.setTypeface(Abold);
        SEcall_rateValue.setTypeface(Abold);
        SEotsurchargeValue.setTypeface(Abold);
        SEesurchargeValue.setTypeface(Abold);
        SEfday_rateValue.setTypeface(Abold);
        SEhday_rateValue.setTypeface(Abold);
        SEhour_rateValue.setTypeface(Abold);
        SEhday_rate.setTypeface(Abold);
        SEfday_rate.setTypeface(Abold);
        SEesurcharge.setTypeface(Abold);
        SE.setTypeface(Abold);
        HS.setTypeface(Abold);
        product_price.setTypeface(Abold);
        rateprice.setTypeface(Abold);
        textView.setTypeface(Abold);
        name.setTypeface(Abold);
        ratings.setTypeface(Abold);
        ratelabel.setTypeface(Abold);
        bisname.setTypeface(Abold);
        subcatitem_namee.setTypeface(Abold);
        subcat_namee.setTypeface(Abold);
        call_rate.setTypeface(Abold);
        RateMe.setTypeface(Abold);
        bookNow.setTypeface(Abold);
       // available.setTypeface(Abold);
       // min_order.setTypeface(Abold);


        /////////////////START

        Log.d("SERVICE_ID", srvce_id);
        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.GET, Constants.API_SERVICETESTDOMAIN+Constants.API_SINGLE_VIEW+srvce_id,
                new Response.Listener<JSONObject>() {



                    @Override
                    public void onResponse(JSONObject req) {
                        System.out.println(req);


                        int ratei;
                        String rate_name="";
                        String first_name="";
                        JSONArray strs = null;
                        JSONObject json= null;

                        try {
                            Log.d("REQ_SERVICE", req.toString());


                            code = req.getInt("code");
                            JSONObject jsonk = req.getJSONObject("data");

                            Log.d("JARRAY", jsonk.toString());
//                            for (int a = 0; a < data.length(); a++) {
                               // json = data.getJSONObject(a);

                                srvc_id = jsonk.getString("srvc_id");
                                business_name = jsonk.getString("business_name");
                                subcatitem_name = jsonk.getString("subcatitem_name");  //masus
                                subcat_name = jsonk.getString("subcat_name");  //full body massage
                            Log.d("subcatitem_name", subcatitem_name+subcat_name);
                                rrate = jsonk.getString("rate");//PRICE
                                first_name = jsonk.getString("first_name");
                                last_name = jsonk.getString("last_name");
                            rate_name  =jsonk.getString("rate_name");
                            ratei   = jsonk.getInt("rate");
                            JSONArray rates_list = jsonk.getJSONArray("rates_list");
                            JSONArray schedule_list = jsonk.optJSONArray("schedule");


                                sm_domain = jsonk.getString("sm_domain");
                                sm_group = jsonk.getString("sm_group");
                                sm_name = jsonk.getString("sm_name");
                                sm_extension = jsonk.getString("sm_extension");
if(schedule_list!=null) {
    String[] r = new String[schedule_list.length()];
    for (int s = 0; s < schedule_list.length(); s++) {
        JSONObject k = schedule_list.getJSONObject(s);
        k.optString("from");
        k.optString("to");
        k.optString("timefrom");
        k.optString("timeto");

        r[s] = k.optString("from") + " " + "-" + " " + k.optString("to") + " " + k.optString("timefrom") + " " + "-" + " " + k.optString("timeto") + "\n";
        String v = Arrays.toString(r);
        v = v.substring(1, v.length() - 1);
        v = v.replaceAll(",", "");
        datesched.setText(v);
        datesched.setVisibility(View.VISIBLE);
    }
}else{
    sked.setVisibility(View.GONE);
}
                            for (int i = 0; i < rates_list.length(); i++) {
                                JSONObject e = rates_list.getJSONObject(i);

                                if(e.optString("type").equals("SE")){
                                   // e.optInt("call_rate");
                                   // e.optInt("hour_rate");
                                   // e.optInt("hday_rate");
//                                    e.optInt("fday_rate");
//                                    e.optInt("esurcharge");
//                                    e.optInt("otsurcharge");
                                    HS.setVisibility(View.VISIBLE);

//                                    otsurcharge.setVisibility(View.VISIBLE);
//                                    hour_rate.setVisibility(View.VISIBLE);
//                                    hday_rate.setVisibility(View.VISIBLE);
//                                    fday_rate.setVisibility(View.VISIBLE);
//                                    esurcharge.setVisibility(View.VISIBLE);
//
//
//                                    otsurchargeValue.setVisibility(View.VISIBLE);
//                                    hour_rateValue.setVisibility(View.VISIBLE);
//                                    hday_rateValue.setVisibility(View.VISIBLE);
//                                    fday_rateValue.setVisibility(View.VISIBLE);
//                                    esurchargeValue.setVisibility(View.VISIBLE);

                                    if( e.optInt("call_rate")!=0){
                                        call_rateValue.setVisibility(View.VISIBLE);
                                        call_rate.setVisibility(View.VISIBLE);
                                        call_rateValue.setText("₱"+String.valueOf(e.optInt("call_rate")));
                                    }else{
                                        call_rateValue.setVisibility(View.GONE);
                                    }



                                    if( e.optInt("call_rate")!=0){
                                        call_rate.setVisibility(View.VISIBLE);
                                        call_rateValue.setText("₱"+String.valueOf(e.optInt("call_rate")));
                                        call_rateValue.setVisibility(View.VISIBLE);
                                    }else{
                                        call_rateValue.setVisibility(View.GONE);
                                    }

                                    if( e.optInt("otsurcharge")!=0){
                                        otsurcharge.setVisibility(View.VISIBLE);
                                        otsurchargeValue.setText("₱"+String.valueOf(e.optInt("otsurcharge")));
                                        otsurchargeValue.setVisibility(View.VISIBLE);
                                    }else{
                                        otsurchargeValue.setVisibility(View.GONE);
                                    }


                                    if( e.optInt("esurcharge")!=0){
                                        esurcharge.setVisibility(View.VISIBLE);
                                        esurchargeValue.setText("₱"+String.valueOf(e.optInt("esurcharge")));
                                        esurchargeValue.setVisibility(View.VISIBLE);
                                    }else{
                                        esurchargeValue.setVisibility(View.GONE);
                                    }

                                    if( e.optInt("hour_rate")!=0){
                                        hour_rate.setVisibility(View.VISIBLE);
                                        hour_rateValue.setText("₱"+String.valueOf(e.optInt("hour_rate")));
                                        hour_rateValue.setVisibility(View.VISIBLE);
                                    }else{
                                        hour_rateValue.setVisibility(View.GONE);
                                    }

                                    if( e.optInt("hday_rate")!=0){
                                        hday_rate.setVisibility(View.VISIBLE);
                                        hday_rateValue.setText("₱"+String.valueOf(e.optInt("hday_rate")));
                                        hday_rateValue.setVisibility(View.VISIBLE);
                                    }else{
                                        hday_rateValue.setVisibility(View.GONE);
                                    }

                                    if( e.optInt("fday_rate")!=0){
                                        fday_rate.setVisibility(View.VISIBLE);
                                        fday_rateValue.setText("₱"+String.valueOf(e.optInt("fday_rate")));
                                        fday_rateValue.setVisibility(View.VISIBLE);
                                    }else{
                                        fday_rateValue.setVisibility(View.GONE);
                                    }
                                }

                                if(e.optString("type").equals("HS")){
                                    // e.optInt("call_rate");
                                    // e.optInt("hour_rate");
                                    // e.optInt("hday_rate");
//                                    e.optInt("fday_rate");
//                                    e.optInt("esurcharge");
//                                    e.optInt("otsurcharge");
                                    SE.setVisibility(View.VISIBLE);



                                    if( e.optInt("call_rate")!=0){
                                        SEcall_rate.setVisibility(View.VISIBLE);
                                        SEcall_rateValue.setText("₱"+String.valueOf(e.optInt("call_rate")));
                                        SEcall_rateValue.setVisibility(View.VISIBLE);
                                    }else{
                                        SEcall_rateValue.setVisibility(View.GONE);
                                    }

                                    if( e.optInt("otsurcharge")!=0){
                                        SEotsurcharge.setVisibility(View.VISIBLE);
                                        SEotsurchargeValue.setText("₱"+String.valueOf(e.optInt("otsurcharge")));
                                        SEotsurchargeValue.setVisibility(View.VISIBLE);
                                    }else{
                                        SEotsurchargeValue.setVisibility(View.GONE);
                                    }


                                    if( e.optInt("esurcharge")!=0){
                                        SEesurcharge.setVisibility(View.VISIBLE);
                                        SEesurchargeValue.setText("₱"+String.valueOf(e.optInt("esurcharge")));
                                        SEesurchargeValue.setVisibility(View.VISIBLE);
                                    }else{
                                        SEesurchargeValue.setVisibility(View.GONE);
                                    }

                                    if( e.optInt("hour_rate")!=0){
                                        SEhour_rate.setVisibility(View.VISIBLE);
                                        SEhour_rateValue.setText("₱"+String.valueOf(e.optInt("hour_rate")));
                                        SEhour_rateValue.setVisibility(View.VISIBLE);
                                    }else{
                                        SEhour_rateValue.setVisibility(View.GONE);
                                    }

                                    if( e.optInt("hday_rate")!=0){
                                        SEhday_rate.setVisibility(View.VISIBLE);
                                        SEhday_rateValue.setText("₱"+String.valueOf(e.optInt("hday_rate")));
                                        SEhday_rateValue.setVisibility(View.VISIBLE);
                                    }else{
                                        SEhday_rateValue.setVisibility(View.GONE);
                                    }

                                    if( e.optInt("fday_rate")!=0){
                                        SEfday_rate.setVisibility(View.VISIBLE);
                                        SEfday_rateValue.setText("₱"+String.valueOf(e.optInt("fday_rate")));
                                        SEfday_rateValue.setVisibility(View.VISIBLE);
                                    }else{
                                        SEfday_rateValue.setVisibility(View.GONE);
                                    }


                                }


                            }

                            name.setText(first_name+" "+last_name);
                            product_price.setText(String.valueOf(ratei));
                            rateprice.setText(rate_name);
                                bisname.setText(business_name);
                                subcatitem_namee.setText(subcatitem_name);
                                subcat_namee.setText(subcat_name);
                                Log.d("imgUrl", sm_domain + sm_group + sm_name + "." + sm_extension);
                               // Log.d("IKOT", Integer.toString(a));

                                mProductImg = (SliderLayout) x.findViewById(R.id.product_img);


                                HashMap<String,String> url_maps = new HashMap<String, String>();
                                url_maps.put(rate_name+"  "+"₱"+ratei, sm_domain + sm_group + sm_name + "." + sm_extension);


                                for(String name : url_maps.keySet()){
                                    TextSliderView defaultSliderView = new TextSliderView(getActivity());
                                    // initialize a SliderLayout
                                    defaultSliderView
                                            .description(name)
                                            .image(url_maps.get(name))
                                            .setScaleType(BaseSliderView.ScaleType.Fit);

                                    //add your extra information
                                    defaultSliderView.bundle(new Bundle());
                                    defaultSliderView.getBundle()
                                            .putString("extra",name);


                                    mProductImg.addSlider(defaultSliderView);

                                }
                            mProductImg.stopAutoCycle();
                                mProductImg.setCustomAnimation(new DescriptionAnimation());
                                mProductImg.setCustomIndicator((PagerIndicator) x.findViewById(R.id.custom_indicator));


                                Glide.with(getActivity())
                                        .load(sm_domain + sm_group + sm_name + "." + sm_extension)
                                        .centerCrop()
                                        .dontAnimate()
                                        .placeholder(R.drawable.ic_launcher)
                                        .into(circleImage);







//                            for (int i = 0; i < strs.length(); i++) {
//
//                                JSONObject jsonn = strs.getJSONObject(i);
//                                json.getString("title");
//                                json.getString("description");
//                                json.getString("price");
//                                json.getString("created_date");
//                                JSONArray cat = json.getJSONArray("category");
//                                JSONArray imge = json.getJSONArray("image");
//                                JSONArray location = json.getJSONArray("location");
//                                JSONArray about_seller = json.getJSONArray("about_seller");
//
//                            }

                                //  variantss = new String[location.length()];




                                //Log.d("VARIANTSSSSSSSSSS", Arrays.toString(variantss));

                                //  String[]   vas = new String[location.length()];








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




        /////////////////////  END




        RateMe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (rateIsVisible) {
                    rate.setVisibility(View.GONE);
                } else {
                    rate.setVisibility(View.VISIBLE);
                }

                rateIsVisible = !rateIsVisible;
            }
        });
        bookNow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Snackbar.make(v, "To Book Services Please Visit Our Website:  www.bigbenta.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Toast.makeText(getActivity(), "To Book Services Please Visit Our Website:  www.bigbenta.com",Toast.LENGTH_SHORT).show();

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle( bisname.getText().toString())
                        .setContentDescription( subcat_namee.getText().toString())
                        .setContentUrl(Uri.parse(Constants.BIGBENTA_ITEM_LINK ))
                               // item_id + "/" + product_name.getText().toString()))
                        .setImageUrl(Uri.parse(sm_domain + sm_group + sm_name + "." + sm_extension))
                        .build();

                shareDialog.show(linkContent);
            }
        });
//        Glide.with(getActivity())
//                .load(pic)
//                .centerCrop()
//                .dontAnimate()
//                .placeholder(R.drawable.ic_launcher)
//                .into(circleImage);
//        textView.setText(price);
//
//        name.setText(namee);




      //  store_name.setText(prod_storename);
       // available.setText(quantity);
       // min_order.setText(minimum_order);



        mAdapter = new RelatedAdapter(CardInfoList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(x.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
       // prepareMovieData();
        return x;
    }
    private void prepareMovieData() {
        String mProductName[] ={"Back Massage","Stone Massage",
                "Facial Massage", "Deep Tissue",
                "Swedish massage", "Shitsua Massage",
        };
        String mImageUrl[]= {"https://i.kinja-img.com/gawker-media/image/upload/s--nDfNPRuJ--/18ixgbfhi2ve7jpg.jpg",
                "http://www.westoverbeauty.co.uk/img/treatments/hotstone.jpg",
                "http://www.folkestonemassagetherapy.com/Facial-Massage.jpg",
                "http://www.nagsheadmassage.com/Deep%20Tissue%20Massage%202b.jpg",
                "http://www.manorbeauty.co.uk/images/swedish-massage.jpg",
                "http://www.nevermindthebuspass.com/wp-content/uploads/2012/02/shiatsu-stretch1.jpg"
        };
        for (int i = 0; i < mProductName.length; i++) {
            RelatedData cardMainData = new RelatedData(mImageUrl[i], mProductName[i]);
            CardInfoList.add(cardMainData);
        }
        mAdapter.notifyDataSetChanged();

    }


}
