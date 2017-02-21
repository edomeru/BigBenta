package ph.dreambig.bigbenta.marketplacev2.LogIn;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag.AdsItemModel;
import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Activation extends Fragment  {
    String user_id="";

    public Activation() {
        // Required empty public constructor
    }

    EditText actCode;
    TextInputLayout codeTIL;
    Button actBut;
    String ActCode;
    SessionManager sessionManager;
    String access_token = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.activation, container, false);

        actBut = (Button) x.findViewById(R.id.actBut);
        actCode = (EditText) x.findViewById(R.id.actCode);
        codeTIL =  (TextInputLayout )x.findViewById(R.id.code);

        //sessionManager = new SessionManager(getApplicationContext());
        actBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitCode();
            }
        });
        return x;
    }



    public void submitCode() {
        ActCode = actCode.getText().toString().trim();


        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("app_key", "88888");
        jsonParams.put("is_mobile", "1");
        jsonParams.put("code", ActCode);


        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST, Constants.API_DOMAINTest+Constants.API_ACTIVATION,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {

                    AdsItemModel setterGetter;

                    @Override
                    public void onResponse(JSONObject req) {
                        System.out.println(req);
                        String messege = "";
                        int code;
                        String codee  = "";

                        String first_name="";
                        String message="";

                        String types="";
                        JSONObject data = null;
                        JSONArray strs = null;
                        try {

                            messege = req.getString("message");
                            code = req.getInt("code");
                            data = req.optJSONObject("data");
                            if ( data!=null) {


                                if (data.has("user_id")) {
                                    user_id = data.optString("user_id");
                                }

                                if (data.has("access_token")) {
                                    access_token = data.optString("access_token");
                                }
                                if (data.has("types")) {
                                    types = data.optString("types");
                                }
                                if (data.has("code")) {
                                    codee = data.optString("code");
                                }

                            }




                            if(code==200){
                                goToHome();
                            }else if(code==404){

                                if(messege.equals("Error")){
                                    codeTIL.setError("Incorrect Code");
                                }else{
                                    codeTIL.setError(null);
                                }
                            }else if(code==422){

                                if(codee.equals(" should be minimum of 4 characters")){
                                    codeTIL.setError(codee);
                                }else{
                                    codeTIL.setError(null);
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

    private void goToHome(){
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.putExtra("pos","store");
        getActivity().finish();
        startActivity(i);

        sessionManager.createLoginSession(access_token,user_id);
    }
}
