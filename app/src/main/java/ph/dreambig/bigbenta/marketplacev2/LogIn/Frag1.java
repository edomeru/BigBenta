package ph.dreambig.bigbenta.marketplacev2.LogIn;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.AdsItemsFrag.AdsItemModel;
import ph.dreambig.bigbenta.marketplacev2.AppController;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Facebook.FacebookRequest;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Facebook.FacebookResponse;
import ph.dreambig.bigbenta.marketplacev2.Profile.ProfileRequest;
import ph.dreambig.bigbenta.marketplacev2.Profile.ProfileResponse;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import ph.dreambig.bigbenta.marketplacev2.UserInformation;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {
    LoginButton loginButton;
     String accessTokenn;
    UserInformation userInformation;
     String fb_user_id;
    Typeface bariol;
    TextView textView;
    ImageView logo, background;
    String access_token = "",from;
    TextView LoginAsGuest;
    Button Login;
    String user_id = "";
    private String username;
    private String password;
    API_Retrofit service,services;
    GoogleSignInOptions gso;
    SignInButton GPlusSignin;
    CallbackManager callbackManager;
    private GoogleApiClient client;
    //    Realm realm;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    TextView label;
    TextInputLayout unTLayout;
    TextInputLayout pwTLayout;
    String loc = "";
    private static final int RC_SIGN_IN = 9001;
    EditText email, passwordE;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    Bundle bundle;

    public Frag1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userInformation = new UserInformation(getActivity());
        View view = inflater.inflate(R.layout.fragment_frag1, container, false);
        LoginManager.getInstance().logOut();
//        GPlusSignin = (SignInButton) view.findViewById(R.id.gplussignin);
//        GPlusSignin.setSize(SignInButton.SIZE_WIDE);
//        GPlusSignin.setColorScheme(SignInButton.COLOR_LIGHT);
        bundle = getArguments();
        if (bundle != null) {
            String position = getArguments().getString("position");
            from = getArguments().getString("from");
        }

        if (from == null){
            ((FirstPage) getActivity()).setTextTitle("LogIn");
    }else{
            ((MainActivity) getActivity()).setTextTitle("LogIn");
        }

        sessionManager = new SessionManager(getActivity());
        label = (TextView) view.findViewById(R.id.label);
        email = (EditText) view.findViewById(R.id.email_login);
        passwordE = (EditText) view.findViewById(R.id.password_login);
        passwordE.setTransformationMethod(new PasswordTransformationMethod());
        unTLayout = (TextInputLayout) view.findViewById(R.id.un);
        pwTLayout = (TextInputLayout) view.findViewById(R.id.pw);
        //CheckLogin();

        textView = (TextView) view.findViewById(R.id.textView);
        Login = (Button) view.findViewById(R.id.login_login);





         bariol = Typeface.createFromAsset(getActivity().getAssets(), "Bariol_Regular.otf");
        label.setTypeface(bariol);
        Login.setTypeface(bariol);
        passwordE.setTypeface(bariol);
        email.setTypeface(bariol);
        Bundle bundle = getArguments();



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("ONCLICK", "");



                login();
            }
        });


//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                System.out.println("onSuccess");
//                LoginAsGuest.setText("Login Success \n"+
//                        loginResult.getAccessToken().getUserId()
//                +"\n"+loginResult.getAccessToken().getToken());
//
//                Toast.makeText(getActivity(), "Login Success",
//                        Toast.LENGTH_LONG).show();
//                Log.d("Login Success","Login Success");
//
//            }
//
//            @Override
//            public void onCancel() {
//                LoginAsGuest.setText("Login Cancelled");
//                Toast.makeText(getActivity(), "Login Cancelled",
//                        Toast.LENGTH_LONG).show();
//                Log.d("Login Cancelled","Login Cancelled");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                LoginAsGuest.setText("ERROR"+error.toString());
//                Toast.makeText(getActivity(), "ERROR",
//                        Toast.LENGTH_LONG).show();
//                Log.d("Login ERROR","Login ERROR");
//            }
//
//        });

        return view;
    }


//    private void CheckLogin() {
//        if (sessionManager.isLoggedIn() == true) {
//
//            Log.d("CheckLogin IF"," ");
//
//            Intent myIntent = new Intent(getActivity(), MainActivity.class);
//            myIntent.putExtra("pos","store");
//            startActivity(myIntent);
//
//            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
//                    .setTitleText("You are Logged In Already!")
//                    .setContentText("Enjoy BigBenta Services")
//                    .show();
//
//            getActivity().finish();
//
//        }
//
//        Log.d("CheckLogin ELSE"," ");
//    }

    public void login() {

        username = email.getText().toString().trim();
        password = passwordE.getText().toString().trim();

      final  Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("app_key", "88888");
        jsonParams.put("is_mobile", "1");
        jsonParams.put("email", username);
        jsonParams.put("password", password);

        JsonObjectRequest jsObjReques = new JsonObjectRequest(Request.Method.POST, Constants.API_DOMAINTest + Constants.API_LOGIN, new JSONObject(jsonParams),
                new Response.Listener<JSONObject>() {

                    AdsItemModel setterGetter;

                    @Override
                    public void onResponse(JSONObject req) {
                        System.out.println(req);
                        String messege = "";
                        int code;
                        String pass = "";
                        String emaill = "";


                        String types = "";
                        JSONObject data = null;
                        JSONArray strs = null;
                        try {




                            messege = req.getString("message");
                            code = req.getInt("code");
                            data = req.optJSONObject("data");

                            Log.d("DATA_LOGIN", data.toString());
                            Log.d("PARAM_LOGIN", jsonParams.toString());


                            if (data != null) {




                                if (data.has("password")) {
                                    pass = data.optString("password");
                                }
                                if (data.has("email")) {
                                    emaill = data.optString("email");
                                }
                                if (data.has("user_id")) {
                                    user_id = data.optString("user_id");
                                }
                                if (data.has("access_token")) {
                                    access_token = data.optString("access_token");
                                }
                                if (data.has("types")) {
                                    types = data.optString("types");
                                }


                            }


                            if (code == 200) {


                                /////START GET PROFILE
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Constants.API_DOMAINTest)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                services = retrofit.create(API_Retrofit.class);
                                ProfileRequest serviceRequest = new ProfileRequest();
                                serviceRequest.setApp_key("88888");
                                serviceRequest.setUser_id(user_id);
                                serviceRequest.setIs_mobile("1");
                                serviceRequest.setToken(access_token);

                                Call<ProfileResponse> serviceResponseCall =  services.getProfileInfo(serviceRequest);
                                Log.d("USER_IS", user_id);
                                Log.d("USER_TOKEN", access_token);
                                Log.d("API_LOGIN", Constants.API_LOGIN);
                                serviceResponseCall.enqueue(new Callback<ProfileResponse>() {
                                    @Override
                                    public void onResponse(Call<ProfileResponse> call, retrofit2.Response<ProfileResponse> response) {
                                        int statusCode = response.code();


                                        ProfileResponse  serviceResponsee = response.body();

                                        Log.d("STATUS CODE", String.valueOf(statusCode));

                                        Log.d("RESPONSE_BODY",  response.body().toString());
                                        Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());

                                        for(int i=0;i< serviceResponsee.getData().size();i++) {

                                            Log.d("SPICTURE_SM",  serviceResponsee.getData().get(0).getImageSm().toString());
                                            Log.d("FIRST_NAME",  serviceResponsee.getData().get(0).getFirstName().toString());
                                            Log.d("LAST_NAME",  serviceResponsee.getData().get(0).getLastName().toString());
                                            Log.d("EMAIL",  serviceResponsee.getData().get(0).getEmail().toString());
                                            Log.d("ACESSTOKEN", access_token);
                                            Log.d("USER_ID", user_id);
                                            setUserInformation(serviceResponsee.getData().get(0).getFirstName().toString(), serviceResponsee.getData().get(0).getLastName().toString(), serviceResponsee.getData().get(0).getEmail().toString(), serviceResponsee.getData().get(0).getImageSm().toString());


                                        }
                                        openProfile(access_token, user_id);








                                    }


                                    @Override
                                    public void onFailure(Call<ProfileResponse> call, Throwable t) {

                                    }
                                });

                                /////END  GET PROFILE



                            } else if (code == 404) {

                                if (messege.equals("Invalid Credential, if you forgot your password please recover your account using your email")) {
                                    unTLayout.setError("Invalid Credential, if you forgot your password please recover your account using your email");
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Error")
                                            .setContentText(messege)
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
//                            MainActivity.popFragment("Shopping Cart");
//                            MainActivity.setFragment("Visit Us");
                                                    sDialog.dismissWithAnimation();
                                                }
                                            })
                                            .show();
                                } else {
                                    unTLayout.setError(null);
                                }
                                if (messege.equals("Invalid Credential")) {
                                    pwTLayout.setError("Invalid Credential");
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Error")
                                            .setContentText(messege)
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
//                            MainActivity.popFragment("Shopping Cart");
//                            MainActivity.setFragment("Visit Us");
                                                    sDialog.dismissWithAnimation();
                                                }
                                            })
                                            .show();
                                } else {
                                    pwTLayout.setError(null);
                                }


                            } else if (code == 422) {

                                if (emaill.equals("Username is required")) {
                                    unTLayout.setError(emaill);
                                } else {
                                    unTLayout.setError(null);
                                }
                                if (pass.equals("Password is required")) {
                                    pwTLayout.setError(pass);
                                } else {
                                    pwTLayout.setError(null);
                                }

                            }else if(code ==504){

                                if (messege.equals("Invalid Credential, if you forgot your password please recover your account using your email")) {
                                    unTLayout.setError("Invalid Credential, if you forgot your password please recover your account using your email");
                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("Error")
                                            .setContentText(messege)
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
//                            MainActivity.popFragment("Shopping Cart");
//                            MainActivity.setFragment("Visit Us");
                                                    sDialog.dismissWithAnimation();
                                                }
                                            })
                                            .show();
                                } else {
                                    unTLayout.setError(null);
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
//        if(validate()){
//
//            Intent i = new Intent(getActivity(), MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
//            getActivity().finish();
//            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
//                    .setTitleText("Logged In!")
//                    .setContentText("Enjoy BigBenta Services")
//                    .show();
//        }else{
//            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
//                    .setTitleText("Error login")
//                    .setContentText("Invalid username or password!")
//                    .show();
//        }
    }

    public boolean validate() {
        boolean valid = true;

        String username = email.getText().toString().trim();
        String password = passwordE.getText().toString().trim();
        if (username.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            email.setError("Enter a valid email address");
            valid = false;
        } else {
            email.setError(null);

        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordE.setError("Enter a valid password");
            valid = false;
        } else {
            passwordE.setError(null);
        }

        return valid;
    }

    private void openProfile(String token, String userid) {
//        StoreTabFragment fr = new StoreTabFragment();
//        FragmentManager fm= getActivity().getSupportFragmentManager();
//        FragmentTransaction ft=fm.beginTransaction();
//        Bundle args = new Bundle();
//        args.putString("pos", "1");
//        fr.setArguments(args);
//        ft.replace(R.id.containerView, fr);
//        ft.commit();
        if (loc.equals("backToPrevFrag")) {
            getFragmentManager().popBackStackImmediate();


        } else {
            sessionManager.createLoginSession(token, userid);
            Intent i = new Intent(getActivity(), MainActivity.class);
            i.putExtra("pos", "store");
            getActivity().finish();
            startActivity(i);


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d("requestCode", String.valueOf(requestCode));
        Log.d("resultCode", String.valueOf(resultCode));
        Log.d("resultCode", String.valueOf(data));

//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            Log.d("Google", result.toString());
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(getActivity(), "" + acct.getDisplayName() + " " + acct.getEmail(), Toast.LENGTH_SHORT).show();
        } else {
            Log.d("Else", result.toString());
            Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginButton.registerCallback(callbackManager, mCallback);
        loginButton.setTypeface(bariol);
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {

        @Override
        public void onSuccess(LoginResult loginResult) {

            Log.d("loginResult SUCCESSn", loginResult.getAccessToken().getPermissions().toString());

            System.out.println("onSuccess");
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Fetching Information...");
            progressDialog.show();
           accessTokenn = loginResult.getAccessToken().getToken().toString();
             fb_user_id = loginResult.getAccessToken().getUserId().toString();
            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    progressDialog.dismiss();

                    Bundle UserInfo = getFacebookData(object);
                    Object UserInfo2 =(Object) getFacebookData(object);
                    Log.d("USERRRRRRRRR", UserInfo.toString());
                    Log.d("FB_TOKEN", accessTokenn.toString());

//                Intent i = new Intent(getActivity(), MainActivity.class);
//                i.putExtra("pos","store");
//                getActivity().finish();
//                startActivity(i);
                    String[] user = new String[1];
                    String[] e = {"first_name","id","email","last_name"};
                    for(int k=0;k<=0;k++) {
//                        user[k]=  UserInfo.getString(e[k]);
                        user[k]= object.toString();
                    }
                    JSONArray mJSONArray = new JSONArray();
                    mJSONArray.put(object);
                    Object objectpo =(Object) object;
                   // objectList.addAll(Arrays.asList(user));
                    Log.d("ARRAY_dfada", Arrays.toString(user));
                    Log.d("ARRAY_JSONARRAY",objectpo.toString() );
                    Log.d("FB_ID",UserInfo.getString("id"));
                    checkFBcredentialsExist( UserInfo.getString("email"), UserInfo.getString("id"), UserInfo.getString("gender"),UserInfo.getString("first_name"),UserInfo.getString("last_name"),UserInfo.getString("profile_pic"));
                    //setUserInformation(UserInfo.getString("first_name"), UserInfo.getString("last_name"), UserInfo.getString("email"), UserInfo.getString("profile_pic"));

                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
            request.setParameters(parameters);
            request.executeAsync();
            Toast.makeText(getActivity(), "Login SUCCESSFULL", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "Login Failed onCancel" + mCallback.toString(), Toast.LENGTH_LONG).show();
            Log.d("CANCEL_LOG", mCallback.getClass().getName().toString());

        }

        @Override
        public void onError(FacebookException error) {
            Toast.makeText(getActivity(), "Login Failed FB" + error.toString(), Toast.LENGTH_LONG).show();

        }
    };

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();


        try {
            String id = object.getString("id");
            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                bundle.putString("profile_pic", profile_pic.toString());
                bundle.putString("id", id);
                if (object.has("first_name"))
                    bundle.putString("first_name", object.getString("first_name"));
                if (object.has("last_name"))
                    bundle.putString("last_name", object.getString("last_name"));
                if (object.has("email"))
                    bundle.putString("email", object.getString("email"));
                if (object.has("gender"))
                    bundle.putString("gender", object.getString("gender"));
//                if (object.has("birthday"))
//                    bundle.putString("birthday", object.getString("birthday"));
//                if (object.has("location"))
//                    bundle.putString("location", object.getJSONObject("location").getString("name"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception ex) {

        }
        return bundle;
    }

    public void setUserInformation(String name, String lastName, String eMail, String profilePicture) {

        userInformation.putData(name, lastName, eMail, profilePicture);
    }


    /////START
    public void checkFBcredentialsExist(final String email,final String fbId,final  String gender,final String first, final String last,final String Prof_pic) {


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.API_DOMAINTest)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    service =retrofit.create(API_Retrofit.class);
    FacebookRequest serviceRequest = new FacebookRequest();
    serviceRequest.setApp_key("88888");
    serviceRequest.setEmail(email);
    serviceRequest.setIs_mobile("1");
    serviceRequest.setPassword("facebook");
        serviceRequest.setFbid(fbId);
        serviceRequest.setFirst_name(first);
        serviceRequest.setLast_name(last);
        serviceRequest.setGender(gender);

    Call<FacebookResponse> serviceResponseCall = service.getFBLogin(serviceRequest);

//jjj
    serviceResponseCall.enqueue(new Callback<FacebookResponse>()

    {
        @Override
        public void onResponse
        (Call < FacebookResponse > call, retrofit2.Response < FacebookResponse > response){
        int statusCode = response.code();


            FacebookResponse serviceResponsee = response.body();
            Log.d("GENDER", gender);
            Log.d("FIRST_FB",first);
            Log.d("LAST_FB",last);
            Log.d("PROF_PIC_FB",Prof_pic);
       // Log.d("STATUS CODE_FB", String.valueOf(serviceResponsee.getCode()));
          //  Log.d("CODE", serviceResponsee.getCode().toString());
            Log.d("TOKEN_FB", accessTokenn);
            Log.d("USER_ID_FB",fb_user_id);
            Log.d("TOKEN_FB2", serviceResponsee.getData().getAccessToken().toString());
            Log.d("USER_ID_FB2",serviceResponsee.getData().getUserId().toString());



           if(serviceResponsee.getCode()==200) {
               //setUserInformation(first, last, email, Prof_pic);

               Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl(Constants.API_DOMAINTest)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();

               services = retrofit.create(API_Retrofit.class);
               ProfileRequest serviceRequest = new ProfileRequest();
               serviceRequest.setApp_key("88888");
               serviceRequest.setUser_id(serviceResponsee.getData().getUserId().toString());
               serviceRequest.setIs_mobile("1");
               serviceRequest.setToken(serviceResponsee.getData().getAccessToken().toString());

               Call<ProfileResponse> serviceResponseCall =  services.getProfileInfo(serviceRequest);
               Log.d("USER_IS", user_id);
               Log.d("USER_TOKEN", access_token);

               serviceResponseCall.enqueue(new Callback<ProfileResponse>() {
                   @Override
                   public void onResponse(Call<ProfileResponse> call, retrofit2.Response<ProfileResponse> response) {
                       int statusCode = response.code();


                       ProfileResponse  serviceResponsee = response.body();

                       Log.d("STATUS CODE", String.valueOf(statusCode));

                       Log.d("RESPONSE_BODY",  response.body().toString());
                       Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                      // UserInformation userInformation = new UserInformation(getActivity());
                       for(int i=0;i< serviceResponsee.getData().size();i++) {

                           Log.d("SPICTURE_SM",  serviceResponsee.getData().get(0).getImageSm().toString());
                           Log.d("FIRST_NAME",  serviceResponsee.getData().get(0).getFirstName().toString());
                           Log.d("LAST_NAME",  serviceResponsee.getData().get(0).getLastName().toString());
                           Log.d("EMAIL",  serviceResponsee.getData().get(0).getEmail().toString());
                           Log.d("PROF_PIC_FB",  Prof_pic);
                           Log.d("ACESSTOKEN", access_token);
                           Log.d("USER_ID", user_id);
                           setUserInformation(serviceResponsee.getData().get(0).getFirstName().toString(), serviceResponsee.getData().get(0).getLastName().toString(), serviceResponsee.getData().get(0).getEmail().toString(), Prof_pic);


//                           userInformation.setFirstName(serviceResponsee.getData().get(0).getFirstName().toString());
//                           userInformation.setLastName(serviceResponsee.getData().get(0).getLastName().toString());
//                           userInformation.setEmail(serviceResponsee.getData().get(0).getEmail().toString());
//                           userInformation.setProfilePicture(Prof_pic);
                       }









                   }


                   @Override
                   public void onFailure(Call<ProfileResponse> call, Throwable t) {

                   }
               });







               openProfile(serviceResponsee.getData().getAccessToken().toString(),serviceResponsee.getData().getUserId().toString());

           }

        }





        @Override
        public void onFailure (Call < FacebookResponse > call, Throwable t){

    }
    }

    );

}
    ///////END



}
