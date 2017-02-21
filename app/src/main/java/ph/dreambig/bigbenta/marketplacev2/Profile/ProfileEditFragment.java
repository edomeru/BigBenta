package ph.dreambig.bigbenta.marketplacev2.Profile;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import ph.dreambig.bigbenta.marketplacev2.API_Retrofit;
import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;
import com.google.gson.JsonSyntaxException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import ph.dreambig.bigbenta.marketplacev2.UserInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileEditFragment extends Fragment {
    EditText first,last,eadd,mobile,confirmpass,newpass,curentpass,phone,city_name,region_name,country_name,Barangay,Unitt,Street,zip;
    CheckBox cb;
    SessionManager sessionManager;
    API_Retrofit service,serviceE;
    String f,l,e,m,id,getfirst,getlast,geteadd,getmobile;
CircleImageView imageProfile;

    public ProfileEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_edit, container, false);
        setHasOptionsMenu(true);
        Typeface bariol = Typeface.createFromAsset(getContext().getAssets(), "Bariol_Regular.otf");
//        zip = (EditText) view.findViewById(R.id.zip);
//        Street = (EditText) view.findViewById(R.id.Street);
//        Unitt= (EditText) view.findViewById(R.id.Unitt);
//        Barangay= (EditText) view.findViewById(R.id.Barangay);
//        country_name = (EditText) view.findViewById(R.id.country_name);
//        region_name= (EditText) view.findViewById(R.id.region_name);
//        city_name = (EditText) view.findViewById(R.id.city_name);
        first = (EditText) view.findViewById(R.id.first);
        last = (EditText) view.findViewById(R.id.last);
        eadd = (EditText) view.findViewById(R.id.emaill);
        mobile = (EditText) view.findViewById(R.id.mobile);
        phone = (EditText) view.findViewById(R.id.phone);
        imageProfile = (CircleImageView) view.findViewById(R.id.imageProfile);
        sessionManager = new SessionManager(getActivity());
        eadd.setEnabled(false);
        first.setTypeface(bariol);
        last.setTypeface(bariol);
        mobile.setTypeface(bariol);
        eadd.setTypeface(bariol);
        phone.setTypeface(bariol);
//        confirmpass  = (EditText) view.findViewById(R.id.confpassword);
//        newpass  = (EditText) view.findViewById(R.id.newpassword);
//        curentpass  = (EditText) view.findViewById(R.id.cpassword);

//        confirmpass.setVisibility(View.GONE);
//        newpass.setVisibility(View.GONE);
//        curentpass.setVisibility(View.GONE);
       // cb=(CheckBox)view.findViewById(R.id.checkbox);

//        cb.setOnClickListener(new View.OnClickListener() {  // checkbox listener
//            public void onClick(View v) {
//                // Perform action on clicks, depending on whether it's now checked
//                if (((CheckBox) v).isChecked()) {
//                    confirmpass.setVisibility(View.VISIBLE);  //visible==0
//                    newpass.setVisibility(View.VISIBLE);
//                    curentpass.setVisibility(View.VISIBLE);
//                } else if (((CheckBox) v).isChecked() == false) {
//                    confirmpass.setVisibility(View.GONE);  //visible==0
//                    newpass.setVisibility(View.GONE);
//                    curentpass.setVisibility(View.GONE);
//                }
//            }
//        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_DOMAINTest)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API_Retrofit.class);
        ProfileRequest serviceRequest = new ProfileRequest();
        serviceRequest.setApp_key("88888");
        serviceRequest.setUser_id(sessionManager.getUser_id());
        serviceRequest.setIs_mobile("1");
        serviceRequest.setToken(sessionManager.getToken());

        Call<ProfileResponse> serviceResponseCall =  service.getProfileInfo(serviceRequest);
        Log.d("USER_IS", sessionManager.getUser_id());
        Log.d("USER_TOKEN", sessionManager.getToken());


        serviceResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, retrofit2.Response<ProfileResponse> response) {
                int statusCode = response.code();


                ProfileResponse  serviceResponsee = response.body();

                Log.d("STATUS CODE", String.valueOf(statusCode));

                Log.d("RESPONSE_BODY",  response.body().toString());
                Log.d("SERVICE_RESPONSE",  serviceResponsee.toString());
                Log.d("FIRST_PROFILE",serviceResponsee.getData().get(0).getFirstName().toString());
                for(int i=0;i< serviceResponsee.getData().size();i++) {
//                    if(serviceResponsee.getData().get(0).getZipCode()== null){
//                        zip.setText("");
//                    }else{
//                        zip.setText(serviceResponsee.getData().get(0).getZipCode().toString());
//                    }

                    first.setText(serviceResponsee.getData().get(0).getFirstName().toString());
                    last.setText(serviceResponsee.getData().get(0).getLastName().toString());
                    eadd.setText(serviceResponsee.getData().get(0).getEmail().toString());

                    if(serviceResponsee.getData().get(0).getMobile()== null){
                        mobile.setText("");
                    }else{
                        mobile.setText(serviceResponsee.getData().get(0).getMobile().toString());
                    }




                    if( serviceResponsee.getData().get(0).getPhone()== null){
                        phone.setText("");
                    }else{
                        phone.setText(serviceResponsee.getData().get(0).getPhone().toString());
                    }



//                    if(serviceResponsee.getData().get(0).getCityName()== null){
//                        city_name.setText("");
//                    }else{
//                        city_name.setText(serviceResponsee.getData().get(0).getCityName().toString());
//                    }
//                    if(serviceResponsee.getData().get(0).getRegionName()== null){
//                        region_name.setText("");
//                    }else{
//                        region_name.setText(serviceResponsee.getData().get(0).getRegionName().toString());
//                    }
//                    if(serviceResponsee.getData().get(0).getCountryName()== null){
//                        country_name.setText("");
//                    }else{
//                        country_name.setText(serviceResponsee.getData().get(0).getCountryName().toString());
//                    }
//                    if(serviceResponsee.getData().get(0).getAreaVillage()== null){
//                        Barangay.setText("");
//                    }else{
//                        Barangay.setText(serviceResponsee.getData().get(0).getAreaVillage().toString());
//                    }
//                    if(serviceResponsee.getData().get(0).getUnit()== null){
//                        Unitt.setText("");
//                    }else{
//                        Unitt.setText(serviceResponsee.getData().get(0).getUnit().toString());
//                    }
//                    if(serviceResponsee.getData().get(0).getStreet()== null){
//                        Street.setText("");
//                    }else{
//                        Street.setText(serviceResponsee.getData().get(0).getStreet().toString());
//                    }

                    UserInformation userInformation = new UserInformation(getActivity());

                    if(sessionManager.isLoggedIn()) {
                        Glide.with(getActivity())
                                .load(userInformation.getProfilePicture().toString())
                                .centerCrop()
                                .dontAnimate()
                                .placeholder(R.drawable.ic_launcher)
                                .into(imageProfile);
                    }else{
                        Glide.with(getActivity())
                                .load("@drawable/com_facebook_profile_picture_blank_portrait")
                                .centerCrop()
                                .dontAnimate()
                                .placeholder(R.drawable.ic_launcher)
                                .into(imageProfile);

                    }
                    Log.d("SPICTURE",  serviceResponsee.getData().get(0).getImageMd().toString());

                }









            }


            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });


        return view;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();    //remove all items
        getActivity().getMenuInflater().inflate(R.menu.profile_edit_menu, menu);
    }
    public void login() {

        int statusCode;
//        if (!validate()) {
//            onLoginFailed();
//            return;
//        }

       // mSubmit.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Sending...");
        progressDialog.show();



        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        Retrofit retrofit2 = new Retrofit.Builder()
                                .baseUrl(Constants.API_DOMAINTest)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        serviceE = retrofit2.create(API_Retrofit.class);
//                        UpdateProfileRequest serviceRequest2 = new UpdateProfileRequest();
//                        serviceRequest2.setApp_key("88888");
//                        serviceRequest2.setIs_mobile("1");
//                        serviceRequest2.setToken(sessionManager.getToken());
//                        serviceRequest2.setUser_id(sessionManager.getUser_id());
//                        serviceRequest2.setFirst_name(first.getText().toString());
//                        serviceRequest2.setLast_name(last.getText().toString());
//                        serviceRequest2.setPhone(phone.getText().toString());
//                        serviceRequest2.setPhone(mobile.getText().toString());
//                        serviceRequest2.setPhone(eadd.getText().toString());

                        Log.d("FIRSTNAME", first.getText().toString());
                        Log.d("MOBILE", mobile.getText().toString());
                        Log.d("EMAIL",eadd.getText().toString());

                        Call<UpdateProfileResponse> serviceResponseCall2 =  serviceE.getUpdatedProfileInfo("88888","1",sessionManager.getToken(),sessionManager.getUser_id(),first.getText().toString(),last.getText().toString(),phone.getText().toString(),mobile.getText().toString(),eadd.getText().toString());
                        Log.d("USER_BLAH", sessionManager.getUser_id());
                        Log.d("USER_TOKEN", sessionManager.getToken());

                        serviceResponseCall2.enqueue(new Callback<UpdateProfileResponse>() {
                            @Override
                            public void onResponse(Call<UpdateProfileResponse> call, retrofit2.Response<UpdateProfileResponse> response2) {
                                int statusCode = response2.code();
try {
    UpdateProfileResponse  serviceResponsee = response2.body();

    Log.d("STATUS CODE", String.valueOf(statusCode));

    Log.d("RESPONSE_BODY",  response2.body().toString());
    Log.d("SERVICE_RESPONSE",  serviceResponsee.getMessage().toString());


    if(String.valueOf(statusCode).equals("200")){
        // success();
        Log.d("YSTAT_CODEEEEE",String.valueOf(statusCode));
        onLoginSuccess();
        progressDialog.dismiss();
    }
}catch (IllegalStateException | JsonSyntaxException exception){
    Log.d("YUUUUUUUYYYYY","wahhaha");
}




                            }


                            @Override
                            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                                Log.d("FAILURE", t.getMessage().toString());
                                onLoginFailed();
                            }
                        });

                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.icon_save) {
            login();


            return true;
        }
//        if (id == R.id.logout) {
//            LogOutDialogFragment logout = new LogOutDialogFragment();
//            logout.show(this.getActivity().getFragmentManager(), "logout");
//
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    public void onLoginSuccess() {
//        _loginButton.setEnabled(true);
//        finish();
        UserInformation changeuserinfo = new UserInformation(getActivity());

        changeuserinfo.updateFirstName(first.getText().toString());
        changeuserinfo.updateLastName(last.getText().toString());
        changeuserinfo.updateEmail(eadd.getText().toString());
        Toast.makeText(getActivity(), "Saved !", Toast.LENGTH_LONG).show();

    }

    public void onLoginFailed() {
        Toast.makeText(getActivity(), "Not Saved !", Toast.LENGTH_LONG).show();

       // mSubmit.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = eadd.getText().toString();
        String password = newpass.getText().toString();
        String cpassword = confirmpass.getText().toString();
        String lname = last.getText().toString();
        String fname = first.getText().toString();
        String currentpasss = curentpass.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            eadd.setError("enter a valid email address");
            valid = false;
        } else {
            eadd.setError(null);
        }

        if ((!password.isEmpty()) && password.length() < 4 || password.length() > 10) {
            newpass.setError("Type in between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            newpass.setError(null);
        }

        if ((!cpassword.isEmpty()) && cpassword.length() < 4 || cpassword.length() > 10) {
            confirmpass.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        }else if((!cpassword.equals(password)) && currentpasss.isEmpty() ){
            confirmpass.setError("password and confirm password doesn't match");
            valid = false;
        }else if((cpassword.equals(password)) && currentpasss.isEmpty() ){
            curentpass.setError("type in your current password");
            valid = false;
        }else {
            confirmpass.setError(null);
        }
        if (lname.isEmpty() ) {
            last.setError("Last name field is required");
            valid = false;
        } else {
            last.setError(null);
        }
        if (fname.isEmpty() ) {
            first.setError("First name field is required");
            valid = false;
        } else {
            first.setError(null);
        }
        return valid;


    }

    private void success(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Profile Updated!")
                .setContentText(first.getText().toString()+" 's "+" Profile has been Updated")
                .show();

    }


}
