package ph.dreambig.bigbenta.marketplacev2.Home.AdsFrag.PostNewAds;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import ph.dreambig.bigbenta.marketplacev2.AdsSubCat.LatestCategory;
import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.R;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */


public class PostNewAds extends Fragment implements View.OnClickListener ,
        View.OnFocusChangeListener, View.OnTouchListener {
    private Toolbar toolbar;
    TextView toolbar_title;
    Spinner ConditionSpinner,ConditionSpinner2;
    String spinnerValue1,spinnerValue2;
    Button submitad;
    View x;
    int value1=0;
    Intent intent;
    int value2=0;
    String[] sub,sub2;
    ArrayAdapter deliveryadapter;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ImageView imageAdd1;
    ImageView imageAdd2;
    ImageView imageAdd3;
    ImageView imageAdd4;
    ImageView imageAdd5;
    ImageView imageAdd6;
    public PostNewAds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         x = inflater.inflate(R.layout.post_new_ads, container, false);
        imageAdd1 = (ImageView)x .findViewById(R.id.imageAdd1);
        imageAdd2 = (ImageView)x.findViewById(R.id.imageAdd2);
        imageAdd3 = (ImageView)x. findViewById(R.id.imageAdd3);
        imageAdd4 = (ImageView)x .findViewById(R.id.imageAdd4);
        imageAdd5 = (ImageView) x.findViewById(R.id.imageAdd5);
        imageAdd6 = (ImageView)x. findViewById(R.id.imageAdd6);
        ConditionSpinner = (Spinner) x.findViewById(R.id.condition);
         deliveryadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.condition, android.R.layout.simple_spinner_dropdown_item);
        ConditionSpinner.setAdapter(deliveryadapter);
        ConditionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerValue1 = String.valueOf(parent.getItemAtPosition(pos));

                if(spinnerValue1.equals("Real Estate")){
                    value1=155;
                }else if(spinnerValue1.equals("Cars/Vehicles")){
                    value1=173;
                }else if(spinnerValue1.equals("Mobile/Gadgets")){
                    value1=143;
                }else if(spinnerValue1.equals("Computers")){
                    value1=56;
                }else if(spinnerValue1.equals("Clothes")){
                    value1=31;
                }else if(spinnerValue1.equals("Pets and  Accessories")){
                    value1=134;
                }else if(spinnerValue1.equals("Hobbies")){
                    value1=50;
                }else if(spinnerValue1.equals("Appliances")){
                    value1=2;
                }else if(spinnerValue1.equals("Furniture")){
                    value1=93;
                }else if(spinnerValue1.equals("Bags and Luggages")){
                    value1=410;
                }else if(spinnerValue1.equals("Bussiness for Sale")){
                    value1=297;
                }else if(spinnerValue1.equals("Services")){
                    value1=196;
                }
                Log.d("spinnerValue1",Integer.toString(value1));
                getData1(value1);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        ConditionSpinner2 = (Spinner) x.findViewById(R.id.condition2);
         deliveryadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.condition, android.R.layout.simple_spinner_dropdown_item);
        ConditionSpinner2.setAdapter(deliveryadapter);
        ConditionSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerValue2 = String.valueOf(parent.getItemAtPosition(pos));
                if(spinnerValue2.equals("Real Estate")){
                    value2=155;
                }else if(spinnerValue2.equals("Cars/Vehicles")){
                    value2=173;
                }else if(spinnerValue2.equals("Mobile/Gadgets")){
                    value2=143;
                }else if(spinnerValue2.equals("Computers")){
                    value2=56;
                }else if(spinnerValue2.equals("Clothes")){
                    value2=31;
                }else if(spinnerValue2.equals("Pets and  Accessories")){
                    value2=134;
                }else if(spinnerValue2.equals("Hobbies")){
                    value2=50;
                }else if(spinnerValue2.equals("Appliances")){
                    value2=2;
                }else if(spinnerValue2.equals("Furniture")){
                    value2=93;
                }else if(spinnerValue2.equals("Bags and Luggages")){
                    value2=410;
                }else if(spinnerValue2.equals("Bussiness for Sale")){
                    value2=297;
                }else if(spinnerValue2.equals("Services")){
                    value2=196;
                }
                Log.d("spinnerValue1",Integer.toString(value2));
                    getData2(value2);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        submitad = (Button) x.findViewById(R.id.submitad);
        submitad.setOnClickListener(this);
        return x;

    }

    @Override
    public void onClick(View v) {
        if (v == imageAdd1) {
            selectImage(1);
        }
        if (v == imageAdd2) {
            selectImage(2);
        }
        if (v == imageAdd3) {
            selectImage(3);
        }
        if (v == imageAdd4) {
            selectImage(4);
        }
        if (v == imageAdd5) {
            selectImage(5);
        }
        if (v == imageAdd6) {
            selectImage(6);
        }
    }

    private void selectImage(final int imagePage) {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Camera")) {
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (imagePage == 1) {
                        startActivityForResult(intent, Constants.PHOTO_CAMERA_1);
                    } else if (imagePage == 2) {
                        startActivityForResult(intent, Constants.PHOTO_CAMERA_2);
                    } else if (imagePage == 3) {
                        startActivityForResult(intent, Constants.PHOTO_CAMERA_3);
                    } else if (imagePage == 4) {
                        startActivityForResult(intent, Constants.PHOTO_CAMERA_4);
                    } else if (imagePage == 5) {
                        startActivityForResult(intent, Constants.PHOTO_CAMERA_5);
                    } else if (imagePage == 6) {
                        startActivityForResult(intent, Constants.PHOTO_CAMERA_6);
                    }
                } else if (items[item].equals("Gallery")) {
                    intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    if (imagePage == 1) {
                        startActivityForResult(Intent.createChooser(intent, "Select File"),
                                Constants.PHOTO_GALLERY_1);
                    } else if (imagePage == 2) {
                        startActivityForResult(Intent.createChooser(intent, "Select File"),
                                Constants.PHOTO_GALLERY_2);
                    } else if (imagePage == 3) {
                        startActivityForResult(Intent.createChooser(intent, "Select File"),
                                Constants.PHOTO_GALLERY_3);
                    } else if (imagePage == 4) {
                        startActivityForResult(Intent.createChooser(intent, "Select File"),
                                Constants.PHOTO_GALLERY_4);
                    } else if (imagePage == 5) {
                        startActivityForResult(Intent.createChooser(intent, "Select File"),
                                Constants.PHOTO_GALLERY_5);
                    } else if (imagePage == 6) {
                        startActivityForResult(Intent.createChooser(intent, "Select File"),
                                Constants.PHOTO_GALLERY_6);
                    }
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    private void getData1(Integer val){
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("app_key", "88888");
        jsonParams.put("category_id", Integer.toString(val));
        jsonParams.put("is_mobile", "1");
        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST,Constants.API_DOMAINTest+Constants.API_ClassifiedSub,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {

                    LatestCategory setterGetter;

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
                            JSONArray data = req.getJSONArray("data");
                            sub2 = new String[data.length()];
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject cat = data.getJSONObject(i);
                                sub2[i] = cat.getString("name");



                            }


                            adapter2 = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_dropdown_item_1line, sub2);
                            BetterSpinner sizeSpin = (BetterSpinner)
                                    x.findViewById(R.id.cat2);
                            sizeSpin.setAdapter(adapter2);
                            sizeSpin.setVisibility(View.VISIBLE);
                            sizeSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String color = sub2[position];
                                    Log.d("yyfyfiy", color);
                                }
                            });






                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                      // Log.d("SIZE OF ADAPTER 2",  String.valueOf(adapter.getItemCount()));

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

    private void getData2(Integer val){
        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("app_key", "88888");
        jsonParams.put("category_id", Integer.toString(val));
        jsonParams.put("is_mobile", "1");


        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST, Constants.API_DOMAINTest+Constants.API_ClassifiedSub,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {

                    LatestCategory   setterGetter;

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
                            JSONArray data = req.getJSONArray("data");
                        sub = new String[data.length()];
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject cat = data.getJSONObject(i);

                                sub[i] = cat.getString("name");


//                                int category_id = Integer.valueOf(cat.getString("category_id"));
//                                setterGetter = new LatestCategory(nameAds,category_id);

//                                latestStr.add(setterGetter);
//                                Log.d("SIZE OF RECYCLERVIEW", String.valueOf(latestStr.size()));
//
//                                adapter.notifyDataSetChanged();
                            }

                            adapter1 = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_dropdown_item_1line, sub);
                            BetterSpinner sizeSpin = (BetterSpinner)
                                    x.findViewById(R.id.cat1);
                            sizeSpin.setAdapter(adapter1);
                            sizeSpin.setVisibility(View.VISIBLE);
                            sizeSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String color = sub[position];
                                    Log.d("yyfyfiy", color);
                                }
                            });







                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                       // Log.d("SIZE OF ADAPTER 2",  String.valueOf(adapter.getItemCount()));

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

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}