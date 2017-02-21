package ph.dreambig.bigbenta.marketplacev2.StoreItemDetail;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.MyCart2;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.MyCart.ShoppingCartData;
import ph.dreambig.bigbenta.marketplacev2.Home.Store.My_Favorites_Store.FavoritesCartData;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.StoreTabFragment;
import ph.dreambig.bigbenta.marketplacev2.Search.SearchActivitySample;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */

public class StoreItemDetail extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    API_Retrofit service;
    ShareDialog shareDialog;
    String imageMd,from;
    private final static int FADE_DURATION = 4000;
    CallbackManager callbackManager;
    Realm realm;
    Bundle bundle;
  ImageView  share;
    String variantId= "";
    String storeId= "";
    ImageView favoriteee;
    ShoppingCartData addcart;
    FavoritesCartData addtofav;
    SessionManager sessionManager;
    String[]  variantss;
    String[]   varia;
    String variantString="";
    ArrayAdapter<String> adapter;
    protected RecyclerView SrecyclerView;
    protected RecyclerView.LayoutManager layoutManager1;

    View  x;
    //protected SingleCheckGenreAdapter Singleadapter;
    String[] varian;
    public StoreItemDetail() {
        // Required empty public constructor
    }
   public  static String pic;
    String price,namee,prod_storename,prod_image,quantity,minimum_order,prod_name,storename,item_id;
  static  TextView desctext,textView,product_name,product_info,available,min_order,store_name;
  static  SliderLayout mProductImg;
    NumberPicker numberPicker;
    Button addtocard;
    TextView origPrice,sale,as,mo,free_delivery;
    StoreItemDetailModel setterGetter;
    public static ArrayList<StoreItemDetailModel> latestClass = new ArrayList<>();

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

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
       x =   inflater.inflate(ph.dreambig.bigbenta.marketplacev2.R.layout.fragment_storeitemdetail, container, false);
        free_delivery =  (TextView) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.free_delivery);
        desctext=  (TextView) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.desctext);
        product_info =  (TextView) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_info);
        textView = (TextView) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_price);
        product_name = (TextView) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_name);
        store_name= (TextView)x. findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.store_name);
        available= (TextView) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.isAvailable);
        min_order= (TextView)x. findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.minOrder);
        favoriteee = (ImageView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.favoriteee) ;
        // SrecyclerView = (RecyclerView)x. findViewById(R.id.recycler_view7);
         layoutManager1 = new LinearLayoutManager(getActivity());
        sale = (TextView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.sale) ;
        as = (TextView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.as) ;
        mo = (TextView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.mo) ;
        origPrice = (TextView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.origPrice);
        share = (ImageView)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.share);
        addtocard = (Button)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.addtocard);
        Typeface Abold = Typeface.createFromAsset(getContext().getAssets(), "Bariol_Regular.otf");
        product_info.setTypeface(Abold);
        textView.setTypeface(Abold);
        product_name.setTypeface(Abold);
        store_name.setTypeface(Abold);
        available.setTypeface(Abold);
        min_order.setTypeface(Abold);
        sale.setTypeface(Abold);
        origPrice.setTypeface(Abold);
        as.setTypeface(Abold);
        mo.setTypeface(Abold);
        desctext.setTypeface(Abold);
        addtocard.setTypeface(Abold);
        free_delivery.setTypeface(Abold);
       // Singleadapter = new SingleCheckGenreAdapter(makeSingleCheckGenres());
//        SrecyclerView.setLayoutManager(layoutManager1);
//        SrecyclerView.setAdapter(Singleadapter);
        realm = Realm.getDefaultInstance();
        setHasOptionsMenu(true);
//        price= getArguments().getString("price");
//        prod_name=getArguments().getString("prod_name");
//        prod_storename=getArguments().getString("prod_storename");
//        prod_image=getArguments().getString("image");
//        quantity=getArguments().getString("quantity");
//        minimum_order=getArguments().getString("minimum_order");
        sessionManager = new SessionManager(getActivity());


        bundle = getArguments();
        if (bundle != null){
            item_id = getArguments().getString("item_id");
            from = getArguments().getString("from");
        }

        Log.d("ITEM_ID", item_id);
        //Log.d("FROM", from);

//        StoreItemDetailService latestClassified = new StoreItemDetailService(getActivity());
//        latestClassified.serviceLatestClassified(Constants.API_DOMAINTest,Constants.API_search ,item_id,latestClass,getActivity());


        /////////////////START
//        Map<String, String> paramscategories = new HashMap<String, String>();
//        paramscategories.put("app_key", "88888");
//        paramscategories.put("category", "stores");
//        paramscategories.put("is_mobile", "1");
//        paramscategories.put("subcategory", "1");
//        paramscategories.put("item_id", item_id);
//        Log.d("PARAMCAT_SERVICE", paramscategories.toString());
//        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,Constants.API_DOMAINTest+Constants.API_search,new JSONObject(paramscategories),
//                new Response.Listener<JSONObject>() {
//
//
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
//                            Log.d("REQ_SERVICE", req.toString());
//
//                            messege = req.getString("message");
//                            code = req.getString("code");
//                            JSONObject data = req.getJSONObject("data");
//
//
//                            JSONObject stores = data.getJSONObject("stores");
//                            cdoo = stores.getString("code");
//
//
//
//                            strs = stores.getJSONArray("stores");
//
//                            for (int i = 0; i < strs.length(); i++) {
//
//                                JSONObject json = strs.getJSONObject(i);
//
//                                JSONArray imge = json.getJSONArray("image");
//                                JSONArray vari = json.getJSONArray("variants");
//                               variantss = new String[vari.length()];
//
//
//
//                                for (int n = 0; n < vari.length(); n++) {
//                                    JSONObject varint = vari.getJSONObject(n);
//                                    variantss[n] = varint.getString("choices");
//                                }
//
//
//
//
//                                Log.d("VARIANTSSSSSSSSSS", Arrays.toString(variantss));
//
//                            String[]   vas = new String[vari.length()];
//
//
//
//                                for (int j = 0; j < imge.length(); j++) {
//                                    JSONObject jsonImg = imge.getJSONObject(j);
//
//                                    Log.d("ITEM_IDDDD", item_id);
//
//                                    setterGetter = new StoreItemDetailModel(json.getString(Constants.PRICE), json.getString(Constants.PRODUCT_NAME),
//                                            json.getString(Constants.STORE_NAME), jsonImg.getString("image_md"),json.getString("id"),json.getString("long_description"));
//
//                                    latestClass.add(setterGetter);
//
//                                    product_name.setText(json.getString(Constants.PRODUCT_NAME));
//                                    textView.setText(json.getString(Constants.PRICE));
//                                    store_name.setText(json.getString(Constants.STORE_NAME));
//                                   product_info.setText(Html.fromHtml(json.getString("long_description")));
//                                    min_order.setText(json.getString("minimum_order"));
//                                    available.setText(json.getString("quantity"));
//
////                                    String[] picArray = new String[imge.length()];
////
////                                    picArray[j] =  jsonImg.getString("image_md");
////                                    variantss[j] =  varint.getString("choices");
//
//
////                                    variantFunc(picArray);
//
////                                    Log.d("CHOICESSSSSSSSS", Arrays.toString(variantss));
////                                    Log.d("PICCCCCCCCCCSSSSSSSS", Arrays.toString(picArray));
//                                   pic =   jsonImg.getString("image_md");
//
//
//
//                                    HashMap<String, String> url_maps = new HashMap< >();
//
//
//
//                                    String v = Arrays.toString(variantss);
//                                    v = v.substring(1, v.length()-1);
//                                    System.out.println("New New String: " + v);
//                                    vas[j] = v;
//                                    System.out.println("New New VASSSSSS: " + Arrays.toString(vas));
//
//                                    url_maps.put(v,jsonImg.getString("image_md"));
//
//                                    for(String namei : url_maps.keySet()){
//                                        TextSliderView defaultSliderView = new TextSliderView(getActivity());
//
//                                        defaultSliderView
//                                                .description(namei)
//                                                .image(url_maps.get(namei))
//                                                .setScaleType(BaseSliderView.ScaleType.Fit);
//
//                                        defaultSliderView.bundle(new Bundle());
//                                        defaultSliderView.getBundle()
//                                                .putString("extra",namei);
//                                        Log.d("VARIAAAAAAA", Arrays.toString(varia));
//
//
//                                        variantFunc();
//                                        mProductImg.addSlider(defaultSliderView);
//
//                                    }
//
//
//
//
//
//
//                                    mProductImg.startAutoCycle();
//
//                                    mProductImg.setCustomAnimation(new DescriptionAnimation());
//                                    mProductImg.setCustomIndicator((PagerIndicator) x.findViewById(R.id.custom_indicator));
//                                    mProductImg.setDuration(9000);
//                                    mProductImg.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
//                                        @Override
//                                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                                        }
//
//                                        @Override
//                                        public void onPageSelected(int position) {
//
//                                        }
//
//                                        @Override
//                                        public void onPageScrollStateChanged(int state) {
//
//                                        }
//                                    });
////                                    ListView l = (ListView)x.findViewById(R.id.transformers);
////                                    l.setAdapter(new SimpleAdapter(getActivity()));
////                                    l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                                        @Override
////                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                                            mProductImg.setPresetTransformer(((TextView) view).getText().toString());
////                                            Toast.makeText(getActivity(), ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
////                                        }
////                                    });
//
//
//                                }
//
//                                }
//
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//
//
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




        /////////////////////  END

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API_Retrofit.class);
        StoreDetailRequest serviceRequest = new StoreDetailRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory("stores");
        serviceRequest.setIs_mobile("1");
        serviceRequest.setItem_id(item_id);
        serviceRequest.setSubcategory("1");

        Call<ph.dreambig.bigbenta.marketplacev2.StoreItemDetail.StoreDetailRetrofitResponse> serviceResponseCall =  service.getStoreDetail(serviceRequest);
        Log.d("ITEM_IDDDDDD", item_id);
        serviceResponseCall.enqueue(new Callback<StoreDetailRetrofitResponse>() {
            @Override
            public void onResponse(Call<StoreDetailRetrofitResponse> call, retrofit2.Response<StoreDetailRetrofitResponse> response) {
                int statusCode = response.code();


                StoreDetailRetrofitResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));

                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());

                for(int i=0;i< serviceResponsee.getData().getStores().getStores().size();i++) {
                    variantId = serviceResponsee.getData().getStores().getStores().get(0).getVariantId().toString();
                    if(serviceResponsee.getData().getStores().getStores().get(0).getIsSale()==1){
                        origPrice.setText("₱"+serviceResponsee.getData().getStores().getStores().get(0).getOriginalPrice().toString());
                        origPrice.setVisibility(View.VISIBLE);
                        sale.setVisibility(View.VISIBLE);
                    }
                    storeId =  serviceResponsee.getData().getStores().getStores().get(0).getStoreId().toString();
                                    product_name.setText(serviceResponsee.getData().getStores().getStores().get(0).getTitle().toString());
                    if(from.equals("MainActivity")) {
                        ((MainActivity) getActivity()).setTextName(product_name.getText().toString());
                    }else{
                        ((SearchActivitySample) getActivity()).setTextName(product_name.getText().toString());
                    }
                                    textView.setText("₱"+serviceResponsee.getData().getStores().getStores().get(0).getPrice().toString());
                                    store_name.setText(serviceResponsee.getData().getStores().getStores().get(0).getStoreName().toString());
                                   product_info.setText(Html.fromHtml(serviceResponsee.getData().getStores().getStores().get(0).getLongDescription().toString()));
                                    min_order.setText(serviceResponsee.getData().getStores().getStores().get(0).getMinimumOrder().toString());
                                    available.setText(serviceResponsee.getData().getStores().getStores().get(0).getQuantity().toString());
                }
              if(Integer.valueOf(serviceResponsee.getData().getStores().getStores().get(0).getIsFreeShipping().toString()) == 1){
                  free_delivery.setVisibility(View.VISIBLE);
                  setScaleAnimation(free_delivery);
              }


                if(Integer.valueOf(serviceResponsee.getData().getStores().getStores().get(0).getQuantity().toString())!=0) {
                    addtocard.setEnabled(true);
                    addtocard.setText("ADD TO CART");
                }else{
                    addtocard.setEnabled(false);
                    addtocard.setText("OUT OF STOCK");
                }

                HashMap<String, String> url_maps = new HashMap<>();
                TextSliderView defaultSliderView;
                varian = new String[serviceResponsee.getData().getStores().getStores().size()];
                for(int p=0;p<serviceResponsee.getData().getStores().getStores().size();p++) {

                    if(serviceResponsee.getData().getStores().getStores().get(p).getVariants().size()!=0) {
                        varian[p] = serviceResponsee.getData().getStores().getStores().get(p).getVariants().get(0).getChoices().toString();
                        Log.d("ARRAYVARIANT", Arrays.toString(varian));
                        if (serviceResponsee.getData().getStores().getStores().get(p).getVariants().get(0).getVariantName().toString().equalsIgnoreCase("color") || serviceResponsee.getData().getStores().getStores().get(p).getVariants().get(0).getChoices().toString().equals("")) {
                            adapter = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_dropdown_item_1line, varian);
                            BetterSpinner colorSpin = (BetterSpinner) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.color2);
                            colorSpin.setAdapter(adapter);
                            colorSpin.setVisibility(View.VISIBLE);
                            colorSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String color = varian[position];
                                    Log.d("yyfyfiy", color);
                                }
                            });
                        }


                        if (serviceResponsee.getData().getStores().getStores().get(p).getVariants().get(0).getVariantName().toString().equalsIgnoreCase("size")) {
                            adapter = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_dropdown_item_1line, varian);
                            BetterSpinner sizeSpin = (BetterSpinner)
                                    x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.size);
                            sizeSpin.setAdapter(adapter);
                            sizeSpin.setVisibility(View.VISIBLE);
                            sizeSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String color = varian[position];
                                    Log.d("yyfyfiy", color);
                                }
                            });

                        }


                        if (!serviceResponsee.getData().getStores().getStores().get(p).getVariants().get(0).getVariantName().toString().equalsIgnoreCase("size") && !serviceResponsee.getData().getStores().getStores().get(p).getVariants().get(0).getVariantName().toString().equalsIgnoreCase("color") && !serviceResponsee.getData().getStores().getStores().get(p).getVariants().get(0).getVariantName().toString().equalsIgnoreCase("")) {
                            adapter = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_dropdown_item_1line, varian);
                            BetterSpinner sizeSpin = (BetterSpinner)
                                    x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.variant);
                            sizeSpin.setAdapter(adapter);
                            sizeSpin.setVisibility(View.VISIBLE);
                            sizeSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String color = varian[position];
                                    Log.d("yyfyfiy", color);
                                }
                            });

                        }



             imageMd     =  serviceResponsee.getData().getStores().getStores().get(0).getImage().get(0).getImageMd().toString();

                    url_maps.put(serviceResponsee.getData().getStores().getStores().get(p).getVariants().get(0).getChoices().toString()  ,serviceResponsee.getData().getStores().getStores().get(p).getImage().get(0).getImageMd().toString());

                    for (String namei : url_maps.keySet()) {
                        defaultSliderView = new TextSliderView(getActivity());

                        defaultSliderView
                                .description(namei)
                                .image(url_maps.get(namei))
                                .setScaleType(BaseSliderView.ScaleType.Fit);

                        defaultSliderView.bundle(new Bundle());
                        defaultSliderView.getBundle()
                                .putString("extra", namei);
                        mProductImg.addSlider(defaultSliderView);

                    }

                    mProductImg.startAutoCycle();
                    mProductImg.setCustomAnimation(new DescriptionAnimation());
                    mProductImg.setCustomIndicator((PagerIndicator) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.custom_indicator));
                    mProductImg.setDuration(9000);
                    mProductImg.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });


                    }else{
////START
                        imageMd     =  serviceResponsee.getData().getStores().getStores().get(0).getImage().get(0).getImageMd().toString();

                        url_maps.put("" ,serviceResponsee.getData().getStores().getStores().get(p).getImage().get(0).getImageMd().toString());

                        for (String namei : url_maps.keySet()) {
                            DefaultSliderView defaultt = new DefaultSliderView(getActivity());

                            defaultt
                                    .description(namei)
                                    .image(url_maps.get(namei))
                                    .setScaleType(BaseSliderView.ScaleType.Fit);

                            defaultt.bundle(new Bundle());
                            defaultt.getBundle()
                                    .putString("extra", namei);
                            mProductImg.addSlider(defaultt);

                        }
                        mProductImg.stopAutoCycle();
                        mProductImg.setCustomAnimation(new DescriptionAnimation());
                        mProductImg.setCustomIndicator((PagerIndicator) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.custom_indicator));
                        mProductImg.setDuration(9000);
                        mProductImg.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });


                        //////END

                    }





                }


            }


            @Override
            public void onFailure(Call<StoreDetailRetrofitResponse> call, Throwable t) {

            }
        });






//        for (int i = 0; i < latestClass.size(); i++) {
//
//             namee = latestClass.get(i).getclassif_name();
//            prod_storename = latestClass.get(i).getStorename();
//
//            price = latestClass.get(i).getprice();
//
//        }

        Log.d("DETAIL", namee+" "+" "+prod_storename);







        addtocard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int quantity  = numberPicker.getValue();
                //Log.d("HELLLOO",Integer.toString(quantity)+" "+pic +" "+namee+" "+price+" "+Integer.toString(getNextKey()));

                String text = textView.getText().toString();
                String digits = text.replaceAll("[^0-9.]", "");
                System.out.println(digits);

                addCart(product_name.getText().toString(),Double.valueOf(digits), imageMd, quantity,store_name.getText().toString(),available.getText().toString(),variantId,storeId);
              // refresh_views();


//                SampleWebView fr=new SampleWebView();
//                FragmentManager fm= getActivity().getSupportFragmentManager();
//                FragmentTransaction ft=fm.beginTransaction();
//                ft.replace(ph.dreambig.bigbenta.marketplace.R.id.containerView, fr);
//                ft.commit();

                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Added to Cart!")
                        .setContentText(quantity+" Items of "+namee+" has been Added to Cart")
                        .show();


            }
        });
        numberPicker =(NumberPicker)x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.numberpicker);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);
        textView.setText(price);
        product_name.setText(namee);
        store_name.setText(prod_storename);

        favoriteee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity  = numberPicker.getValue();
                String text = textView.getText().toString();
                String digits = text.replaceAll("[^0-9.]", "");
                System.out.println(digits);
                Log.d("PROD_NAME", product_name.getText().toString());
                addToFav(product_name.getText().toString(),Double.valueOf(digits), imageMd, quantity,store_name.getText().toString(),available.getText().toString(),variantId,storeId);

                StoreTabFragment fr=new StoreTabFragment();
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("position", "4");
                fr.setArguments(args);
                ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
                ft.commit();

                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Added to Favorites!")
                        .setContentText(quantity+" Items of "+namee+" has been Added to Favorites")
                        .show();
            }
        });

        mProductImg = (SliderLayout) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.product_img);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle(product_name.getText().toString())
                        .setContentDescription(product_info.getText().toString())
                        .setContentUrl(Uri.parse(Constants.BIGBENTA_ITEM_LINK +
                                item_id + "/" + product_name.getText().toString()))
                        .setImageUrl(Uri.parse(imageMd))
                        .build();

                shareDialog.show(linkContent);
            }
        });


//        HashMap<String,String> url_maps = new HashMap<String, String>();
//        url_maps.put("one", pic);
//        url_maps.put("two", pic);
//        url_maps.put("three", pic);


//        HashMap<String, StoreItemDetailModel> url_maps = new HashMap<String, StoreItemDetailModel>();
//        for (StoreItemDetailModel product : latestClass) {
//            url_maps.put(product.getimage(), product);
//
//        }
//
//
//
//        for(String name : url_maps.keySet()){
//            DefaultSliderView defaultSliderView = new DefaultSliderView(getActivity());
//            // initialize a SliderLayout
//            defaultSliderView
//                    .description(name)
//                    //.image(url_maps.get())
//                    .setScaleType(BaseSliderView.ScaleType.Fit);
//
//            //add your extra information
//            defaultSliderView.bundle(new Bundle());
//            defaultSliderView.getBundle()
//                    .putString("extra",name);
//
//
//            mProductImg.addSlider(defaultSliderView);
//
//        }
//        mProductImg.startAutoCycle();
//
//        mProductImg.setCustomAnimation(new DescriptionAnimation());
//        mProductImg.setCustomIndicator((PagerIndicator) x.findViewById(R.id.custom_indicator));
//        mProductImg.setDuration(4000);
//




        return x;

    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.action_service).setVisible(false);
//        menu.findItem(R.id.action_cads).setVisible(false);
//        menu.findItem(R.id.action_cart).setVisible(true);
       // menu.findItem(R.id.action_chat).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }
    @Override public void onDestroy(){
        super.onDestroy();
        realm.close();

    }
    @Override
    public void onStop() {
        mProductImg.stopAutoCycle();
        super.onStop();
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void addCart(final String ProdName, final double ProdPrice,final String ImgUrl,final int Quantity,String store_name,String avail,String variantId,String storeId){
        addcart = new ShoppingCartData();
        addcart.setId(getNextKey());
        addcart.setProductName(ProdName);
        addcart.setPrice(ProdPrice);
        addcart.setQuantity(Quantity);
        addcart.setSubTotal(Quantity*ProdPrice);
        addcart.setImageUrl(ImgUrl);
        addcart.setStoreName(store_name);
        addcart.setAvailableStocks(avail);
        addcart.setVariantId(variantId);
        addcart.setStoreId(storeId);

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(addcart);
        realm.commitTransaction();
        new FetchCountTask(AppController.CartItemsCount + Quantity).execute();
    }

    private void addToFav(final String ProdName, final double ProdPrice,final String ImgUrl,final int Quantity,String store_name,String avail,String variantId,String storeId){
        addtofav = new FavoritesCartData();
        addtofav.setId(getFavNextKey());
        addtofav.setProductName(ProdName);
        addtofav.setPrice(ProdPrice);
        addtofav.setQuantity(Quantity);
        addtofav.setSubTotal(Quantity*ProdPrice);
        addtofav.setImageUrl(ImgUrl);
        addtofav.setStoreName(store_name);
        addtofav.setAvailableStocks(avail);
        addtofav.setVariantId(variantId);
        addtofav.setStoreId(storeId);


        realm.beginTransaction();
        realm.copyToRealmOrUpdate(addtofav);
        realm.commitTransaction();
        //new FetchCountTask(BaseApplication.CartItemsCount + Quantity).execute();
    }
    public void refresh_views(){

        RealmResults<FavoritesCartData> r1 = realm.where(FavoritesCartData.class).findAll();
        String output="";
        for(FavoritesCartData cart: r1){

            output +=cart.toString();
        }

        product_info.setText(output);
    }
    public int getNextKey() {

try {
    return realm.where(ShoppingCartData.class).max("id").intValue() + 1;
}catch(NullPointerException e){
    e.printStackTrace();
}
        return 0;
    }

    public int getFavNextKey() {

        try {
            return realm.where(FavoritesCartData.class).max("id").intValue() + 1;
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }
    private void updateNotificationsBadge(int count) {
        AppController.CartItemsCount = count;
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class FetchCountTask extends AsyncTask<Void, Integer, Integer> {

        int CartCount;
        public FetchCountTask(int i) {
            super();
            CartCount = i;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            return CartCount;
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }

public void variantFunc(){

    Map<String, String> paramscategories = new HashMap<String, String>();
    paramscategories.put("app_key", "88888");
    paramscategories.put("is_mobile", "1");
    paramscategories.put("item_id", item_id);
  Log.d("PARAMCAT_SERVICE", paramscategories.toString());
    JsonArrayRequest jsObjReques = new JsonArrayRequest(Request.Method.POST,Constants.API_DOMAINTest+Constants.API_VARIANTS,new JSONObject(paramscategories),
            new Response.Listener<JSONArray>() {



                @Override
                public void onResponse(JSONArray req) {
                    System.out.println(req);
                    String messege = "";
                    String code = "";
                    String pname= "";
                    String variant = "";
                    String cod= "";
                    String name="";
                    String variant_name= "";
                    String item_name="";
                    String item_id = "";
                    JSONArray choices = null;
                    JSONArray strs = null;
                    JSONObject obj =null;
                    JSONObject choicesss =null;

                    try {
                        Log.d("REQ_SERVICE", req.toString());
                        for (int i = 0; i < req.length(); i++) {
                            obj = req.getJSONObject(i);
                            item_id =     obj.getString("item_id");
                            item_name =  obj.getString("item_name");
                            variant_name =obj.getString("variant_name");

                            choices = obj.getJSONArray("choices");
                            String[] ch = new String[choices.length()];
                           String[] s = (String[])ch;
                            System.out.println("ARRAYPO: " + Arrays.toString(s));
                            varian = new String[choices.length()];
                            for ( int k = 0; k <  choices.length(); k++) {
                               choicesss = choices.getJSONObject(k);
                                System.out.println("POJSONNNNNN!!!POTA: " +choicesss);


                                    varian[k] = choicesss.getString("choices");

                                Log.d("BWAHAHHAHAHAHAH", String.valueOf(varian[k]));
                                System.out.println("PLEASSSEEEEEEE: " + Arrays.toString(varian));



                            }
                           // spinnerSliderFunc(varian, picArray );
                          if(variant_name.equalsIgnoreCase("color")||variant_name.equals("")) {
                              adapter = new ArrayAdapter<String>(getActivity(),
                                      android.R.layout.simple_dropdown_item_1line, varian);
                              BetterSpinner colorSpin = (BetterSpinner) x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.color2);
                              colorSpin.setAdapter(adapter);
                              colorSpin.setVisibility(View.VISIBLE);
                              colorSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                  @Override
                                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                      String color = varian[position];
                                      Log.d("yyfyfiy", color);
                                  }
                              });
                          }

                            if(variant_name.equalsIgnoreCase("size")){
                              adapter = new ArrayAdapter<String>(getActivity(),
                                      android.R.layout.simple_dropdown_item_1line, varian);
                              BetterSpinner sizeSpin = (BetterSpinner)
                                      x.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.size);
                             sizeSpin.setAdapter(adapter);
                              sizeSpin.setVisibility(View.VISIBLE);
                              sizeSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                  @Override
                                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                      String color = varian[position];
                                      Log.d("yyfyfiy", color);
                                  }
                              });

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




    /////////////////////  END

}
  public void  spinnerSliderFunc(final String[] variant, String [] pics){

      System.out.println("LOOB: " + Arrays.toString(variant));
//      adapter = new ArrayAdapter<String>(getActivity(),
//              android.R.layout.simple_dropdown_item_1line, variant);
//                                textView.setVisibility(View.VISIBLE);
//      BetterSpinner colorSpin = (BetterSpinner)
//              x.findViewById(R.id.spinner1);
//      colorSpin.setAdapter(adapter);
//
//      colorSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//          @Override
//          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              String color =   variant[position];
//              Log.d("yyfyfiy", color);
//          }
//      });


//      ///LIBRARY
//      HashMap<String, String> url_maps = new HashMap< >();
//
//      // picArray[j];
//      //Arrays.toString(picArray)
//
//      String newString = Arrays.toString(pics);
//      newString = newString.substring(1, newString.length()-1);
//      Log.d("PICSTRING", newString);
//
//
//
//
//      String v = Arrays.toString(variant);
//
//
//      v = v.substring(1, v.length()-1);
//      System.out.println("New New ArrayString: " + variant.toString());
//      System.out.println("New New String: " + v);
//
//
//
//
//      url_maps.put(v,newString );
//
//      for(String namei : url_maps.keySet()){
//          TextSliderView defaultSliderView = new TextSliderView(getActivity());
//          // initialize a SliderLayout
//          defaultSliderView
//                  .description(namei)
//                  .image(url_maps.get(namei))
//                  .setScaleType(BaseSliderView.ScaleType.Fit);
//
//          //add your extra information
//          defaultSliderView.bundle(new Bundle());
//          defaultSliderView.getBundle()
//                  .putString("extra",namei);
//
//
//          mProductImg.addSlider(defaultSliderView);
//
//      }
//      mProductImg.startAutoCycle();
//
//      mProductImg.setCustomAnimation(new DescriptionAnimation());
//      mProductImg.setCustomIndicator((PagerIndicator) x.findViewById(R.id.custom_indicator));
//      mProductImg.setDuration(9000);
  }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

}
