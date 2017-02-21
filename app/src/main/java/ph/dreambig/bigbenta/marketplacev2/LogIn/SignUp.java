package ph.dreambig.bigbenta.marketplacev2.LogIn;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.TermsFragmentMain;

/**
 * Created by Acer on 31/03/2016.
 */
public class SignUp extends Fragment implements View.OnClickListener {
    Button mSubmit;
    EditText fn, ln, _emailText, confirmemail, _passwordText, _confirmpassword,mn;
    TextView Condition,terms,createLabel;
    static int LayoutId;
    private static final String TAG = "LoginActivity";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup,container,false);
        mSubmit = (Button) view.findViewById(R.id.submit);

        ln = (EditText) view.findViewById(R.id.ln);
        terms= (TextView) view.findViewById(R.id.terms);
        _passwordText = (EditText) view.findViewById(R.id.pw);
        _confirmpassword = (EditText) view.findViewById(R.id.confirmpassword);
        Condition = (TextView)view.findViewById(R.id.condition);
        createLabel= (TextView) view.findViewById(R.id.createLabel);
        Condition.setOnClickListener(this);
//        mSubmit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.submit:
                login();
                break;
            case R.id.condition:

                TermsFragmentMain fragment = new TermsFragmentMain();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerView, fragment);
                 fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
        }
    }

    public static void setID(int Id){LayoutId = Id;}

    public int getLayoutId(){return LayoutId; }

    public void login() {
        Log.d(TAG, "SignUp");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        mSubmit.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.alert_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Sending...");
        progressDialog.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 1000);
    }

    public void onLoginSuccess() {
//
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_LONG).show();

        mSubmit.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String cpassword = _confirmpassword.getText().toString();
        String lname = ln.getText().toString();
        String fname = fn.getText().toString();
        String mname = mn.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }
        if (mname.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mname).matches()) {
            mn.setError("Enter valid middle name");
            valid = false;
        } else {
            mn.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Invalid password");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (cpassword.isEmpty() || cpassword.length() < 4 || cpassword.length() > 10) {
            _confirmpassword.setError("Invalid password");
            valid = false;
        }else if(!cpassword.equals(password)){
            _confirmpassword.setError("Invalid password");
            valid = false;
        }else {
            _confirmpassword.setError(null);
        }
        if (lname.isEmpty() ) {
            ln.setError("Last name field is required");
            valid = false;
        } else {
            ln.setError(null);
        }
        if (fname.isEmpty() ) {
            fn.setError("First name field is required");
            valid = false;
        } else {
            fn.setError(null);
        }
        return valid;
    }



}
