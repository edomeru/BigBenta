package ph.dreambig.bigbenta.marketplacev2.LogIn;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import cn.pedant.SweetAlert.SweetAlertDialog;

//import cn.pedant.SweetAlert.SweetAlertDialog;
//import io.realm.Realm;
//import ph.jumpdigital.httpfresh.freshonline.MainActivity;
//import ph.jumpdigital.httpfresh.freshonline.SessionManager.SessionManager;
//import ph.jumpdigital.httpfresh.freshonline.SignUp;
//import ph.jumpdigital.httpfresh.freshonline.UserInformation.UserInformation;


public class FirstPageLogin extends Fragment implements View.OnClickListener  {

    ImageView logo, background;
    SessionManager session;
    TextView LoginAsGuest;
    Button Login;
    GoogleSignInOptions gso;
    SignInButton GPlusSignin;
    CallbackManager callbackManager;
    private GoogleApiClient client;
//    Realm realm;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    TextView ToRegister;
    private static final int RC_SIGN_IN = 9001;
    EditText email,passwordE;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

 //   private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
//        @Override
//        public void onSuccess(LoginResult loginResult) {
//
//            System.out.println("onSuccess");
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setMessage("Fetching Information...");
//            progressDialog.show();
//            String accessToken = loginResult.getAccessToken().getToken();
//            GraphRequest request =  GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                @Override
//                public void onCompleted(JSONObject object, GraphResponse response) {
//                    progressDialog.dismiss();

//                    Bundle UserInfo= getFacebookData(object);
//                    setUserInformation(UserInfo.getString("first_name"),UserInfo.getString("last_name"),UserInfo.getString("email"),UserInfo.getString("profile_pic"));
//                    Intent Main = new Intent(getActivity(),MainActivity.class);
//                    startActivity(Main);
//                    sessionManager = new SessionManager(getActivity());
//                    sessionManager.createLoginSession(UserInfo.getString("first_name"),UserInfo.getString("last_name"));
//                    getActivity().finish();
//                }
//            });
//            Bundle parameters = new Bundle();
//            parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
//            request.setParameters(parameters);
//            request.executeAsync();
  //      }

//        @Override
//        public void onCancel() {
//
//        }

//        @Override
//        public void onError(FacebookException error) {
//
//            Log.d("Facebook",error.toString());
//            Toast.makeText(getActivity(), "There is something wrong.", Toast.LENGTH_SHORT).show();
//        }
 //   };

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getActivity());
//
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
//                .enableAutoManage((FragmentActivity) getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login,container,false);
        //realm = Realm.getDefaultInstance();

        GPlusSignin = (SignInButton) view.findViewById(R.id.gplussignin);
        GPlusSignin.setSize(SignInButton.SIZE_WIDE);
        GPlusSignin.setColorScheme(SignInButton.COLOR_LIGHT);


//        GPlusSignin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signIn();
//            }
//        });

        session = new SessionManager(getActivity());

        email = (EditText)view.findViewById(R.id.email_login);
        passwordE = (EditText)view.findViewById(R.id.password_login);

        CheckLogin();

        Login = (Button)view.findViewById(R.id.login_login);
        LoginAsGuest = (TextView) view.findViewById(R.id.loginasguest);
        ToRegister = (TextView)view.findViewById(R.id.registertv);
        logo = (ImageView) view.findViewById(R.id.logo);
        background = (ImageView) view.findViewById(R.id.background);
        background.setBackgroundColor(Color.WHITE);
        Login.getBackground().setAlpha(128);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });
//        ToRegister.setOnClickListener(this);
//        LoginAsGuest.setOnClickListener(this);


//        Glide.with(this)
//                .load(R.drawable.paulabg)
//                .centerCrop()
//                .into(background);


        Glide.with(this)
                .load(R.drawable.bbmarketplc)

                .into(logo);

        callbackManager = CallbackManager.Factory.create();
        LoginAsGuest.setOnClickListener(this);
        ToRegister.setOnClickListener(this);
        return view;
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);}

    public void setUserInformation(String name, String lastName, String eMail, String profilePicture){
//        UserInformation userInformation = new UserInformation(getActivity());
//        userInformation.putData(name,lastName,eMail,profilePicture);
    }
    private void CheckLogin() {
//        if (session.isLoggedIn() == true) {
//            Intent i = new Intent(getActivity(), MainActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(i);
//            getActivity().finish();
//        }
    }


    public void login() {
        if(validate()){
            Intent login = new Intent(getActivity(),MainActivity.class);
            startActivity(login);
            getActivity().finish();
        }else{
            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error login")
                    .setContentText("Invalid username or password!")
                    .show();
        }
    }

    public boolean validate() {
        boolean valid = true;

        String username = email.getText().toString().trim();
        String password = passwordE.getText().toString().trim() ;
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

//    @Override
//    public void onStart() {
//        super.onStart();
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
//    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        LoginButton loginButton = (LoginButton)view.findViewById(R.id.login_button);
//        loginButton.setFragment(this);
//        loginButton.setReadPermissions("email");
//        loginButton.registerCallback(callbackManager,callback);
  //  }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode,resultCode,data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            Log.d("Google",result.toString());
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(getActivity(), ""+acct.getDisplayName()+" "+acct.getEmail(), Toast.LENGTH_SHORT).show();
        } else {
            Log.d("Else",result.toString());
            Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.registertv:
//              SignUp signUp = new SignUp();
//                FragmentManager manager = getFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                transaction.replace(R.id.firstpage, signUp, "");
//                transaction.addToBackStack(null);
//                transaction.commit();
//                signUp.setID(R.id.firstpage);

                SignUp fragment = new SignUp();
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.firstpage, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

              break;
            case R.id.loginasguest:
//                Intent Main = new Intent(getActivity(),MainActivity.class);
//                startActivity(Main);
//                getActivity().finish();
                login();


            default:
                break;
        }
    }

//    private Bundle getFacebookData(JSONObject object) {
//        Bundle bundle = new Bundle();
//
//        try{
//           String id = object.getString("id");
//            try{
//                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
//                bundle.putString("profile_pic", profile_pic.toString());
//
//                bundle.putString("idFacebook", id);
//                if (object.has("first_name"))
//                    bundle.putString("first_name", object.getString("first_name"));
//                if (object.has("last_name"))
//                    bundle.putString("last_name", object.getString("last_name"));
//                if (object.has("email"))
//                    bundle.putString("email", object.getString("email"));
//                if (object.has("gender"))
//                    bundle.putString("gender", object.getString("gender"));
//                if (object.has("birthday"))
//                    bundle.putString("birthday", object.getString("birthday"));
//                if (object.has("location"))
//                    bundle.putString("location", object.getJSONObject("location").getString("name"));
//            }catch (MalformedURLException e){
//                e.printStackTrace();
//                return null;
//            }
//        }catch (Exception ex){
//
//        }
//        return bundle;
//    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Log.d("onConnectionFailed",""+connectionResult.toString());
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        Log.d("onConnected","onConnected");
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
}
