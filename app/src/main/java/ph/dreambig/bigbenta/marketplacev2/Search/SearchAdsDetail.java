package ph.dreambig.bigbenta.marketplacev2.Search;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.AdsItemDetail.AdsDetailRequest;
import ph.dreambig.bigbenta.marketplacev2.AdsItemDetail.AdsDetailRetrofitResponse;
import ph.dreambig.bigbenta.marketplacev2.AdsItemDetail.AdsItemDetailModel;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchAdsDetail extends Fragment {
    CircleImageView circleImage;
    String city_name = "";
    String mobile="";
    String imgmd="";
    String email="";
    String firstN="";
    View  x;
    String lastN="";
    String region_name = "";
    API_Retrofit service;
    String image_sm="";
    private boolean sendIsVisible = false;
    private boolean rateIsVisible = false;
    public static ArrayList<AdsItemDetailModel> latestClass = new ArrayList<>();
    String item_id;
    AdsItemDetailModel setterGetter;
    private View sendView, rate;
    public SearchAdsDetail() {
        // Required empty public constructor
    }
    String price,prod_name,prod_storename,prod_image,quantity,minimum_order;
    TextView textView,product_name,created_date,available,category;
    TextView Descriptionn,nameofSeller,mobilee,emailme,product_pricee;
    SliderLayout mProductImg;
Button msgButton,RateMe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       x =   inflater.inflate(R.layout.fragment_adsitemdetail, container, false);

        Typeface bariol = Typeface.createFromAsset(getContext().getAssets(), "Bariol_Regular.otf");

        String pic = getArguments().getString("pic");
        String namee = getArguments().getString("name");
        String price = getArguments().getString("price");
        item_id = getArguments().getString("item_id");

        ((MainActivity) getActivity()).setTextName(namee);



        mProductImg = (SliderLayout) x.findViewById(R.id.product_img);
        sendView = x.findViewById(R.id.sendNow);
        rate  = x.findViewById(R.id.rate);

         Descriptionn  =(TextView) x.findViewById(R.id.decs);
        circleImage = (CircleImageView) x.findViewById(R.id.profile_image);
        textView = (TextView) x.findViewById(R.id.price);
        product_name = (TextView) x.findViewById(R.id.product_name);
        mobilee = (TextView) x.findViewById(R.id.mobile);
        emailme = (TextView) x.findViewById(R.id.emailme);
        nameofSeller = (TextView) x.findViewById(R.id.name);
        //  store_name= (TextView)x. findViewById(R.id.store_name);
        category= (TextView) x.findViewById(R.id.category);
        created_date= (TextView)x. findViewById(R.id.posted);
        msgButton = (Button)x.findViewById(R.id.bookNow);
        RateMe= (Button)x.findViewById(R.id.RateMe);
        sendView.setVisibility(View.GONE);
        rate.setVisibility(View.GONE);

        Descriptionn.setTypeface(bariol);
        textView.setTypeface(bariol);
        product_name.setTypeface(bariol);
        mobilee.setTypeface(bariol);
        emailme.setTypeface(bariol);
        nameofSeller.setTypeface(bariol);
        category.setTypeface(bariol);
        created_date.setTypeface(bariol);
        msgButton.setTypeface(bariol);
        RateMe.setTypeface(bariol);


        /////////////////START
//        Map<String, String> paramscategories = new HashMap<String, String>();
//        paramscategories.put("app_key", "88888");
//        paramscategories.put("category", "classified");
//        paramscategories.put("is_mobile", "1");
//        paramscategories.put("item_id", item_id);
//
//        Log.d("PARAMCAT_SERVICE", paramscategories.toString());
//        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST, Constants.API_DOMAINTest+Constants.API_search,new JSONObject(paramscategories),
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
//
//                        String imgclassi = "";
//                        String region_name = "";
//                        String city_name = "";
//                        JSONArray strs = null;
//                        String firstN= "";
//                        String lastN= "";
//                        String email= "";
//                        String mobile= "";
//                        try {
//                            Log.d("REQ_SERVICE", req.toString());
//
//                            messege = req.getString("message");
//                            code = req.getString("code");
//                            JSONObject data = req.getJSONObject("data");
//
//
//                            JSONObject classified = data.getJSONObject("classified");
//
//
//
//                            strs = classified.getJSONArray("classified");
//
//                            for (int i = 0; i < strs.length(); i++) {
//
//                                JSONObject json = strs.getJSONObject(i);
//                                json.getString("title");
//                                json.getString("description");
//                                json.getString("price");
//                                json.getString("created_date");
//                                JSONArray cat =  json.getJSONArray("category");
//                                JSONArray imge = json.getJSONArray("image");
//                                JSONArray location = json.getJSONArray("location");
//                                JSONArray about_seller = json.getJSONArray("about_seller");
//
//                                product_name.setText(json.getString("title"));
//
//                              //  variantss = new String[location.length()];
//
//
//                                for (int c = 0; c < cat.length(); c++) {
//                                    JSONObject catg = cat.getJSONObject(c);
//                                 String   cat_name = catg.getString("category_name");
//                                    category.setText(cat_name);
//
//                                }
//
//                                for (int n = 0; n < location.length(); n++) {
//                                    JSONObject loc = location.getJSONObject(n);
//                                    city_name = loc.getString("city_name");
//                                    region_name = loc.getString("region_name");
//                                }
//
//                                for (int s = 0; s < about_seller.length(); s++) {
//                                    JSONObject seller = about_seller.getJSONObject(s);
//                                    firstN = seller.getString("first_name");
//                                    lastN = seller.getString("last_name");
//                                    email = seller.getString("email");
//                                    mobile = seller.getString("mobile");
//                                    image_sm = seller.getString("image_md");
//
//                                    nameofSeller.setText(firstN+" "+lastN);
//                                    mobilee.setText(mobile);
//                                    emailme.setText(email);
//                                    Glide.with(getActivity())
//                                            .load(image_sm)
//                                            .centerCrop()
//                                            .dontAnimate()
//                                            .placeholder(R.drawable.ic_launcher)
//                                            .into(circleImage);
//
//                                }
//
//
//                                //Log.d("VARIANTSSSSSSSSSS", Arrays.toString(variantss));
//
//                              //  String[]   vas = new String[location.length()];
//
//
//                          //  String [] imgpics = new String[imge.length()];
//                                for (int j = 0; j < imge.length(); j++) {
//                                    JSONObject jsonImg = imge.getJSONObject(j);
//
//
//
//                                    setterGetter = new AdsItemDetailModel(json.getString("id"),json.getString("title"),json.getString("description"),json.getString("price"),
//                                            json.getString("created_date"),json.getString("category_name"),city_name,region_name,jsonImg.getString("image_md"));
//
//                                    latestClass.add(setterGetter);
//
//
//                                    textView.setText("₱"+json.getString("price"));
//                                   // store_name.setText(json.getString(Constants.STORE_NAME));
//                                    Descriptionn.setText(Html.fromHtml(json.getString("description")));
//                                    created_date.setText(json.getString("created_date"));
//                                    Log.d("PRICEEEEE", json.getString("price"));
//
//                                    Log.d("ITEM_IDDDD", item_id);
//                                //imgpics[j]= jsonImg.getString("image_md");
//
//
//
//                                    HashMap<String, String> url_maps = new HashMap< >();
//
//
//
////                                    String v = Arrays.toString(variantss);
////                                    v = v.substring(1, v.length()-1);
////                                    System.out.println("New New String: " + v);
////                                    vas[j] = v;
////                                    System.out.println("New New VASSSSSS: " + Arrays.toString(vas));
//
//                                    url_maps.put("₱"+json.getString("price"),jsonImg.getString("image_md"));
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
//                                       // Log.d("VARIAAAAAAA", Arrays.toString(varia));
//
//
//                                       // variantFunc();
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
//                            }
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
        AdsDetailRequest serviceRequest = new AdsDetailRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setCategory("classified");
        serviceRequest.setIs_mobile("1");
        serviceRequest.setItem_id(item_id);

        Call<AdsDetailRetrofitResponse> serviceResponseCall =  service.getAdsDetail(serviceRequest);
        Log.d("ITEM_IDDDDDD", item_id);
        serviceResponseCall.enqueue(new Callback<AdsDetailRetrofitResponse>() {
            @Override
            public void onResponse(Call<AdsDetailRetrofitResponse> call, retrofit2.Response<AdsDetailRetrofitResponse> response) {
                int statusCode = response.code();


                AdsDetailRetrofitResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));

                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());

                for(int i=0;i< serviceResponsee.getData().getClassified().getClassified().size();i++) {

//                    latestStr.add(serviceResponsee);
//                    Log.d("IMAGGEEEEEEEEEEE",   serviceResponsee.getData().get(i).getSmDomain()+ serviceResponsee.getData().get(i).getSmGroup()+serviceResponsee.getData().get(i).getSmName()+"."+serviceResponsee.getData().get(i).getSmExtension());
//                    adapter.notifyDataSetChanged();
                    String   cat_name = serviceResponsee.getData().getClassified().getClassified().get(i).getCategory().get(i).getCategoryName().toString();
                                    category.setText(cat_name);
                }

                for(int j=0;j< serviceResponsee.getData().getClassified().getClassified().size();j++) {
                    String   titl = serviceResponsee.getData().getClassified().getClassified().get(j).getTitle().toString();
                    product_name.setText(titl);
                }


                for(int k=0;k< serviceResponsee.getData().getClassified().getClassified().size();k++) {
                    city_name = serviceResponsee.getData().getClassified().getClassified().get(k).getLocation().get(k).getCityName().toString();
                    region_name = serviceResponsee.getData().getClassified().getClassified().get(k).getLocation().get(k).getRegionName().toString();
                }

                for(int l=0;l< serviceResponsee.getData().getClassified().getClassified().size();l++) {
                    lastN = serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getLastName().toString();
                    firstN= serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getFirstName().toString();
                    email = serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getEmail().toString();
//                    Log.d("MOBILE", serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getMobile());
//                    Log.d("MOBILE2", serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getMobile().toString());
                    if(serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getMobile()==null) {
                        mobile="";
                    }else{
                        mobile = serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getMobile().toString();
                    }
                     image_sm = serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(0).getImageMd().toString();
                    lastN = serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getLastName().toString();
                    firstN= serviceResponsee.getData().getClassified().getClassified().get(l).getAboutSeller().get(l).getFirstName().toString();
                    mobilee.setText(mobile);
                    emailme.setText(email);
                    nameofSeller.setText(firstN+" "+lastN);
                    Log.d("IMAGEPO", image_sm);
                    Glide.with(getActivity())
                                        .load(image_sm)
                                           .centerCrop()
                                          .dontAnimate()
                                            .placeholder(R.drawable.ic_launcher)
                                            .into(circleImage);
                }

                for(int m=0;m< serviceResponsee.getData().getClassified().getClassified().size();m++) {
                    textView.setText("₱" + serviceResponsee.getData().getClassified().getClassified().get(m).getPrice().toString());
                                   // store_name.setText(json.getString(Constants.STORE_NAME));
                                    Descriptionn.setText(serviceResponsee.getData().getClassified().getClassified().get(m).getDescription().toString());
                                    created_date.setText(serviceResponsee.getData().getClassified().getClassified().get(m).getCreatedDate().toString());

                }


                for(int p=0;p<serviceResponsee.getData().getClassified().getClassified().get(0).getImage().size();p++) {
//
                    HashMap<String, String> url_maps = new HashMap<>();
                    url_maps.put(textView.getText().toString(),serviceResponsee.getData().getClassified().getClassified().get(0).getImage().get(p).getImageMd().toString());

                    for (String namei : url_maps.keySet()) {
                        TextSliderView defaultSliderView = new TextSliderView(getActivity());

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
                   mProductImg.setCustomIndicator((PagerIndicator) x.findViewById(R.id.custom_indicator));
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


                }



            }


            @Override
            public void onFailure(Call<AdsDetailRetrofitResponse> call, Throwable t) {

            }
        });

        msgButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (sendIsVisible) {
                    sendView.setVisibility(View.GONE);
                } else {
                    sendView.setVisibility(View.VISIBLE);
                }

                sendIsVisible = !sendIsVisible;
            }
        });

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

        textView.setText(price);







        return x;
    }

}
