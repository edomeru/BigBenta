package ph.dreambig.bigbenta.marketplacev2.LogIn;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag.AdsItemModel;
import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.TermsFragment;
import ph.dreambig.bigbenta.marketplacev2.TermsFragmentMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment implements View.OnClickListener {


    public Frag2() {
        // Required empty public constructor
    }
String first,last,ketai,eameru,pass,cp,from;
    Button mSubmit;
Bundle bundle;
    EditText fn, ln, _emailText, confirmemail, _passwordText, _confirmpassword, mobile;
    TextView Condition,terms,createLabel;
    TextInputLayout firstTextInputLayout,LNTextInputLayout,MOBTextInputLayout,emailTextInputLayout,pwTextInputLayout,cpTextInputLayout;
    static int LayoutId;
    private static final String TAG = "LoginActivity";
    SessionManager sessionManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup, container, false);
        mSubmit = (Button) view.findViewById(R.id.submit);
        fn = (EditText) view.findViewById(R.id.fn);
        mobile = (EditText) view.findViewById(R.id.mobile);
        ln = (EditText) view.findViewById(R.id.ln);
        _emailText = (EditText) view.findViewById(R.id.emaill);
        _passwordText = (EditText) view.findViewById(R.id.pw);
        _confirmpassword = (EditText) view.findViewById(R.id.confirmpassword);
        terms= (TextView) view.findViewById(R.id.terms);
        Condition = (TextView) view.findViewById(R.id.condition);
        createLabel= (TextView) view.findViewById(R.id.createLabel);
         firstTextInputLayout =  (TextInputLayout )view.findViewById(R.id.firstTextInputLayout);
        LNTextInputLayout =  (TextInputLayout )view.findViewById(R.id.LNTextInputLayout);
        MOBTextInputLayout=  (TextInputLayout )view.findViewById(R.id.MOBTextInputLayout);
        emailTextInputLayout=  (TextInputLayout )view.findViewById(R.id.emailTextInputLayout);
        pwTextInputLayout=  (TextInputLayout )view.findViewById(R.id.pwTextInputLayout);
        cpTextInputLayout=  (TextInputLayout )view.findViewById(R.id.cpTextInputLayout);

        Condition.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        sessionManager = new SessionManager(getActivity());
        Typeface bariol = Typeface.createFromAsset(getActivity().getAssets(), "Bariol_Regular.otf");


        bundle = getArguments();
        if (bundle != null){

            from = getArguments().getString("from");
        }

        mSubmit.setTypeface(bariol);
        fn.setTypeface(bariol);
        ln.setTypeface(bariol);
        _emailText.setTypeface(bariol);
        _passwordText.setTypeface(bariol);
        _confirmpassword.setTypeface(bariol);
        Condition.setTypeface(bariol);
        firstTextInputLayout.setTypeface(bariol);
        LNTextInputLayout.setTypeface(bariol);
        MOBTextInputLayout.setTypeface(bariol);
        emailTextInputLayout.setTypeface(bariol);
        pwTextInputLayout.setTypeface(bariol);
        cpTextInputLayout.setTypeface(bariol);
        terms.setTypeface(bariol);
        createLabel.setTypeface(bariol);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.submit:
                login();
                break;
            case R.id.condition:
                if(from !=null) {
                    TermsFragmentMain fragment = new TermsFragmentMain();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else{
                    TermsFragment fragment = new TermsFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.firstpage, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
        }
    }

    public static void setID(int Id) {
        LayoutId = Id;
    }

    public int getLayoutId() {
        return LayoutId;
    }

    public void login() {
        first = fn.getText().toString().trim();
        last = ln.getText().toString().trim();
        ketai = mobile.getText().toString().trim();
        eameru = _emailText.getText().toString().trim();
        pass = _passwordText.getText().toString().trim();
        cp = _confirmpassword.getText().toString().trim();

        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("app_key", "88888");
        jsonParams.put("is_mobile", "1");
        jsonParams.put("email", eameru);
        jsonParams.put("password", pass);
        jsonParams.put("password_confirm", cp);
        jsonParams.put("mobile", pass);
        jsonParams.put("first_name", first);
        jsonParams.put("last_name", last);
        jsonParams.put("mobile", ketai);


        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST, Constants.API_DOMAINTest+Constants.API_REG,new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {

                    AdsItemModel setterGetter;

                    @Override
                    public void onResponse(JSONObject req) {
                        System.out.println(req);
                        String messege = "";
                        int code;
                        String pass= "";
                        String emailll = "";
                        String password_confirm = "";
                        String first_name="";
                        String message="";
                        String user_id="";
                        String last_name="";
                        String mobilee="";
                        JSONObject data = null;
                        JSONArray strs = null;
                        try {

                            messege = req.getString("message");
                            code = req.getInt("code");
                            data = req.optJSONObject("data");
                            if ( data!=null) {

                                if (data.has("password")) {
                                    pass = data.optString("password");
                                }
                                if (data.has("email")) {
                                    emailll = data.optString("email");
                                }
                                if (data.has("password_confirm")) {
                                    password_confirm = data.optString("password_confirm");
                                }
                                if (data.has("first_name")) {
                                    first_name = data.optString("first_name");
                                }
                                if (data.has("message")) {
                                    message = data.optString("message");
                                }
                                if (data.has("user_id")) {
                                    user_id = data.optString("user_id");
                                }

                                if (data.has("last_name")) {
                                    last_name = data.optString("last_name");
                                }
                                if (data.has("mobile")) {
                                    mobilee = data.optString("mobile");
                                }

                            }




                            if(code==200){
                                goToActivation();
                            }else if(code==422){

                                if(emailll.equals(" should be email format")){
                                    emailTextInputLayout.setError(emailll);
                                }else if(emailll.equals("Email is required")){
                                    emailTextInputLayout.setError(emailll);
                                }else if(emailll.equals(" should be minimum of 4 characters")){
                                    emailTextInputLayout.setError(emailll);
                                }else{
                                    emailTextInputLayout.setError(null);
                                }


                                if(pass.equals(" should be maximum of 15 characters")){
                                    pwTextInputLayout.setError(pass);
                                }else if(pass.equals(" should be minimum of 7 characters")){
                                    pwTextInputLayout.setError(pass);
                                }else if(pass.equals("Password is required")){
                                    pwTextInputLayout.setError(pass);
                                }else{
                                    pwTextInputLayout.setError(null);
                                }


                                if(password_confirm.equals("Password Confirmation not match")){
                                    cpTextInputLayout.setError(password_confirm);
                                }else if(password_confirm.equals(" should be minimum of 7 characters")){
                                    cpTextInputLayout.setError(password_confirm);
                                }else if(password_confirm.equals("Confirmation of password is required")){
                                    cpTextInputLayout.setError(password_confirm);
                                }else{
                                    cpTextInputLayout.setError(null);
                                }

                                if(first_name.equals(" should be minimum of 2 characters")){
                                    firstTextInputLayout.setError(first_name);
                                }else if(first_name.equals("First name is required")){
                                    firstTextInputLayout.setError(first_name);
                                }else{
                                    firstTextInputLayout.setError(null);
                                }


                                if(last_name.equals(" should be minimum of 2 characters")){
                                    LNTextInputLayout.setError(last_name);
                                }else if(last_name.equals("Last name is required")){
                                    LNTextInputLayout.setError(last_name);
                                }else{
                                    LNTextInputLayout.setError(null);
                                }


                                if(mobilee.equals(" should be minimum of 3 characters")){
                                    MOBTextInputLayout.setError(mobilee);
                                }else if(mobilee.equals(" should be maximum of 11 characters")){
                                    MOBTextInputLayout.setError(mobilee);
                                }else if(mobilee.equals("Mobile is required")){
                                    MOBTextInputLayout.setError(mobilee);
                                }else{
                                    MOBTextInputLayout.setError(null);
                                }

                            }else if(code==409){

                                if(emailll.equals("Email is already registered but not yet activated, please check your email to activate your account")){
                                    emailTextInputLayout.setError(emailll);
                                }else{
                                    emailTextInputLayout.setError(null);
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

//    public void onLoginSuccess() {
////
//        Intent intent = new Intent(getActivity(), MainActivity.class);
//        startActivity(intent);
//    }
//
//    public void onLoginFailed() {
//        Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_LONG).show();
//
//        mSubmit.setEnabled(true);
//    }

//    public boolean validate() {
//        boolean valid = true;
//
//        String email = _emailText.getText().toString();
//        String password = _passwordText.getText().toString();
//        String cpassword = _confirmpassword.getText().toString();
//        String lname = ln.getText().toString();
//        String fname = fn.getText().toString();
//        String mname = mobile.getText().toString();
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            _emailText.setError("Enter a valid email address");
//            valid = false;
//        } else {
//            _emailText.setError(null);
//        }
//        if (mname.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mname).matches()) {
//            mobile.setError("Enter valid middle name");
//            valid = false;
//        } else {
//            mobile.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            _passwordText.setError("Invalid password");
//            valid = false;
//        } else {
//            _passwordText.setError(null);
//        }
//
//        if (cpassword.isEmpty() || cpassword.length() < 4 || cpassword.length() > 10) {
//            _confirmpassword.setError("Invalid password");
//            valid = false;
//        } else if (!cpassword.equals(password)) {
//            _confirmpassword.setError("Invalid password");
//            valid = false;
//        } else {
//            _confirmpassword.setError(null);
//        }
//        if (lname.isEmpty()) {
//            ln.setError("Last name field is required");
//            valid = false;
//        } else {
//            ln.setError(null);
//        }
//        if (fname.isEmpty()) {
//            fn.setError("First name field is required");
//            valid = false;
//        } else {
//            fn.setError(null);
//        }
//        return valid;
//    }

    private void goToActivation(){
        Activation fr = new Activation();
        FragmentManager fm= getActivity().getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.firstpage, fr);
        ft.commit();


    }
}