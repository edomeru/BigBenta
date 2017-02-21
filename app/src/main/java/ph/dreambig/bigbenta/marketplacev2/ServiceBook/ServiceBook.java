package ph.dreambig.bigbenta.marketplacev2.ServiceBook;



import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.BaseApplication;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.TabsViewpager.ServicesTabFragment;
import ph.dreambig.bigbenta.marketplacev2.Home.Service.Categories_Service.MyBookings.BookingListData;
import ph.dreambig.bigbenta.marketplacev2.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;


public class ServiceBook extends Fragment implements DatePickerDialog.OnDateSetListener {
    Realm realm;
    View x;
    JSONObject e;
    String duration;
    String locate;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    BookingListData addbook;
    EditText DeliveryDate;
    Spinner DeliveryDateSpinner;
    Button orderReview;
    Object spinnerValue;
    String durationl;
    String srvce_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        x = inflater.inflate(R.layout.shipping_method,container,false);


        Bundle bundle=getArguments();

        if(bundle !=null)
            if(bundle.containsKey("srvce_id")){

                srvce_id = getArguments().getString("srvce_id");

            }

        DeliveryDate = (EditText)x.findViewById(R.id.deliverydate);
//        DeliveryDateSpinner = (Spinner)x.findViewById(R.id.deliverydatespinner);
        orderReview = (Button)x.findViewById(R.id.imageButton);

        /////////////////START

        Log.d("SERVICE_ID", srvce_id);
        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.GET, Constants.API_SERVICETESTDOMAIN+Constants.API_SINGLE_VIEW+srvce_id,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject req) {
                        System.out.println(req);

                        String rrate = "";
                        int code;
                        String srvc_id = "";
                        String sm_name = "";
                        String cod = "";
                        String sm_extension = "";
                        String sm_domain = "";
                        String sm_group = "";
                        String subcatitem_name = "";
                        String subcat_name = "";
                        String business_name = "";
                        String last_name = "";
                        int ratei;
                        String rate_name = "";
                        String first_name = "";
                        JSONArray strs = null;
                        JSONObject json = null;

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
                            Log.d("subcatitem_name", subcatitem_name + subcat_name);
                            rrate = jsonk.getString("rate");//PRICE
                            first_name = jsonk.getString("first_name");
                            last_name = jsonk.getString("last_name");
                            rate_name = jsonk.getString("rate_name");
                            ratei = jsonk.getInt("rate");
                            JSONArray rates_list = jsonk.getJSONArray("rates_list");
                            JSONArray schedule_list = jsonk.optJSONArray("schedule");


                            sm_domain = jsonk.getString("sm_domain");
                            sm_group = jsonk.getString("sm_group");
                            sm_name = jsonk.getString("sm_name");
                            sm_extension = jsonk.getString("sm_extension");


                            if (schedule_list != null) {
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
//                                    datesched.setText(v);
//                                    datesched.setVisibility(View.VISIBLE);
                                }
                            }


                            final String[] lugar = new String[rates_list.length()];
                            for ( int i = 0; i < rates_list.length(); i++) {
                                 e = rates_list.getJSONObject(i);

                                if(e.optString("type").equals("SE") && e.optString("type").equals("HS")){
                                    lugar[i]= "Service Provider's Establishment, Customer Premises/Home Service";
                                } else if(e.optString("type").equals("SE")){
    lugar[i]="Service Provider's Establishment";
}else if(e.optString("type").equals("HS")){
    lugar[i]="Customer Premises/Home Service";
}
                               // lugar[i] = e.optString("type");
                                adapter = new ArrayAdapter<String>(getActivity(),
                                        android.R.layout.simple_dropdown_item_1line, lugar);
                                BetterSpinner colorSpin = (BetterSpinner) x.findViewById(R.id.loc);
                                colorSpin.setAdapter(adapter);
                                colorSpin.setVisibility(View.VISIBLE);
                                colorSpin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        locate = lugar[position];
                                        Log.d("LOCATION", locate);
                                        durationfuc(locate);

                                    }
                                });


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











        ArrayAdapter deliveryadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.delivery_date, android.R.layout.simple_spinner_dropdown_item);
//        DeliveryDateSpinner.setAdapter(deliveryadapter);
//        DeliveryDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//                 spinnerValue = parent.getItemAtPosition(pos);
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
        realm = Realm.getDefaultInstance();
        DeliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        ServiceBook.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAccentColor(getResources().getColor(R.color.mediumBlue));
               dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");

            }
        });

        orderReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          // MainActivity.setFragment("Order Review");
               // addBooklist(name,price, pic,  DeliveryDate.getText().toString(),spinnerValue.toString());

                ServicesTabFragment fr=new ServicesTabFragment();
                FragmentManager fm= getActivity().getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Bundle args = new Bundle();
                args.putString("position", "2");
                fr.setArguments(args);
                ft.replace(R.id.containerView, fr);
                ft.commit();


                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Book Requested!")
                        .setContentText(" You have requested to book service from "+srvce_id)
                        .show();
            }
        });


        return x;
    }


    public void durationfuc(final String durationn){


        /////////////////START


        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.GET, Constants.API_SERVICETESTDOMAIN+Constants.API_SINGLE_VIEW+srvce_id,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject req) {
                        System.out.println(req);

                        String rrate = "";
                        int code;
                        String srvc_id = "";
                        String sm_name = "";
                        String cod = "";
                        String sm_extension = "";
                        String sm_domain = "";
                        String sm_group = "";
                        String subcatitem_name = "";
                        String subcat_name = "";
                        String business_name = "";
                        String last_name = "";
                        int ratei;
                        String rate_name = "";
                        String first_name = "";
                        JSONArray strs = null;
                        JSONObject json = null;

                        try {
                            Log.d("REQ_SERVICE", req.toString());


                            code = req.getInt("code");
                            JSONObject jsonk = req.getJSONObject("data");

                            Log.d("JARRAY", jsonk.toString());
//                            for (int a = 0; a < data.length(); a++) {
                            // json = data.getJSONObject(a);


                            rate_name = jsonk.getString("rate_name");
                            ratei = jsonk.getInt("rate");
                            JSONArray rates_listt = jsonk.getJSONArray("rates_list");


                           final  String[] SEdesu = new String[rates_listt.length()];

                            for (int m = 0; m < rates_listt.length(); m++) {
                                JSONObject l = rates_listt.optJSONObject(m);

                                if (durationn.equals("Service Provider's Establishment") && l.optString("type").equals("SE")) {
                                    l.optInt("call_rate");
                                    l.optInt("hour_rate");
                                    l.optInt("hday_rate");
                                    l.optInt("fday_rate");

                                    SEdesu[m] = String.valueOf(l.optInt("call_rate")) + "," + String.valueOf(l.optInt("hour_rate")) + "," + String.valueOf(l.optInt("hday_rate") )+ "," + String.valueOf(l.optInt("fday_rate"));

                                }

                              else  if (durationn.equals("Customer Premises/Home Service") && l.optString("type").equals("HS")){
                                    l.optInt("call_rate");
                                    l.optInt("hour_rate");
                                    l.optInt("hday_rate");
                                    l.optInt("fday_rate");

                                    SEdesu[m] = String.valueOf(l.optInt("call_rate")) + "," + String.valueOf(l.optInt("hour_rate")) + "," + String.valueOf(l.optInt("hday_rate") )+ "," + String.valueOf(l.optInt("fday_rate"));
                                }

                            }
                            Log.d("DURATIONNNNNNN", Arrays.toString(SEdesu));
                            Log.d("loccccccc", durationn);
                            adapter2 = new ArrayAdapter<String>(getActivity(),
                                    android.R.layout.simple_dropdown_item_1line, SEdesu);
                            BetterSpinner Spin = (BetterSpinner) x.findViewById(R.id.deliverydatespinner);
                            Spin.setAdapter(adapter2);
                            Spin.setVisibility(View.VISIBLE);
                            Spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    durationl = SEdesu[position];
                                    Log.d("duration", durationl);
                                }
                            });

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


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"-"+(monthOfYear+1)+"-"+year;
       DeliveryDate.setText(date);
    }

    private void addBooklist(final String name, final int Price,final String pic,final String DateBooked,final String duration){
        addbook = new BookingListData();
        addbook.setId(getNextKey());
        addbook.setSPName(name);
        addbook.setSPStatus("ACCEPTED");
        addbook.setAmount(Price);
        addbook.setImageUrl(pic);
        addbook.setSPDate("9/25/16");
        addbook.setDateBooked(DateBooked);
        addbook.setDuration(duration);
        addbook.setRefNo("RQB-78-08");
        addbook.setSPAddress("114 Neptune St. Guada Subd., Bayan Luma 6, Imus, Cavite");
        addbook.setPaymentStatus("Pending");

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(addbook);
        realm.commitTransaction();
        new FetchCountTask(BaseApplication.ServiceItemsCount+1 ).execute();
    }

    public int getNextKey() {

        try {
            return realm.where(BookingListData.class).max("id").intValue() + 1;
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override public void onDestroy(){
        super.onDestroy();
        realm.close();

    }
    @Override
    public void onStop() {
        super.onStop();
    }
    private void updateNotificationsBadge(int count) {
        BaseApplication.ServiceItemsCount = count;
        getActivity().invalidateOptionsMenu();
    }
    public class FetchCountTask extends AsyncTask<Void, Integer, Integer> {

        int ServiceCount;
        public FetchCountTask(int i) {
            super();
            ServiceCount = i;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            return ServiceCount;
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }
}
