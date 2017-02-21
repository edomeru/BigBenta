package ph.dreambig.bigbenta.marketplacev2.LANDINGPAGE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.HelpCenterActivity;
import ph.dreambig.bigbenta.marketplacev2.Home.MainActivity;
import ph.dreambig.bigbenta.marketplacev2.LogIn.FirstPage;
import ph.dreambig.bigbenta.marketplacev2.LogIn.Frag1;
import ph.dreambig.bigbenta.marketplacev2.Search.SearchActivitySample;
import ph.dreambig.bigbenta.marketplacev2.SessionManager;

/**
 * Created by edmeralarte on 14/09/2016.
 */

public class LANDINGPAGE extends AppCompatActivity implements View.OnClickListener {
TextView signin,hc;
    ImageView str,ads,service,prime;
    SessionManager sessionManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ph.dreambig.bigbenta.marketplacev2.R.layout.landingpagee);
        Toolbar toolbar = (Toolbar) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.toolbarlanding);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        sessionManager = new SessionManager(getApplicationContext());


        hc =( TextView)findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.hc);
        signin =( TextView)findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.signin);
        str =( ImageView) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.store);
        prime=( ImageView) findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.prime);
                ads =( ImageView)findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.classified);
                service =( ImageView)findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.service);
        str.setOnClickListener(this);
        ads.setOnClickListener(this);
        service.setOnClickListener(this);
        signin.setOnClickListener(this);
        hc.setOnClickListener(this);


        prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "BigBenta Prime will be Out SOON, Please Visit Our Website:  www.bigbenta.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "montserratlight.ttf");
        signin.setTypeface(tf);
        if (sessionManager.isLoggedIn() == true) {
            signin.setVisibility(View.GONE);
        }
        hc.setTypeface(tf);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the com.example.edmeralarte.bigbenta2.Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == ph.dreambig.bigbenta.marketplacev2.R.id.login) {
            Frag1 fr=new Frag1();
            FragmentManager fm= this.getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            Bundle args = new Bundle();
            args.putString("position", "1");
            fr.setArguments(args);
            ft.addToBackStack(null);
            ft.replace(ph.dreambig.bigbenta.marketplacev2.R.id.containerView, fr);
            ft.commit();

            return true;
        }

//        if (id == R.id.action_chat) {
//            return true;
//        }
//        if (id == R.id.action_cart) {
//            StoreTabFragment fr=new StoreTabFragment();
//            FragmentManager fm= this.getSupportFragmentManager();
//            FragmentTransaction ft=fm.beginTransaction();
//            Bundle args = new Bundle();
//            args.putString("position", "1");
//            fr.setArguments(args);
//            ft.replace(R.id.containerView, fr);
//            ft.commit();
//            return true;
//        }
//
//        if (id == R.id.action_service) {
//            ServicesTabFragment fr=new ServicesTabFragment();
//            FragmentManager fm= this.getSupportFragmentManager();
//            FragmentTransaction ft=fm.beginTransaction();
//            Bundle args = new Bundle();
//            args.putString("position", "1");
//            fr.setArguments(args);
//            ft.replace(R.id.containerView, fr);
//            ft.commit();
//            return true;
//        }

        if (id == ph.dreambig.bigbenta.marketplacev2.R.id.search) {
            searchItem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(ph.dreambig.bigbenta.marketplacev2.R.menu.landingmenu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case ph.dreambig.bigbenta.marketplacev2.R.id.store:

                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("pos","store");
                this.finish();
                startActivity(i);


                break;
            case ph.dreambig.bigbenta.marketplacev2.R.id.classified:
                Intent c = new Intent(getApplicationContext(), MainActivity.class);
                c.putExtra("pos","ads");
                this.finish();
                startActivity(c);


                break;

            case ph.dreambig.bigbenta.marketplacev2.R.id.service:
                Intent s = new Intent(getApplicationContext(), MainActivity.class);
                s.putExtra("pos","service");
                this.finish();
                startActivity(s);

                break;

            case ph.dreambig.bigbenta.marketplacev2.R.id.signin:
                Intent sign = new Intent(this,FirstPage.class);
                startActivity(sign);


                break;

            case ph.dreambig.bigbenta.marketplacev2.R.id.hc:
                Intent hc = new Intent(this,HelpCenterActivity.class);
                startActivity(hc);


                break;


//            case ph.dreambig.bigbenta.marketplacev2.R.id.prime:
//
//                Toast.makeText(this, "BigBenta Prime will be Out SOON, Please Visit Our Website:  www.bigbenta.com",Toast.LENGTH_SHORT).show();
//                break;

            default:
                break;
        }

        }


    public void searchItem() {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();

        dialog.setContentView(ph.dreambig.bigbenta.marketplacev2.R.layout.search_dialog);

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setTitle("BigBenta");

        final EditText editTextSearch = (EditText) dialog.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.editTextSearch);

        dialog.show();

        Button buttonSearch = (Button) dialog.findViewById(ph.dreambig.bigbenta.marketplacev2.R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
                if (editTextSearch.getText().toString().trim().length() == 0) {
                    dialog.dismiss();
                } else {
//                    Intent intent = new Intent(LANDINGPAGE.this, SearchALLActivity.class);
//                    Bundle bundleText = new Bundle();
//                    bundleText.putString(Constants.KEY_SEARCH, editTextSearch.getText().toString());
//
//                    intent.putExtras(bundleText);
//                    startActivity(intent);
//                    dialog.dismiss();
//                    Log.d("EDITTEXTSEARCH",  editTextSearch.getText().toString());


                    Intent intent = new Intent(LANDINGPAGE.this, SearchActivitySample.class);
                    Bundle bundleText = new Bundle();
                    bundleText.putString(Constants.KEY_SEARCH, editTextSearch.getText().toString());

                    intent.putExtras(bundleText);
                    startActivity(intent);
                    dialog.dismiss();
                    Log.d("EDITTEXTSEARCH",  editTextSearch.getText().toString());
//
//                    SearchALLFragment fr = new SearchALLFragment();
//                        FragmentManager fm = LANDINGPAGE.this.getSupportFragmentManager();
//                        FragmentTransaction ft = fm.beginTransaction();
//                        Bundle args = new Bundle();
//                        args.putString(Constants.KEY_SEARCH, editTextSearch.getText().toString());
//                        fr.setArguments(args);
//                        ft.addToBackStack(null);
//                        ft.replace(R.id.containerView, fr);
//                        ft.commit();
//                        dialog.dismiss();


                }
            }
        });
    }
    }

