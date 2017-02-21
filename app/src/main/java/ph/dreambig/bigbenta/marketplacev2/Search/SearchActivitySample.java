package ph.dreambig.bigbenta.marketplacev2.Search;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ph.dreambig.bigbenta.marketplacev2.Constants;
import ph.dreambig.bigbenta.marketplacev2.R;
import ph.dreambig.bigbenta.marketplacev2.Search.Search_ALL.SearchALLFragment;


/**
 * Created by Acer on 31/03/2016.
 */
public class SearchActivitySample extends AppCompatActivity {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    TextView toolbar_subtitle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.seachpage);
        toolbar_subtitle1 = (TextView)findViewById(R.id.toolbar_title);
        Typeface bariol = Typeface.createFromAsset(getAssets(), "Bariol_Regular.otf");
        toolbar_subtitle1.setTypeface(bariol);
//        FirstPageLogin firstPageLogin = new FirstPageLogin();
//        FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.add(R.id.firstpage, firstPageLogin, "");
//        transaction.commit();
//        FirstPageLogin fragment = new FirstPageLogin();
//        android.support.v4.app.FragmentManager fragmentManager = this.getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.firstpage, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFragmentManager = getSupportFragmentManager();
        Bundle bundleText = getIntent().getExtras();
    String    search = bundleText.getString(Constants.KEY_SEARCH);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(24,53,68));
        }

//        mFragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.replace(R.id.firstpage,new SearchALLFragment()).commit();

        SearchALLFragment fr = new SearchALLFragment();
                        FragmentManager fm = SearchActivitySample.this.getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Bundle args = new Bundle();
                        args.putString(Constants.KEY_SEARCH, search);
                        fr.setArguments(args);
                        ft.addToBackStack(null);
                        ft.replace(R.id.seachpage, fr);
                        ft.commit();


    }
//    @Override
//    public void onBackPressed() {
//       android.support.v4.app. FragmentManager fm = getFragmentManager();
//        if (fm.getBackStackEntryCount() >= 1) {
//            fm.popBackStack();
//        } else {
//            finish();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the com.example.edmeralarte.bigbenta2.Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

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

        if (id == R.id.search) {
            searchItem();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTextTitle(String text){
        toolbar_subtitle1.setTextColor(Color.WHITE);
        toolbar_subtitle1.setText(text);
        //toolbar_title2.setVisibility(View.GONE);
        //toolbar_subtitle1.setVisibility(View.GONE);

    }
    public void searchItem() {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = dialog.getWindow();

        dialog.setContentView(R.layout.search_dialog);

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setTitle("BigBenta");

        final EditText editTextSearch = (EditText) dialog.findViewById(R.id.editTextSearch);

        dialog.show();

        Button buttonSearch = (Button) dialog.findViewById(R.id.buttonSearch);
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


                    Intent intent = new Intent(SearchActivitySample.this, SearchActivitySample.class);
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
    public void setTextName(String text) {
        toolbar_subtitle1.setTextColor(Color.WHITE);
        toolbar_subtitle1.setText(text);
    }

}


